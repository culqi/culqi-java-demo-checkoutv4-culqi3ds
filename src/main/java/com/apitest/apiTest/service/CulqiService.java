package com.apitest.apiTest.service;

import com.apitest.apiTest.rest.dto.CardRequest;
import com.apitest.apiTest.rest.dto.ChargeRequest;
import com.apitest.apiTest.rest.dto.CustomerRequest;
import org.springframework.http.ResponseEntity;

public interface CulqiService {
    ResponseEntity<Object> generateCharge(ChargeRequest chargeRequest);
    ResponseEntity<Object> createCustomer(CustomerRequest customerRequest);
    ResponseEntity<Object> createCard(CardRequest cardRequest);


}
