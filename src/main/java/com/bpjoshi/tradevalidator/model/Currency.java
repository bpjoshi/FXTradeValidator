package com.bpjoshi.tradevalidator.model;

import java.time.LocalDate;

import lombok.Data;

/**
 * @author bpjoshi(Bhagwati Prasad)
 */
@Data
public class Currency {
	private final String base;
    private final LocalDate date;
}
