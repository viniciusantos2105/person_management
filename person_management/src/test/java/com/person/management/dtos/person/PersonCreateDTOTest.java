package com.person.management.dtos.person;

import com.person.management.dtos.address.AddressCreateDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonCreateDTOTest {

    @Test
    public void testPersonCreateDTO() {
        String fullName = "Test Name";
        String birthDate = "01/01/2000";
        AddressCreateDTO address = new AddressCreateDTO("Test Street", "12345", "100", "Test City", "Test State", true);

        // Act
        PersonCreateDTO personCreateDTO = new PersonCreateDTO(fullName, birthDate, address);

        // Assert
        assertEquals(fullName, personCreateDTO.getFullName());
        assertEquals(birthDate, personCreateDTO.getBirthDate());
        assertEquals(address, personCreateDTO.getAddress());
    }
}