package com.person.management.dtos.address;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressEditDTOTest {

    @Test
    public void testAddressEditDTO() {
        String street = "Test Street";
        String postalCode = "12345";
        String number = "100";
        String city = "Test City";
        String state = "Test State";
        Boolean isPrimary = true;

        AddressEditDTO addressEditDTO = new AddressEditDTO(street, postalCode, number, city, state, isPrimary);

        assertEquals(street, addressEditDTO.getStreet());
        assertEquals(postalCode, addressEditDTO.getPostalCode());
        assertEquals(number, addressEditDTO.getNumber());
        assertEquals(city, addressEditDTO.getCity());
        assertEquals(state, addressEditDTO.getState());
        assertEquals(isPrimary, addressEditDTO.getIsPrimary());
    }
}