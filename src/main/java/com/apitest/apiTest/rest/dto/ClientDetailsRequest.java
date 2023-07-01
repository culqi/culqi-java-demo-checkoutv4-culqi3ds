package com.apitest.apiTest.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;

@Builder
@Getter
@Setter
@AllArgsConstructor
@ToString
public class ClientDetailsRequest {
    @JsonProperty("first_name")
    private String first_name;

    @JsonProperty("last_name")
    private String last_name;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty("phone_number")
    private String phone_number;

}
