package com.bpjoshi.tradevalidator.controller;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.concat;
import static javaslang.API.$;
import static javaslang.API.Case;
import static javaslang.API.Match;

import java.util.List;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bpjoshi.tradevalidator.config.ClassInfo;
import com.bpjoshi.tradevalidator.model.Trade;
import com.bpjoshi.tradevalidator.model.ValidList;
import com.bpjoshi.tradevalidator.model.ValidityInfo;
import com.bpjoshi.tradevalidator.validation.Validator;
import com.bpjoshi.tradevalidator.validation.ValidatorResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javaslang.Tuple;
import javaslang.Tuple2;

/**
 * @author bpjoshi(Bhagwati Prasad)
 */
@RestController
@ClassInfo(summary="Controller class that handles validation for trade")
@Api(tags = {"trades"})
public class TradeController {

	private final List<Validator> validationConditions;
	private final List<Validator> optionConditions;
	private final List<Validator> spotConditions;
	private final List<Validator> forwardConditions;

	public TradeController(@Qualifier("trades") List<Validator> validationConditions,
			@Qualifier("options") List<Validator> optionConditions,
			@Qualifier("spots") List<Validator> spotConditions,
			@Qualifier("forwards") List<Validator> forwardConditions) {
		this.validationConditions = validationConditions;
		this.optionConditions = optionConditions;
		this.spotConditions = spotConditions;
		this.forwardConditions = forwardConditions;
	}
	
	@RequestMapping(value = "/trades", method = RequestMethod.POST)
	@ApiOperation(value = "Create trades.", notes = "Returns all trades with validation messages")
    ResponseEntity<?> validateTrade(@Valid @RequestBody final ValidList<Trade> validTrades) {
        final Stream<Tuple2<Trade, Stream<ValidatorResult>>> specificValidation = validTrades.stream()
                                                                                                .map(x -> Match(x.getType()).of(
                                                                                                        Case($("VanillaOption"), validateOption(x)),
                                                                                                        Case($("Forward"), validateForward(x)),
                                                                                                        Case($("Spot"), validateTrade(x))));

        final List<Tuple2<Trade, String>> allValidationResults = specificValidation.map(x -> Tuple.of(x._1, concat(x._2, tradesValidation(x))))
                                                                                   .map(x -> Tuple.of(x._1, prepareValidationMessage(x)))
                                                                                   .collect(toList());
        return ResponseEntity.ok(convertToValidationInfo(allValidationResults));
    }

    private List<ValidityInfo> convertToValidationInfo(List<Tuple2<Trade, String>> validationResults) {
        return validationResults.stream().map(x -> new ValidityInfo(x._1, x._2.isEmpty(), x._2)).collect(toList());
    }

    private String prepareValidationMessage(Tuple2<Trade, Stream<ValidatorResult>> validationResults) {
        return validationResults._2.filter(y -> !y.isValid()).map(x -> x.getValidationInfo()).collect(joining(" "));
    }

    private Stream<ValidatorResult> tradesValidation(Tuple2<Trade, Stream<ValidatorResult>> validationResults) {
        return validationConditions.stream().map(x -> x.validate(validationResults._1));
    }

    private Tuple2<Trade, Stream<ValidatorResult>> validateTrade(Trade trade) {
        return Tuple.of(trade, spotConditions.stream().map(x -> x.validate(trade)));
    }

    private Tuple2<Trade, Stream<ValidatorResult>> validateForward(Trade trade) {
        return Tuple.of(trade, forwardConditions.stream().map(x -> x.validate(trade)));
    }

    private Tuple2<Trade, Stream<ValidatorResult>> validateOption(Trade trade) {
        return Tuple.of(trade, optionConditions.stream().map(x -> x.validate(trade)));
    }
}
