package com.bpjoshi.tradevalidator.validation.trade;

import java.util.Arrays;
import java.util.List;

import com.bpjoshi.tradevalidator.config.ClassInfo;
import com.bpjoshi.tradevalidator.model.Trade;
import com.bpjoshi.tradevalidator.validation.Validator;
import com.bpjoshi.tradevalidator.validation.ValidatorResult;
/**
 * @author bpjoshi(Bhagwati Prasad)
 */
@ClassInfo(summary = "Validates supported parties. Currently supported parties are PLUTO1, PLUTO2")
public class SupportedPartiesValidator implements Validator {
	private static final String VALIDATOR_MESSAGE = "THE CUSTOMER PARTY IS NOT SUPPORTED.";
    private static final List<String> SUPPORTED_CUSTOMERS = Arrays.asList(
            "PLUTO1",
            "PLUTO2"
    );
	@Override
	public ValidatorResult validate(Trade data) {
		return SUPPORTED_CUSTOMERS.contains(data.getCustomer())
                ? ValidatorResult.valid()
                : ValidatorResult.invalid(VALIDATOR_MESSAGE);
	}

}
