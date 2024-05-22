package com.person.management.dtos.person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonEditDTOTest {

    @Test
    public void testPersonEditDTO() {
        String fullName = "Test Name";
        String birthDate = "01/01/2000";

        PersonEditDTO personEditDTO = new PersonEditDTO(fullName, birthDate);

        assertEquals(fullName, personEditDTO.getFullName());
        assertEquals(birthDate, personEditDTO.getBirthDate());
    }
}