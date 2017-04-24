package com.sdcuike.practice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Address {
    private Long addrId;

    private String street;

    private String city;

    private String state;

    private String zip;

    private String country;
}