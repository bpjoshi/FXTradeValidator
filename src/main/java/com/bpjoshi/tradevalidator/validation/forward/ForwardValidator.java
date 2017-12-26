package com.bpjoshi.tradevalidator.validation.forward;

import com.bpjoshi.tradevalidator.config.ClassInfo;
import com.bpjoshi.tradevalidator.model.Trade;
import com.bpjoshi.tradevalidator.validation.Validator;
import com.bpjoshi.tradevalidator.validation.ValidatorResult;

/**
 * @author bpjoshi(Bhagwati Prasad)
 */
@ClassInfo(summary = "Validator class for Forward Type")
public class ForwardValidator implements Validator{
	 private static final String VALIDATOR_MESSAGE = "FORWARD TRADE DATE SHOULD BE BEFORE VALUE DATE.";
	    @Override
	    public ValidatorResult validate(final Trade data) {
	        return data.getValueDate().isAfter(data.getTradeDate())
	                ? ValidatorResult.valid()
	                : ValidatorResult.invalid(VALIDATOR_MESSAGE);
	    }
}
