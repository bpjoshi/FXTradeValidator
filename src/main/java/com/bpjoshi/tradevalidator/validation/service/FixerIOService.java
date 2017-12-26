package com.bpjoshi.tradevalidator.validation.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bpjoshi.tradevalidator.config.ClassInfo;
import com.bpjoshi.tradevalidator.model.Currency;
/**
 * @author bpjoshi(Bhagwati Prasad)
 */
@Service
@ClassInfo(summary = "A rest client that calls fixer.io api for validating some part of the trade information.")
public class FixerIOService {

    private final RestTemplate restTemplate;

    public FixerIOService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean hasCurrencyRateForDate(final String currency, final LocalDate date) {
        final String url = String.format("http://api.fixer.io/%s?base=%s", date, currency);
        final Currency currencyInfo = invoke(createRequestEntity(url), Currency.class).getBody();
        return currencyInfo.getDate().isEqual(date);
    }

    private <T> ResponseEntity<T> invoke(final RequestEntity<?> request, Class<T> type) {
        return this.restTemplate.exchange(request, type);
    }

    private RequestEntity<?> createRequestEntity(final String url) {
        try {
            return RequestEntity.get(new URI(url))
                                .accept(MediaType.APPLICATION_JSON).build();
        } catch (URISyntaxException ex) {
            throw new IllegalStateException("Invalid URL " + url, ex);
        }
    }
}
