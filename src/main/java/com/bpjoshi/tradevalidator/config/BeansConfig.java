package com.bpjoshi.tradevalidator.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.client.RestTemplate;

import com.bpjoshi.tradevalidator.validation.Validator;
import com.bpjoshi.tradevalidator.validation.forward.ForwardValidator;
import com.bpjoshi.tradevalidator.validation.option.AmericanStyleExcerciseStartDateValidator;
import com.bpjoshi.tradevalidator.validation.option.ExpiryAndPremiumDateValidator;
import com.bpjoshi.tradevalidator.validation.option.OptionCurrencyISOValidator;
import com.bpjoshi.tradevalidator.validation.option.StyleValidator;
import com.bpjoshi.tradevalidator.validation.service.CurrencyConversionService;
import com.bpjoshi.tradevalidator.validation.service.CurrencyValidationService;
import com.bpjoshi.tradevalidator.validation.service.FixerIOService;
import com.bpjoshi.tradevalidator.validation.spot.SpotValidator;
import com.bpjoshi.tradevalidator.validation.trade.CurrencyISOCodeValidator;
import com.bpjoshi.tradevalidator.validation.trade.SupportedPartiesValidator;
import com.bpjoshi.tradevalidator.validation.trade.TradeAndValueDateValidator;
import com.bpjoshi.tradevalidator.validation.trade.ValueDateOnHolidayValidator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
/**
 * @author bpjoshi(Bhagwati Prasad)
 */
@Configuration
public class BeansConfig {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	@Primary
	public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
		ObjectMapper objectMapper = builder.createXmlMapper(false).build();
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		return objectMapper;
	}

	@Bean
	public javax.validation.Validator validator() {
		return new org.springframework.validation.beanvalidation.LocalValidatorFactoryBean();
	}

	@Bean
	public List<Validator> trades(CurrencyValidationService currencyISO, FixerIOService fixerClient,
			CurrencyConversionService currenciesPairConverter) {
		return Arrays.asList(new CurrencyISOCodeValidator(currencyISO, currenciesPairConverter),
				new SupportedPartiesValidator(), new TradeAndValueDateValidator(),
				new ValueDateOnHolidayValidator(fixerClient, currenciesPairConverter));
	}

	@Bean
	public List<Validator> options(CurrencyValidationService currencyISO) {
		return Arrays.asList(new StyleValidator(),
				new AmericanStyleExcerciseStartDateValidator(),
				new OptionCurrencyISOValidator(currencyISO),
				new ExpiryAndPremiumDateValidator());
	}

	@Bean
	public List<Validator> forwards() {
		return Arrays.asList(new ForwardValidator());
	}

	@Bean
	public List<Validator> spots() {
		return Arrays.asList(new SpotValidator());
	}
}
