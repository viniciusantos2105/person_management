package com.person.management.dtos.address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressViewDTO {

    private Long addressId;
    private String street;
    private String postalCode;
    private String number;
    private String city;
    private String state;
    private Boolean isPrimary;
}
