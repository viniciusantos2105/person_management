package com.person.management.services;

import com.person.management.adapters.Adapter;
import com.person.management.dtos.person.PersonCreateDTO;
import com.person.management.dtos.person.PersonEditDTO;
import com.person.management.dtos.person.PersonViewDTO;
import com.person.management.entities.Person;
import com.person.management.models.PersonModel;
import com.person.management.repositories.person.PersonRepository;
import com.person.management.repositories.person.PersonRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final Adapter adapter;
    private final PersonAddressService personAddressService;
    private final PersonRepository personRepository;
    private final PersonRepositoryImpl personRepositoryImpl;

    public PersonService(PersonRepository personRepository, PersonRepositoryImpl personRepositoryImpl, PersonAddressService personAddressService, Adapter adapter) {
        this.personRepository = personRepository;
        this.personRepositoryImpl = personRepositoryImpl;
        this.personAddressService = personAddressService;
        this.adapter = adapter;
    }

    public PersonViewDTO findById(Long personId) {
        return adapter.map(personRepositoryImpl.findPersonById(personId), PersonViewDTO.class);
    }

    public List<PersonViewDTO> listAll() {
        List<PersonModel> list = personRepositoryImpl.findAll();
        return list.stream()
                .map(person -> adapter.map(person, PersonViewDTO.class))
                .collect(Collectors.toList());
    }

    public PersonViewDTO createPerson(PersonCreateDTO personCreateDTO) {
        Person person = adapter.map(personCreateDTO, Person.class);
        PersonModel personModel = adapter.map(person, PersonModel.class);
        personRepository.save(personModel);
        person = adapter.map(personModel, Person.class);
        personAddressService.createAddress(personCreateDTO.getAddress(), person.getPersonId());
        return adapter.map(person, PersonViewDTO.class);
    }

    public PersonViewDTO editPerson(Long personId, PersonEditDTO personEditDTO) {
        Person persistedPerson = adapter.map(personRepositoryImpl.findPersonById(personId), Person.class);
        Person editedPerson = adapter.map(personEditDTO, Person.class);
        adapter.updateTargetFromSource(editedPerson, persistedPerson);
        PersonModel personModel = adapter.map(persistedPerson, PersonModel.class);
        personRepository.save(personModel);
        return adapter.map(persistedPerson, PersonViewDTO.class);
    }

    public void deletePerson(Long personId) {
        personRepositoryImpl.deletePerson(personId);
    }
}
