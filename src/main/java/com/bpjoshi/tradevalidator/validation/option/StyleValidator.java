package com.bpjoshi.tradevalidator.validation.option;

import com.bpjoshi.tradevalidator.config.ClassInfo;
import com.bpjoshi.tradevalidator.model.Trade;
import com.bpjoshi.tradevalidator.validation.Validator;
import com.bpjoshi.tradevalidator.validation.ValidatorResult;
/**
 * @author bpjoshi(Bhagwati Prasad)
 */
@ClassInfo(summary = "Validates if the trade style is supported")
public class StyleValidator implements Validator {
	
	private static final String VALIDATOR_MESSAGE = "ENTERED STYLE TYPE IS NOT SUPPORTED.";
	@Override
	public ValidatorResult validate(Trade data) {
		return "EUROPEAN".equals(data.getStyle()) || "AMERICAN".equals(data.getStyle())
                ? ValidatorResult.valid()
                : ValidatorResult.invalid(VALIDATOR_MESSAGE);
	}

}
