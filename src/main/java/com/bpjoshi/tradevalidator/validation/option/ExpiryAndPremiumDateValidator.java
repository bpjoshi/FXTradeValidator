package com.bpjoshi.tradevalidator.validation.option;

import com.bpjoshi.tradevalidator.config.ClassInfo;
import com.bpjoshi.tradevalidator.model.Trade;
import com.bpjoshi.tradevalidator.validation.Validator;
import com.bpjoshi.tradevalidator.validation.ValidatorResult;
/**
 * @author bpjoshi(Bhagwati Prasad)
 */
@ClassInfo(summary = "Validator class for Expiry & Premium date w.r.t. Delivery date")
public class ExpiryAndPremiumDateValidator implements Validator {
	
	private static final String VALIDATOR_MESSAGE = "EXPIRY OR PREMIUM DATE SHOULD BE BEFORE DELIVERY DATE.";
	
	@Override
	public ValidatorResult validate(Trade data) {
		 return data.getExpiryDate().isBefore(data.getDeliveryDate()) && data.getPremiumDate().isBefore(data.getDeliveryDate())
	                ? ValidatorResult.valid()
	                : ValidatorResult.invalid(VALIDATOR_MESSAGE);
	}

}
