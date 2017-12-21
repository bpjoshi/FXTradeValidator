package com.bpjoshi.tradevalidator.validation.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;


/**
 * @author bpjoshi(Bhagwati Prasad)
 */
@RunWith(SpringRunner.class)
@RestClientTest(FixerIOService.class)
public class FixerIOServiceTest {
	@Autowired
    private RestTemplate template;

    @Autowired
    private FixerIOService fixerClient;

    @MockBean
    private CurrencyValidationService currencyISO;

    @MockBean
    private CurrencyConversionService currenciesPairConverter;

    private MockRestServiceServer server;
    
    @Before
    public void setUp() {
        server = MockRestServiceServer.createServer(template);
    }
    
    @Test
    public void shouldReturnValidCurrencyRateForGivenDate() {
        //given
        expectJson("http://api.fixer.io/2017-01-05?base=USD", "{\"base\":\"USD\",\"date\":\"2017-01-05\",\"rates\":{\"AUD\":1.3714,\"BGN\":1.8625,\"BRL\":3.2132,\"CAD\":1.33,\"CHF\":1.0193,\"CNY\":6.8861,\"CZK\":25.732,\"DKK\":7.0794,\"GBP\":0.81364,\"HKD\":7.7546,\"HRK\":7.2155,\"HUF\":293.57,\"IDR\":13361.0,\"ILS\":3.8545,\"INR\":67.935,\"JPY\":116.54,\"KRW\":1191.1,\"MXN\":21.279,\"MYR\":4.485,\"NOK\":8.5911,\"NZD\":1.4367,\"PHP\":49.512,\"PLN\":4.1596,\"RON\":4.2915,\"RUB\":59.473,\"SEK\":9.0792,\"SGD\":1.4357,\"THB\":35.805,\"TRY\":3.6206,\"ZAR\":13.641,\"EUR\":0.95229}}");

        //when
        final boolean result = fixerClient.hasCurrencyRateForDate("USD", LocalDate.of(2017, 1, 5));

        //then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnInvalidCurrencyRateForGivenDate() {
        //given
        expectJson("http://api.fixer.io/2017-01-07?base=USD", "{\"base\":\"USD\",\"date\":\"2017-01-06\",\"rates\":{\"AUD\":1.3714,\"BGN\":1.8625,\"BRL\":3.2132,\"CAD\":1.33,\"CHF\":1.0193,\"CNY\":6.8861,\"CZK\":25.732,\"DKK\":7.0794,\"GBP\":0.81364,\"HKD\":7.7546,\"HRK\":7.2155,\"HUF\":293.57,\"IDR\":13361.0,\"ILS\":3.8545,\"INR\":67.935,\"JPY\":116.54,\"KRW\":1191.1,\"MXN\":21.279,\"MYR\":4.485,\"NOK\":8.5911,\"NZD\":1.4367,\"PHP\":49.512,\"PLN\":4.1596,\"RON\":4.2915,\"RUB\":59.473,\"SEK\":9.0792,\"SGD\":1.4357,\"THB\":35.805,\"TRY\":3.6206,\"ZAR\":13.641,\"EUR\":0.95229}}");

        //when
        final boolean result = fixerClient.hasCurrencyRateForDate("USD", LocalDate.of(2017, 1, 7));

        //then
        assertThat(result).isFalse();
    }

    private void expectJson(String url, String bodyPath) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        this.server.expect(requestTo(url))
                   .andExpect(method(HttpMethod.GET))
                   .andExpect(header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE))
                   .andRespond(withStatus(HttpStatus.OK)
                           .body(bodyPath)
                           .headers(httpHeaders));
    }
}
