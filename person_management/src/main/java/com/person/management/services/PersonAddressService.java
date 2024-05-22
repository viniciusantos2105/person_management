package com.person.management.services;

import com.person.management.adapters.Adapter;
import com.person.management.dtos.address.AddressCreateDTO;
import com.person.management.dtos.address.AddressViewDTO;
import com.person.management.entities.Address;
import com.person.management.entities.Person;
import com.person.management.models.AddressModel;
import com.person.management.models.PersonModel;
import com.person.management.repositories.address.AddressRepository;
import com.person.management.repositories.person.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonAddressService {

    private final Adapter adapter;
    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;

    public PersonAddressService(PersonRepository personRepository, AddressRepository addressRepository, Adapter adapter) {
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
        this.adapter = adapter;
    }

    public AddressViewDTO createAddress(AddressCreateDTO addressCreateDTO, Long personId) {
        PersonModel personModel = personRepository.findById(personId).orElseThrow(() -> new RuntimeException("Person not found"));
        Person person = adapter.map(personModel, Person.class);
        Address address = adapter.map(addressCreateDTO, Address.class);
        address.setPerson(person);
        isPrimary(person, address);
        AddressModel addressModel = adapter.map(address, AddressModel.class);
        addressRepository.save(addressModel);
        return adapter.map(addressModel, AddressViewDTO.class);
    }

    public void isPrimary(Person person, Address newAddress) {
        if (newAddress.getIsPrimary()) {
            setAllAddressesToNotPrimary(person);
            newAddress.setIsPrimary(true);
        } else {
            newAddress.setIsPrimary(person.getAddresses().isEmpty());
        }
        person.getAddresses().add(newAddress);
        personRepository.save(adapter.map(person, PersonModel.class));
    }

    void setAllAddressesToNotPrimary(Person person) {
        person.getAddresses().forEach(a -> {
            a.setIsPrimary(false);
            addressRepository.save(adapter.map(a, AddressModel.class));
        });
    }
}