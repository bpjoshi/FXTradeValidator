package com.bpjoshi.tradevalidator.validation.service;

import org.springframework.stereotype.Service;

import javaslang.Tuple;
import javaslang.Tuple2;
/**
 * @author bpjoshi(Bhagwati Prasad)
 */
@Service
public class CurrencyConversionService {
	public Tuple2<String, String> convert(final String currencyPair) {
        if (currencyPair.length() != 6) {
            throw new IllegalArgumentException("CURRENCY PAIR LENGTH SHOULD BE 6 LETTERS.");
        }
        final String first = currencyPair.substring(0, 3);
        final String second = currencyPair.substring(3);
        return Tuple.of(first, second);
    }
}
