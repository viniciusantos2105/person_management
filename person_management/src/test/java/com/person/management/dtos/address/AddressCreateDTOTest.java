package com.person.management.dtos.address;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressCreateDTOTest {

    @Test
    public void testAddressCreateDTO() {
        String street = "Test Street";
        String postalCode = "12345";
        String number = "100";
        String city = "Test City";
        String state = "Test State";
        Boolean isPrimary = true;

        AddressCreateDTO addressCreateDTO = new AddressCreateDTO(street, postalCode, number, city, state, isPrimary);

        assertEquals(street, addressCreateDTO.getStreet());
        assertEquals(postalCode, addressCreateDTO.getPostalCode());
        assertEquals(number, addressCreateDTO.getNumber());
        assertEquals(city, addressCreateDTO.getCity());
        assertEquals(state, addressCreateDTO.getState());
        assertEquals(isPrimary, addressCreateDTO.getIsPrimary());
    }
}
