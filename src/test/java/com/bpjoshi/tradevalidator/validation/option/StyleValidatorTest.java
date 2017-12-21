package com.bpjoshi.tradevalidator.validation.option;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.bpjoshi.tradevalidator.model.Trade;
import com.bpjoshi.tradevalidator.validation.ValidatorResult;

/**
 * @author bpjoshi(Bhagwati Prasad)
 */
public class StyleValidatorTest {


    @Test
    public void shouldReturnValidWhenStyleIsEuropean() {
        //given
        final Trade option = mock(Trade.class);
        when(option.getStyle()).thenReturn("EUROPEAN");

        //when
        final ValidatorResult result = new StyleValidator().validate(option);

        //then
        assertThat(result.isValid()).isTrue();
    }

    @Test
    public void shouldReturnInvalidWhenStyleIsNotSupported() {
        //given
        final Trade option = mock(Trade.class);
        when(option.getStyle()).thenReturn("INDIAN");

        //when
        final ValidatorResult result = new StyleValidator().validate(option);

        //then
        assertThat(result.isValid()).isFalse();
    }
}
