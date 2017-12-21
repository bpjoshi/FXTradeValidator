package com.bpjoshi.tradevalidator.validation.trade;

import org.junit.Test;

import com.bpjoshi.tradevalidator.model.Trade;
import com.bpjoshi.tradevalidator.validation.ValidatorResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
/**
 * @author bpjoshi(Bhagwati Prasad)
 */
public class SupportedPartiesValidatorTest {


    @Test
    public void shouldReturnNotValidWhenCustomerIsNotSupported() {
        //given
        final Trade trade = mock(Trade.class);
        when(trade.getCustomer()).thenReturn("dummyCustomer");

        //when
        ValidatorResult result = new SupportedPartiesValidator().validate(trade);

        //then
        assertThat(result.isValid()).isFalse();
    }

    @Test
    public void shouldReturnValidWhenCustomerIsSupported() {
        //given
        final Trade trade = mock(Trade.class);
        when(trade.getCustomer()).thenReturn("PLUTO2");

        //when
        ValidatorResult result = new SupportedPartiesValidator().validate(trade);

        //then
        assertThat(result.isValid()).isTrue();
    }
}