package com.bpjoshi.tradevalidator.validation;

import com.bpjoshi.tradevalidator.config.ClassInfo;
import com.bpjoshi.tradevalidator.model.Trade;
/**
 * @author bpjoshi(Bhagwati Prasad)
 */
@ClassInfo(summary = "Classes that check trade validation should implement this common interface")
public interface Validator {
	 ValidatorResult validate(final Trade data);
}
