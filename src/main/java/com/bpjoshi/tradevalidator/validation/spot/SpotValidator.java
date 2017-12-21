package com.bpjoshi.tradevalidator.validation.spot;

import com.bpjoshi.tradevalidator.model.Trade;
import com.bpjoshi.tradevalidator.validation.Validator;
import com.bpjoshi.tradevalidator.validation.ValidatorResult;
/**
 * @author bpjoshi(Bhagwati Prasad)
 */
public class SpotValidator implements Validator{

    private static final String VALIDATOR_MESSAGE = "VALUE DATE SHOULD BE AFTER TRADE DATE.";

    @Override
    public ValidatorResult validate(final Trade data) {
        return data.getValueDate().isAfter(data.getTradeDate()) && data.getValueDate().isBefore(data.getTradeDate().plusDays(3))
                ? ValidatorResult.valid()
                : ValidatorResult.invalid(VALIDATOR_MESSAGE);
    }
}
