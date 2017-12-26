package com.bpjoshi.tradevalidator.model;

import com.bpjoshi.tradevalidator.config.ClassInfo;

import lombok.Getter;
/**
 * @author bpjoshi(Bhagwati Prasad)
 */
@Getter
@ClassInfo(summary = "This class returned as response to a call to API represents validation information of a trade")
public class ValidityInfo {
	private final Trade trade;
    private final boolean valid;
    private final String error;

    public ValidityInfo(final Trade trade, final boolean valid, final String error) {
        this.trade = trade;
        this.valid = valid;
        this.error = error;
    }
}
