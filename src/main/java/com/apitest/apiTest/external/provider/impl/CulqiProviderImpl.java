package com.apitest.apiTest.external.provider.impl;

import com.apitest.apiTest.external.provider.CulqiProvider;
import com.apitest.apiTest.rest.dto.CardRequest;
import com.apitest.apiTest.rest.dto.ChargeRequest;
import com.apitest.apiTest.rest.dto.CustomerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class CulqiProviderImpl implements CulqiProvider {

    private String GENERATE_CHARGE = "/v2/charges";
    private String CREATE_CUSTOMERS = "/v2/customers";
    private String CREATE_CARD = "/v2/cards";

    @Value("${app.culqi_api.public-api.url}")
    private String CULQI_API_BASE_URL;

    @Value("${app.culqi.secret-key}")
    private String SECRET_KEY;

    private final RestTemplate restTemplate;

    @Override
    public ResponseEntity<Object> generateCharge(ChargeRequest chargeRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + SECRET_KEY);
        HttpEntity<ChargeRequest> request = new HttpEntity<>(chargeRequest, headers);
        return restTemplate.postForEntity(getURI(GENERATE_CHARGE), request, Object.class);
    }

    @Override
    public ResponseEntity<Object> createCustomer(CustomerRequest customerRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + SECRET_KEY);
        HttpEntity<CustomerRequest> request = new HttpEntity<>(customerRequest, headers);
        return restTemplate.postForEntity(getURI(CREATE_CUSTOMERS), request, Object.class);
    }

    @Override
    public ResponseEntity<Object> createCard(CardRequest cardRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + SECRET_KEY);
        HttpEntity<CardRequest> request = new HttpEntity<>(cardRequest, headers);
        return restTemplate.postForEntity(getURI(CREATE_CARD), request, Object.class);
    }

    private String getURI (String endpoint) {
        return CULQI_API_BASE_URL.concat(endpoint);
    }
}
