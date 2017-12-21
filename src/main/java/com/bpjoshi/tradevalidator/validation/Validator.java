package com.bpjoshi.tradevalidator.validation;

import com.bpjoshi.tradevalidator.model.Trade;
/**
 * @author bpjoshi(Bhagwati Prasad)
 */
public interface Validator {
	 ValidatorResult validate(final Trade data);
}
