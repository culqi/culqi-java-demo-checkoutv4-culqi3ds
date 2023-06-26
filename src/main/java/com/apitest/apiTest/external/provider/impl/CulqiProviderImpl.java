package com.apitest.apiTest.external.provider.impl;

import com.apitest.apiTest.external.provider.CulqiProvider;
import com.apitest.apiTest.rest.dto.CardRequest;
import com.apitest.apiTest.rest.dto.ChargeRequest;
import com.apitest.apiTest.rest.dto.CustomerRequest;
import com.apitest.apiTest.rest.dto.OrderRequest;
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
        culqi.public_key = "pk_live_53d22e51b61a43d1";
        culqi.secret_key = "sk_live_LoSAl6rqTInlzPSJ";
        return culqi;
    }
    protected Map<String, Object> jsonCharge(String source_id, ChargeRequest chargeRequest) throws Exception {
        Map<String, Object> charge = new HashMap<String, Object>();
        Map<String, Object> metadata = new HashMap<String, Object>();
        Map<String, Object> authentication_3DS = new HashMap<String, Object>();
        metadata.put("order_id", "1234");
        charge.put("amount", chargeRequest.getAmount());
        charge.put("capture", true);
        charge.put("currency_code", chargeRequest.getCurrency());
        charge.put("description", "Venta de prueba");
        charge.put("email", chargeRequest.getMail());
        charge.put("installments", 0);
        charge.put("metadata", metadata);
        charge.put("source_id", source_id);
        charge.put("authentication_3DS", authentication_3DS);
        return charge;
    }
    protected Map<String, Object> jsonOrder(OrderRequest orderRequest) throws Exception {
        Map<String, Object> charge = new HashMap<String, Object>();
        charge.put("amount", orderRequest.getAmount());
        charge.put("currency_code", orderRequest.getCurrency());
        charge.put("description", orderRequest.getDescription());
        charge.put("order_number", orderRequest.getOrder_number());
        charge.put("client_details", orderRequest.getClientDetailsRequest());
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
    @Override
    public ResponseEntity<Object> generateOrder(OrderRequest orderRequest) throws Exception {
        Map<String, Object> resp = init().order.create(jsonOrder(orderRequest));
        System.out.println(resp);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(resp);
        return new ResponseEntity<>(json, headers, HttpStatus.OK);
        //return (ResponseEntity)resp;
    }
    public ResponseEntity<Object> generateChargeEncrypt(ChargeRequest chargeRequest) throws Exception {
        String rsaPublicKey = "-----BEGIN PUBLIC KEY-----'+\n" +
                "'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC9hD00BnivDj73/1SKZw5AyQvw'+\n" +
                "'FpvR/DKzW7Jqg1iwFWXrX6k1r57qZJH2wF1tZ9T3wTyw1we6BYgwPNRVC1IXe+E8'+\n" +
                "'B6xAWG8ta7BCZK/a6IFL+l9Q9BhkHBeVTD7qGEfCjhnB7QtyrTQwmytoNBKk1Tl7'+\n" +
                "'kbz8NO7jeiUxkZm75wIDAQAB'+\n" +
                "'-----END PUBLIC KEY----";
        String rsaId = "2ab335ad-c40d-4375-8dad-3ea315de23b0";
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
