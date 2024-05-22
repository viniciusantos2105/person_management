package com.person.management.dtos.address;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressViewDTOTest {

    @Test
    public void testAddressViewDTO() {
        Long addressId = 1L;
        String street = "Test Street";
        String postalCode = "12345";
        String number = "100";
        String city = "Test City";
        String state = "Test State";
        Boolean isPrimary = true;

        AddressViewDTO addressViewDTO = new AddressViewDTO(addressId, street, postalCode, number, city, state, isPrimary);

        assertEquals(addressId, addressViewDTO.getAddressId());
        assertEquals(street, addressViewDTO.getStreet());
        assertEquals(postalCode, addressViewDTO.getPostalCode());
        assertEquals(number, addressViewDTO.getNumber());
        assertEquals(city, addressViewDTO.getCity());
        assertEquals(state, addressViewDTO.getState());
        assertEquals(isPrimary, addressViewDTO.getIsPrimary());
    }
}