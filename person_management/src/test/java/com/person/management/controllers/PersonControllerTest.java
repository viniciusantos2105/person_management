package com.person.management.controllers;

import com.person.management.dtos.person.PersonCreateDTO;
import com.person.management.dtos.person.PersonEditDTO;
import com.person.management.dtos.person.PersonViewDTO;
import com.person.management.services.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PersonControllerTest {

    // ... outros métodos de teste e configurações

    @InjectMocks
    private PersonController personController;
    @Mock
    private PersonService personService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById() {
        Long personId = 1L;
        PersonViewDTO expectedPerson = new PersonViewDTO();
        when(personService.findById(personId)).thenReturn(expectedPerson);

        ResponseEntity<PersonViewDTO> response = personController.findById(personId);

        verify(personService, times(1)).findById(personId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPerson, response.getBody());
    }


    @Test
    public void testListAll() {
        PersonViewDTO personViewDTO = new PersonViewDTO();
        List<PersonViewDTO> expectedPersons = Collections.singletonList(personViewDTO);
        when(personService.listAll()).thenReturn(expectedPersons);

        ResponseEntity<List<PersonViewDTO>> response = personController.listAll();

        verify(personService, times(1)).listAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPersons, response.getBody());
    }

    @Test
    public void testCreatePerson() {
        PersonCreateDTO personCreateDTO = new PersonCreateDTO();
        PersonViewDTO expectedPerson = new PersonViewDTO();
        when(personService.createPerson(personCreateDTO)).thenReturn(expectedPerson);

        ResponseEntity<PersonViewDTO> response = personController.createPerson(personCreateDTO);

        verify(personService, times(1)).createPerson(personCreateDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedPerson, response.getBody());
    }


    @Test
    public void testEditPerson() {
        Long personId = 1L;
        PersonEditDTO personEditDTO = new PersonEditDTO();
        PersonViewDTO expectedPerson = new PersonViewDTO();
        when(personService.editPerson(personId, personEditDTO)).thenReturn(expectedPerson);

        ResponseEntity<PersonViewDTO> response = personController.editPerson(personId, personEditDTO);

        verify(personService, times(1)).editPerson(personId, personEditDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPerson, response.getBody());
    }


    @Test
    public void testDeletePerson() {
        Long personId = 1L;
        doNothing().when(personService).deletePerson(personId);

        ResponseEntity<Void> response = personController.deletePerson(personId);

        verify(personService, times(1)).deletePerson(personId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}