package com.bpjoshi.tradevalidator;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
/**
 * @author bpjoshi(Bhagwati Prasad)
 */
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ForexTradeValidatorApplication.class);
	}

}
