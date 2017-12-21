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
public class AmericanStyleExcerciseStartDateValidatorTest {

    @Test
    public void shouldReturnValidResultWhenExcerciseStartDateIsBeforeExpiryDateAndAfterTradeDate() {
        //given
        final Trade option = mock(Trade.class);
        when(option.getStyle()).thenReturn("AMERICAN");
        when(option.getExcerciseStartDate()).thenReturn(LocalDate.of(2017, 12, 18));
        when(option.getExpiryDate()).thenReturn(LocalDate.of(2017, 12, 19));
        when(option.getTradeDate()).thenReturn(LocalDate.of(2017, 12, 15));
        //when
        final ValidatorResult result = new AmericanStyleExcerciseStartDateValidator().validate(option);
        //then
        assertThat(result.isValid()).isTrue();
    }

    @Test
    public void shouldReturnInvalidResultWhenExcerciseStartDateIsAfterExpiryDateAndAfterTradeDate() {
        //given
        final Trade option = mock(Trade.class);
        when(option.getStyle()).thenReturn("AMERICAN");
        when(option.getExcerciseStartDate()).thenReturn(LocalDate.of(2017, 12, 20));
        when(option.getExpiryDate()).thenReturn(LocalDate.of(2017, 12, 19));
        when(option.getTradeDate()).thenReturn(LocalDate.of(2017, 12, 15));
        //when

        final ValidatorResult result = new AmericanStyleExcerciseStartDateValidator().validate(option);

        //then
        assertThat(result.isValid()).isFalse();
    }

    @Test
    public void shouldReturnInvalidResultWhenExcerciseStartDateIsAfterExpiryDateAndBeforeTradeDate() {
        //given
        final Trade option = mock(Trade.class);
        when(option.getStyle()).thenReturn("AMERICAN");
        when(option.getExcerciseStartDate()).thenReturn(LocalDate.of(2017, 12, 21));
        when(option.getExpiryDate()).thenReturn(LocalDate.of(2017, 12, 19));
        when(option.getTradeDate()).thenReturn(LocalDate.of(2017, 12, 22));
        //when

        final ValidatorResult result = new AmericanStyleExcerciseStartDateValidator().validate(option);

        //then
        assertThat(result.isValid()).isFalse();
    }


}
