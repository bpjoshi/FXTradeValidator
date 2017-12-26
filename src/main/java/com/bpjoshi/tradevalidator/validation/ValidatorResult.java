package com.bpjoshi.tradevalidator.validation;

import com.bpjoshi.tradevalidator.config.ClassInfo;

import lombok.Getter;
/**
 * @author bpjoshi(Bhagwati Prasad)
 */
@Getter
@ClassInfo(summary = "Instance of this class is returned as a result of validation")
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
