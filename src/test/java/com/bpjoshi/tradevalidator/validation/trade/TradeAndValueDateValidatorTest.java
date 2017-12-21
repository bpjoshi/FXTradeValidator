package com.bpjoshi.tradevalidator.validation.trade;

import org.junit.Test;

import com.bpjoshi.tradevalidator.model.Trade;
import com.bpjoshi.tradevalidator.validation.ValidatorResult;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
/**
 * @author bpjoshi(Bhagwati Prasad)
 */
public class TradeAndValueDateValidatorTest {

    @Test
    public void shouldReturnDatesAreValidWhenValueDateIsAfterTradeDate() {
        //given
        final Trade trade = mock(Trade.class);
        when(trade.getValueDate()).thenReturn(LocalDate.of(2017, 1, 17));
        when(trade.getTradeDate()).thenReturn(LocalDate.of(2017, 1, 15));

        //when
        final ValidatorResult result = new TradeAndValueDateValidator().validate(trade);

        //then
        assertThat(result.isValid()).isTrue();
    }

    @Test
    public void shouldReturnDatesAreInvalidWhenValueDateIsBeforeTradeDate() {
        //given
        final Trade trade = mock(Trade.class);
        when(trade.getValueDate()).thenReturn(LocalDate.of(2017, 1, 10));
        when(trade.getTradeDate()).thenReturn(LocalDate.of(2017, 1, 15));

        //when
        final ValidatorResult result = new TradeAndValueDateValidator().validate(trade);

        //then
        assertThat(result.isValid()).isFalse();
    }
}