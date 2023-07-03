package com.apitest.apiTest.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@ToString
public class AntifraudDetails {
    @JsonProperty("first_name")
    private String first_name;

    @JsonProperty("last_name")
    private String last_name;

    private String address;

    @JsonProperty("address_city")
    private String address_city;

    @JsonProperty("country_code")
    private String country_code;

    @JsonProperty("phone_number")
    private String phone_number;

    @JsonProperty("device_finger_print_id")
    private String device_finger_print_id;
}
