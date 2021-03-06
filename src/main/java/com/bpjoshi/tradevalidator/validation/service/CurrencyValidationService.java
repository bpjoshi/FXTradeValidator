package com.bpjoshi.tradevalidator.validation.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bpjoshi.tradevalidator.config.ClassInfo;
/**
 * @author bpjoshi(Bhagwati Prasad)
 */
@Service
@ClassInfo(summary = "Represents valid currencies")
public class CurrencyValidationService {
	private static final List<String> VALID_CURRENCIES = Arrays.asList(
            "USD",
            "EUR"
    );

    public boolean checkValidISO(final String currency) {
        return VALID_CURRENCIES.contains(currency);
    }
}
