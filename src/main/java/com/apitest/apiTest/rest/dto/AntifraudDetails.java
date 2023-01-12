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
    private String name;

    @JsonProperty("last_name")
    private String lastName;

    private String address;

    @JsonProperty("address_city")
    private String city;

    @JsonProperty("country_code")
    private String country;

    @JsonProperty("phone_number")
    private String phone;

    @JsonProperty("device_finger_print_id")
    private String deviceFingerPrintId;
}
