package com.bpjoshi.tradevalidator.validation.spot;

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
public class SpotValidatorTest {

    @Test
    public void shouldReturnValidWhenValueDateIsAfterTradeDateAndDifferenceBetweenValueAndTradeIsTwoDays() {
        //given
        final Trade spot = mock(Trade.class);
        when(spot.getValueDate()).thenReturn(LocalDate.of(2017, 12, 13));
        when(spot.getTradeDate()).thenReturn(LocalDate.of(2017, 12, 11));
        //when
        final ValidatorResult result = new SpotValidator().validate(spot);

        //then
        assertThat(result.isValid()).isTrue();
    }

    @Test
    public void shouldReturnNotValidWhenValueDateIsAfterTradeDateAndDifferenceBetweenValueAndTradeIsThreeDays() {
        //given
        final Trade spot = mock(Trade.class);
        when(spot.getValueDate()).thenReturn(LocalDate.of(2017, 12, 13));
        when(spot.getTradeDate()).thenReturn(LocalDate.of(2017, 12, 10));
        //when
        final ValidatorResult result = new SpotValidator().validate(spot);

        //then
        assertThat(result.isValid()).isFalse();
    }

}
