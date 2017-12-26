package com.bpjoshi.tradevalidator.validation.option;

import com.bpjoshi.tradevalidator.config.ClassInfo;
import com.bpjoshi.tradevalidator.model.Trade;
import com.bpjoshi.tradevalidator.validation.Validator;
import com.bpjoshi.tradevalidator.validation.ValidatorResult;
import com.bpjoshi.tradevalidator.validation.service.CurrencyValidationService;
/**
 * @author bpjoshi(Bhagwati Prasad)
 */
@ClassInfo(summary = "Validator class for valid currency formats")
public class OptionCurrencyISOValidator implements Validator {
	
	private static final String VALIDATOR_MESSAGE = "OPTION CURRENCY SHOULD BE IN VALID FORMAT";
    private CurrencyValidationService currencyISO;

    public OptionCurrencyISOValidator(final CurrencyValidationService currencyISO) {
        this.currencyISO = currencyISO;
    }
	@Override
	public ValidatorResult validate(Trade data) {
		 return currencyISO.checkValidISO(data.getPremiumCcy()) && currencyISO.checkValidISO(data.getPayCcy())
	                ? ValidatorResult.valid()
	                : ValidatorResult.invalid(VALIDATOR_MESSAGE);
	}

}
