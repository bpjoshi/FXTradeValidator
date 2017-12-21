package com.bpjoshi.tradevalidator.validation.trade;

import org.junit.Test;

import com.bpjoshi.tradevalidator.model.Trade;
import com.bpjoshi.tradevalidator.validation.ValidatorResult;
import com.bpjoshi.tradevalidator.validation.service.CurrencyConversionService;
import com.bpjoshi.tradevalidator.validation.service.FixerIOService;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
/**
 * @author bpjoshi(Bhagwati Prasad)
 */
public class ValueDateOnHolidayValidatorTest {

    @Test
    public void shouldReturnValidWhenDatesForCurrenciesAreNotWeekendOrNonWorking() {
        //given
        final Trade trade = mock(Trade.class);
        final FixerIOService fixer = mock(FixerIOService.class);
        when(trade.getCcyPair()).thenReturn("EURUSD");
        when(trade.getValueDate()).thenReturn(LocalDate.of(2017, 7, 3));
        when(fixer.hasCurrencyRateForDate(any(), any())).thenReturn(true);

        //when
        final ValidatorResult result = new ValueDateOnHolidayValidator(fixer, new CurrencyConversionService()).validate(trade);

        //then
        assertThat(result.isValid()).isTrue();
    }

    @Test
    public void shouldReturnInvalidWhenDatesForCurrenciesAreWeekendOrNonWorking() {
        //given
        final Trade forward = mock(Trade.class);
        final FixerIOService fixer = mock(FixerIOService.class);
        when(forward.getCcyPair()).thenReturn("EURUSD");
        when(forward.getValueDate()).thenReturn(LocalDate.of(2017, 7, 3));
        when(fixer.hasCurrencyRateForDate(any(), any())).thenReturn(false);

        //when
        final ValidatorResult result = new ValueDateOnHolidayValidator(fixer, new CurrencyConversionService()).validate(forward);

        //then
        assertThat(result.isValid()).isFalse();
    }
}