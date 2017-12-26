package com.bpjoshi.tradevalidator.validation.option;

import com.bpjoshi.tradevalidator.config.ClassInfo;
import com.bpjoshi.tradevalidator.model.Trade;
import com.bpjoshi.tradevalidator.validation.Validator;
import com.bpjoshi.tradevalidator.validation.ValidatorResult;
/**
 * @author bpjoshi(Bhagwati Prasad)
 */
@ClassInfo(summary = "Validator class for exercise start date of Trades having AMERICAN Style")
public class AmericanStyleExcerciseStartDateValidator implements Validator {
	
	private static final String VALIDATOR_MESSAGE = "EXERCISE START DATE IS NOT VALID.";
	@Override
	public ValidatorResult validate(Trade data) {
		 return "AMERICAN".equals(data.getStyle())
	                && data.getExcerciseStartDate() != null
	                && (data.getExcerciseStartDate().isAfter(data.getTradeDate()) && data.getExcerciseStartDate().isBefore(data.getExpiryDate()))
	                ? ValidatorResult.valid()
	                : ValidatorResult.invalid(VALIDATOR_MESSAGE);
	}

}
