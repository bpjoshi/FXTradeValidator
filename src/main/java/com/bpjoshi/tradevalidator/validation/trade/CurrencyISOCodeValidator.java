package com.bpjoshi.tradevalidator.validation.trade;

import com.bpjoshi.tradevalidator.model.Trade;
import com.bpjoshi.tradevalidator.validation.Validator;
import com.bpjoshi.tradevalidator.validation.ValidatorResult;
import com.bpjoshi.tradevalidator.validation.service.CurrencyConversionService;
import com.bpjoshi.tradevalidator.validation.service.CurrencyValidationService;

import javaslang.Tuple2;
/**
 * @author bpjoshi(Bhagwati Prasad)
 */
public class CurrencyISOCodeValidator implements Validator {
	private static final String VALIDATOR_MESSAGE = "ISO CODE IS INVALID FOR CURRENCIES %s. ";
	private final CurrencyValidationService currencyISO;
    private final CurrencyConversionService currenciesPairConverter;
    
	public CurrencyISOCodeValidator(CurrencyValidationService currencyISO, CurrencyConversionService currenciesPairConverter) {
		super();
		this.currencyISO = currencyISO;
		this.currenciesPairConverter = currenciesPairConverter;
	}

	@Override
	public ValidatorResult validate(Trade data) {
        final Tuple2<String, String> currencyPair = currenciesPairConverter.convert(data.getCcyPair());

        return currencyISO.checkValidISO(currencyPair._1) && currencyISO.checkValidISO(currencyPair._2)
                ? ValidatorResult.valid()
                : ValidatorResult.invalid(String.format(VALIDATOR_MESSAGE, data.getCcyPair()));

    }
}
