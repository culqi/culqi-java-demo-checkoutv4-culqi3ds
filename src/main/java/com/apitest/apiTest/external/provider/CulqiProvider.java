package com.apitest.apiTest.external.provider;

import com.apitest.apiTest.rest.dto.CardRequest;
import com.apitest.apiTest.rest.dto.ChargeRequest;
import com.apitest.apiTest.rest.dto.CustomerRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

public interface CulqiProvider {
    ResponseEntity<Object> generateCharge(ChargeRequest chargeRequest) throws Exception;

    ResponseEntity<Object> generateChargeEncrypt(ChargeRequest chargeRequest) throws Exception;
    ResponseEntity<Object> createCustomer(CustomerRequest customerRequest) throws Exception;

    ResponseEntity<Object> createCard(CardRequest cardRequest) throws Exception;
}
