package com.bpjoshi.tradevalidator.validation.option;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.bpjoshi.tradevalidator.model.Trade;
import com.bpjoshi.tradevalidator.validation.ValidatorResult;
import com.bpjoshi.tradevalidator.validation.service.CurrencyValidationService;

/**
 * @author bpjoshi(Bhagwati Prasad)
 */
public class OptionCurrencyISOValidatorTest {

    @Test
    public void shouldReturnValidWhenCurrencyIsISOValid() {
        //given
        final Trade option = mock(Trade.class);
        when(option.getPayCcy()).thenReturn("USD");
        when(option.getPremiumCcy()).thenReturn("EUR");

        //when
        final ValidatorResult result = new OptionCurrencyISOValidator(new CurrencyValidationService()).validate(option);

        //then
        assertThat(result.isValid()).isTrue();
    }

    @Test
    public void shouldReturnInvalidWhenCurrencyIsNotISOValid() {
        //given
        final Trade option = mock(Trade.class);
        when(option.getPayCcy()).thenReturn("LALA");
        when(option.getPremiumCcy()).thenReturn("HEHE");

        //when
        final ValidatorResult result = new OptionCurrencyISOValidator(new CurrencyValidationService()).validate(option);

        //then
        assertThat(result.isValid()).isFalse();
    }
}
