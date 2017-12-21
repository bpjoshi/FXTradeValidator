package com.bpjoshi.tradevalidator.validation.trade;

import com.bpjoshi.tradevalidator.model.Trade;
import com.bpjoshi.tradevalidator.validation.Validator;
import com.bpjoshi.tradevalidator.validation.ValidatorResult;
/**
 * @author bpjoshi(Bhagwati Prasad)
 */
public class TradeAndValueDateValidator implements Validator {
	private static final String VALIDATOR_MESSAGE = "TRADE DATE SHOULD BE BEFORE VALUE DATE";

	@Override
	public ValidatorResult validate(Trade data) {
		return data.getValueDate().isAfter(data.getTradeDate())
                ? ValidatorResult.valid()
                : ValidatorResult.invalid(VALIDATOR_MESSAGE);
	}
	
}
