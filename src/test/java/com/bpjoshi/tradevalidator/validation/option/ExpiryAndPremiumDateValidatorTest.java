package com.bpjoshi.tradevalidator.validation.option;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Test;

import com.bpjoshi.tradevalidator.model.Trade;
import com.bpjoshi.tradevalidator.validation.ValidatorResult;

/**
 * @author bpjoshi(Bhagwati Prasad)
 */
public class ExpiryAndPremiumDateValidatorTest {

    @Test
    public void shouldReturnValidResultWhenExpiryDateAndPremiumDataIsBeforeDeliveryDate() {
        //given
        final Trade option = mock(Trade.class);
        when(option.getExpiryDate()).thenReturn(LocalDate.of(2017, 12, 10));
        when(option.getPremiumDate()).thenReturn(LocalDate.of(2017, 12, 11));
        when(option.getDeliveryDate()).thenReturn(LocalDate.of(2017, 12, 12));

        //when
        final ValidatorResult result = new ExpiryAndPremiumDateValidator().validate(option);

        //then
        assertThat(result.isValid()).isTrue();

    }

    @Test
    public void shouldReturnInvalidResultWhenExpiryDateIsNotBeforeDeliveryDate() {
        //given
        final Trade option = mock(Trade.class);
        when(option.getExpiryDate()).thenReturn(LocalDate.of(2017, 12, 20));
        when(option.getPremiumDate()).thenReturn(LocalDate.of(2017, 12, 11));
        when(option.getDeliveryDate()).thenReturn(LocalDate.of(2017, 12, 12));

        //when
        final ValidatorResult result = new ExpiryAndPremiumDateValidator().validate(option);

        //then
        assertThat(result.isValid()).isFalse();

    }

    @Test
    public void shouldReturnInvalidResultWhenPremiumDateIsNotBeforeDeliveryDate() {
        //given
        final Trade option = mock(Trade.class);
        when(option.getExpiryDate()).thenReturn(LocalDate.of(2017, 12, 10));
        when(option.getPremiumDate()).thenReturn(LocalDate.of(2017, 12, 20));
        when(option.getDeliveryDate()).thenReturn(LocalDate.of(2017, 12, 12));

        //when
        final ValidatorResult result = new ExpiryAndPremiumDateValidator().validate(option);

        //then
        assertThat(result.isValid()).isFalse();

    }
}
