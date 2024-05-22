package com.person.management.services;

import com.person.management.adapters.Adapter;
import com.person.management.dtos.person.PersonCreateDTO;
import com.person.management.dtos.person.PersonEditDTO;
import com.person.management.dtos.person.PersonViewDTO;
import com.person.management.entities.Person;
import com.person.management.models.PersonModel;
import com.person.management.repositories.person.PersonRepository;
import com.person.management.repositories.person.PersonRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonRepositoryImpl personRepositoryImpl;

    @Mock
    private PersonAddressService personAddressService;

    @Mock
    private Adapter adapter;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById() {
        Long personId = 1L;
        PersonModel personModel = new PersonModel();
        PersonViewDTO expectedPersonViewDTO = new PersonViewDTO();

        when(personRepositoryImpl.findPersonById(personId)).thenReturn(personModel);
        when(adapter.map(personModel, PersonViewDTO.class)).thenReturn(expectedPersonViewDTO);

        PersonViewDTO result = personService.findById(personId);

        verify(personRepositoryImpl, times(1)).findPersonById(personId);
        verify(adapter, times(1)).map(personModel, PersonViewDTO.class);
        assertEquals(expectedPersonViewDTO, result);
    }

    @Test
    public void testListAll() {
        List<PersonModel> personModels = Arrays.asList(new PersonModel(), new PersonModel());
        PersonViewDTO expectedPersonViewDTO = new PersonViewDTO();

        when(personRepositoryImpl.findAll()).thenReturn(personModels);
        when(adapter.map(any(PersonModel.class), eq(PersonViewDTO.class))).thenReturn(expectedPersonViewDTO);

        List<PersonViewDTO> result = personService.listAll();

        verify(personRepositoryImpl, times(1)).findAll();
        verify(adapter, times(personModels.size())).map(any(PersonModel.class), eq(PersonViewDTO.class));
        assertEquals(personModels.size(), result.size());
        result.forEach(personViewDTO -> assertEquals(expectedPersonViewDTO, personViewDTO));
    }

    @Test
    public void testCreatePerson() {
        PersonCreateDTO personCreateDTO = new PersonCreateDTO();
        Person person = new Person();
        PersonModel personModel = new PersonModel();
        PersonViewDTO expectedPersonViewDTO = new PersonViewDTO();

        when(adapter.map(personCreateDTO, Person.class)).thenReturn(person);
        when(adapter.map(person, PersonModel.class)).thenReturn(personModel);
        when(adapter.map(personModel, Person.class)).thenReturn(person);
        when(adapter.map(person, PersonViewDTO.class)).thenReturn(expectedPersonViewDTO);

        PersonViewDTO result = personService.createPerson(personCreateDTO);

        verify(adapter, times(1)).map(personCreateDTO, Person.class);
        verify(adapter, times(1)).map(person, PersonModel.class);
        verify(personRepository, times(1)).save(personModel);
        verify(adapter, times(1)).map(personModel, Person.class);
        verify(personAddressService, times(1)).createAddress(personCreateDTO.getAddress(), person.getPersonId());
        verify(adapter, times(1)).map(person, PersonViewDTO.class);
        assertEquals(expectedPersonViewDTO, result);
    }

    @Test
    public void testEditPerson() {
        Long personId = 1L;
        PersonEditDTO personEditDTO = new PersonEditDTO();
        Person persistedPerson = new Person();
        Person editedPerson = new Person();
        PersonModel personModel = new PersonModel();
        PersonViewDTO expectedPersonViewDTO = new PersonViewDTO();

        when(personRepositoryImpl.findPersonById(personId)).thenReturn(personModel);
        when(adapter.map(personModel, Person.class)).thenReturn(persistedPerson);
        when(adapter.map(personEditDTO, Person.class)).thenReturn(editedPerson);
        when(adapter.map(persistedPerson, PersonModel.class)).thenReturn(personModel);
        when(adapter.map(persistedPerson, PersonViewDTO.class)).thenReturn(expectedPersonViewDTO);

        PersonViewDTO result = personService.editPerson(personId, personEditDTO);

        verify(personRepositoryImpl, times(1)).findPersonById(personId);
        verify(adapter, times(1)).map(personModel, Person.class);
        verify(adapter, times(1)).map(personEditDTO, Person.class);
        verify(adapter, times(1)).updateTargetFromSource(editedPerson, persistedPerson);
        verify(adapter, times(1)).map(persistedPerson, PersonModel.class);
        verify(personRepository, times(1)).save(personModel);
        verify(adapter, times(1)).map(persistedPerson, PersonViewDTO.class);
        assertEquals(expectedPersonViewDTO, result);
    }

    @Test
    public void testDeletePerson() {
        Long personId = 1L;

        personService.deletePerson(personId);
        verify(personRepositoryImpl, times(1)).deletePerson(personId);
    }
}
