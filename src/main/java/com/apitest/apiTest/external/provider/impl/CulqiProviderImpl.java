package com.apitest.apiTest.external.provider.impl;

import com.apitest.apiTest.external.provider.CulqiProvider;
import com.apitest.apiTest.rest.dto.CardRequest;
import com.apitest.apiTest.rest.dto.ChargeRequest;
import com.apitest.apiTest.rest.dto.CustomerRequest;
import com.culqi.util.CurrencyCode;
import com.culqi.util.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.culqi.Culqi;

import java.util.*;

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

    public Culqi init(){
        Culqi culqi = new Culqi();
        culqi.public_key = "pk_live_da33560a681ff246";
        culqi.secret_key = "sk_live_34a07dcb6d4c7e39";
        return culqi;
    }
    protected Map<String, Object> jsonCharge(String source_id, ChargeRequest chargeRequest) throws Exception {
        Map<String, Object> charge = new HashMap<String, Object>();
        Map<String, Object> metadata = new HashMap<String, Object>();
        metadata.put("order_id", "1234");
        charge.put("amount", chargeRequest.getAmount());
        charge.put("capture", true);
        charge.put("currency_code", chargeRequest.getCurrency());
        charge.put("description", "Venta de prueba");
        charge.put("email", chargeRequest.getMail());
        charge.put("installments", 0);
        charge.put("metadata", metadata);
        charge.put("source_id", source_id);
        return charge;
    }
    @Override
    public ResponseEntity<Object> generateCharge(ChargeRequest chargeRequest) throws Exception {
        Map<String, Object> resp = init().charge.create(jsonCharge(chargeRequest.getSource(), chargeRequest));
        System.out.println(resp);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(resp);
        return new ResponseEntity<>(json, headers, HttpStatus.OK);
        //return (ResponseEntity)resp;
    }

    public ResponseEntity<Object> generateChargeEncrypt(ChargeRequest chargeRequest) throws Exception {
        String rsaPublicKey="-----BEGIN PUBLIC KEY-----\n"
                + "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDADka0Pt4SuWlHRA6kcJIwDde\n"
                + "o67OYBEgQDEelmmixs9AlB/1bv446XOOE8eTJSridll2ZAn2nze7Gl2vQs0yW+4A\n"
                + "XmszJwugM0lxTDiPdTXdbrA4VXiXDG29VLQCAxt1+/c7bE84hMS6cymWgEjYoa6I\n"
                + "xX8u0ncLyiRUdZC2cwIDAQAB\n"
                + "-----END PUBLIC KEY-----";
        String rsaId = "5243bad7-1d88-49c0-9699-f8ae156da58f";
        Map<String, Object> resp = init().charge.create(jsonCharge(chargeRequest.getSource(), chargeRequest), rsaPublicKey, rsaId);
        System.out.println(resp);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(resp);
        return new ResponseEntity<>(json, headers, HttpStatus.OK);
        //return (ResponseEntity)resp;
    }
    protected Map<String, Object> jsonCustomer(CustomerRequest customerRequest) throws Exception {
        Map<String, Object> customer = new HashMap<String, Object>();
        customer.put("address", customerRequest.getAddress());
        customer.put("address_city", customerRequest.getAddressCity());
        customer.put("country_code", customerRequest.getCountryCode());
        customer.put("email", customerRequest.getEmail());
        customer.put("first_name",customerRequest.getFirstName());
        customer.put("last_name",customerRequest.getLastName());
        customer.put("phone_number", customerRequest.getPhoneNumber());
        return customer;
    }
    @Override
    public ResponseEntity<Object> createCustomer(CustomerRequest customerRequest) throws Exception {
        Map<String, Object> resp = init().customer.create(jsonCustomer(customerRequest));
        System.out.println(resp);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(resp);
        return new ResponseEntity<>(json, headers, HttpStatus.OK);
    }
    protected Map<String, Object> jsonCard(String customerId, String tokenId) throws Exception {
        Map<String, Object> card = new HashMap<String, Object>();
        card.put("customer_id", customerId);
        card.put("token_id", tokenId);
        return card;
    }
    @Override
    public ResponseEntity<Object> createCard(CardRequest cardRequest) throws Exception {
        Map<String, Object> resp = init().card.create(jsonCard(cardRequest.getCustomerId(),cardRequest.getTokenId()));;
        System.out.println(resp);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(resp);
        return new ResponseEntity<>(json, headers, HttpStatus.OK);
    }

    private String getURI (String endpoint) {
        return CULQI_API_BASE_URL.concat(endpoint);
    }
}
