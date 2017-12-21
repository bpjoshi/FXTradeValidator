package com.bpjoshi.tradevalidator.validation.trade;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.bpjoshi.tradevalidator.model.Trade;
import com.bpjoshi.tradevalidator.validation.ValidatorResult;
import com.bpjoshi.tradevalidator.validation.service.CurrencyConversionService;
import com.bpjoshi.tradevalidator.validation.service.CurrencyValidationService;

/**
 * @author bpjoshi(Bhagwati Prasad)
 */
public class CurrencyISOCodeValidatorTest {

    @Test
    public void shouldReturnValidWhenCurrenciesHaveValidISO() {
        //given
        final Trade trade = mock(Trade.class);
        when(trade.getCcyPair()).thenReturn("EURUSD");
        //when
        ValidatorResult result = new CurrencyISOCodeValidator(new CurrencyValidationService(), new CurrencyConversionService()).validate(trade);

        //then
        assertThat(result.isValid()).isTrue();
    }

    @Test
    public void shouldReturnInvalidWhenCurrenciesHaveNotValidData() {
        //given
        final Trade trade = mock(Trade.class);
        when(trade.getCcyPair()).thenReturn("EU");
        //when
        final CurrencyISOCodeValidator currenciesISOTradeValidation = new CurrencyISOCodeValidator(new CurrencyValidationService(), new CurrencyConversionService());
        assertThatThrownBy(() -> currenciesISOTradeValidation.validate(trade))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldReturnInvalidWhenCurrenciesHaveNotValidISOData() {
        //given
        final Trade trade = mock(Trade.class);
        when(trade.getCcyPair()).thenReturn("EURINR");
        //when
        ValidatorResult result = new CurrencyISOCodeValidator(new CurrencyValidationService(), new CurrencyConversionService()).validate(trade);

        //then
        assertThat(result.isValid()).isFalse();
    }
}
