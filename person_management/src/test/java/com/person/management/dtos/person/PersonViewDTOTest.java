package com.person.management.dtos.person;

import com.person.management.dtos.address.AddressViewDTO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonViewDTOTest {

    @Test
    public void testPersonViewDTO() {
        Long personId = 1L;
        String fullName = "Test Name";
        String birthDate = "01/01/2000";
        List<AddressViewDTO> addresses = new ArrayList<>();
        addresses.add(new AddressViewDTO(1L, "Test Street", "12345", "100", "Test City", "Test State", true));

        PersonViewDTO personViewDTO = new PersonViewDTO(personId, fullName, birthDate, addresses);

        assertEquals(personId, personViewDTO.getPersonId());
        assertEquals(fullName, personViewDTO.getFullName());
        assertEquals(birthDate, personViewDTO.getBirthDate());
        assertEquals(addresses, personViewDTO.getAddresses());
    }
}