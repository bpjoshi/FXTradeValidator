package com.bpjoshi.tradevalidator.validation.trade;

import com.bpjoshi.tradevalidator.config.ClassInfo;
import com.bpjoshi.tradevalidator.model.Trade;
import com.bpjoshi.tradevalidator.validation.Validator;
import com.bpjoshi.tradevalidator.validation.ValidatorResult;
import com.bpjoshi.tradevalidator.validation.service.CurrencyConversionService;
import com.bpjoshi.tradevalidator.validation.service.FixerIOService;
import javaslang.Tuple2;
/**
 * @author bpjoshi(Bhagwati Prasad)
 */
@ClassInfo(summary = "class fixer api for checking if the value date for a trade is on a holiday")
public class ValueDateOnHolidayValidator implements Validator {
	 private static final String VALIDATOR_MESSAGE = "VALUE DATE FOR CURRENCIES IS ON NON WORKING DAY.";

	    private final FixerIOService fixerIOService;
	    private final CurrencyConversionService currenciesPairConverter;
		public ValueDateOnHolidayValidator(FixerIOService fixerIOService,
				CurrencyConversionService currenciesPairConverter) {
			super();
			this.fixerIOService = fixerIOService;
			this.currenciesPairConverter = currenciesPairConverter;
		}
		@Override
		public ValidatorResult validate(Trade data) {
	        final Tuple2<String, String> currencies = currenciesPairConverter.convert(data.getCcyPair());
	        return fixerIOService.hasCurrencyRateForDate(currencies._1, data.getValueDate()) && fixerIOService.hasCurrencyRateForDate(currencies._2, data.getValueDate())
	                ? ValidatorResult.valid()
	                : ValidatorResult.invalid(VALIDATOR_MESSAGE);
	    }
	    
}
