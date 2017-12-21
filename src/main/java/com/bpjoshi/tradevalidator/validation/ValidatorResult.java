package com.bpjoshi.tradevalidator.validation;

import lombok.Getter;
/**
 * @author bpjoshi(Bhagwati Prasad)
 */
@Getter
public class ValidatorResult {
	private final boolean isValid;
	private final String validationInfo;

	private ValidatorResult(final boolean isValid, final String validationInfo) {
		this.isValid = isValid;
		this.validationInfo = validationInfo;
	}

	public static ValidatorResult valid() {
		return new ValidatorResult(true, "");
	}

	public static ValidatorResult invalid(final String validationInfo) {
		return new ValidatorResult(false, validationInfo);
	}
}
