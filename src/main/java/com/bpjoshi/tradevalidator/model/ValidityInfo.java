package com.bpjoshi.tradevalidator.model;

import lombok.Getter;
/**
 * @author bpjoshi(Bhagwati Prasad)
 */
@Getter
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
