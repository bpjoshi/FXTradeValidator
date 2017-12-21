package com.bpjoshi.tradevalidator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.bpjoshi.tradevalidator.config.BeansConfig;
/**
 * @author bpjoshi(Bhagwati Prasad)
 */
@SpringBootApplication
@Import(BeansConfig.class)
public class ForexTradeValidatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForexTradeValidatorApplication.class, args);
	}
	
}
