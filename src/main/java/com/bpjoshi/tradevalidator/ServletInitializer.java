package com.bpjoshi.tradevalidator;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
/**
 * @version 0.1
 * @author bpjoshi(Bhagwati Prasad)
 */
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ForexTradeValidatorApplication.class);
	}

}
