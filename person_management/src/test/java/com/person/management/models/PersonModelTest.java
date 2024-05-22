package com.person.management.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonModelTest {

    @Test
    public void testPersonModel() {
        Long personId = 1L;
        String fullName = "Test Name";
        Date birthDate = new Date();
        List<AddressModel> addressList = new ArrayList<>();
        addressList.add(new AddressModel(1L, "Test Street", "12345", "100", "Test City", "Test State", true, null));

        PersonModel personModel = new PersonModel(personId, fullName, birthDate, addressList);

        assertEquals(personId, personModel.getPersonId());
        assertEquals(fullName, personModel.getFullName());
        assertEquals(birthDate, personModel.getBirthDate());
        assertEquals(addressList, personModel.getAddressList());
    }
}