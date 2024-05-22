package com.person.management.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AddressModelTest {

    @Test
    public void testAddressModel() {
        Long addressId = 1L;
        String street = "Test Street";
        String postalCode = "12345";
        String number = "100";
        String city = "Test City";
        String state = "Test State";
        Boolean isPrimary = true;
        PersonModel person = null;

        AddressModel addressModel = new AddressModel(addressId, street, postalCode, number, city, state, isPrimary, person);

        assertEquals(addressId, addressModel.getAddressId());
        assertEquals(street, addressModel.getStreet());
        assertEquals(postalCode, addressModel.getPostalCode());
        assertEquals(number, addressModel.getNumber());
        assertEquals(city, addressModel.getCity());
        assertEquals(state, addressModel.getState());
        assertEquals(isPrimary, addressModel.getIsPrimary());
        assertNull(addressModel.getPerson());
    }
}