package com.person.management.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private Long addressId;
    private String street;
    private String postalCode;
    private String number;
    private String city;
    private String state;
    private Boolean isPrimary;
    private Person person;
}
