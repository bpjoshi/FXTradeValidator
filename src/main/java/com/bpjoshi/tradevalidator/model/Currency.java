package com.bpjoshi.tradevalidator.model;

import java.time.LocalDate;

import com.bpjoshi.tradevalidator.config.ClassInfo;

import lombok.Data;

/**
 * @author bpjoshi(Bhagwati Prasad)
 */
@Data
@ClassInfo(summary = "This class represents a currency with base and date")
public class Currency {
	private final String base;
    private final LocalDate date;
}
