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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PersonAddressServiceTest {

    @InjectMocks
    private PersonAddressService personAddressService;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private Adapter adapter;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAddress() {
        Long personId = 1L;
        AddressCreateDTO addressCreateDTO = new AddressCreateDTO();
        PersonModel personModel = new PersonModel();
        Person person = new Person();
        Address address = new Address();
        AddressModel addressModel = new AddressModel();
        AddressViewDTO expectedAddressViewDTO = new AddressViewDTO();

        address.setIsPrimary(false);
        addressCreateDTO.setIsPrimary(false);

        when(personRepository.findById(personId)).thenReturn(Optional.of(personModel));
        when(adapter.map(personModel, Person.class)).thenReturn(person);
        when(adapter.map(addressCreateDTO, Address.class)).thenReturn(address);
        when(adapter.map(address, AddressModel.class)).thenReturn(addressModel);
        when(adapter.map(addressModel, AddressViewDTO.class)).thenReturn(expectedAddressViewDTO);

        AddressViewDTO result = personAddressService.createAddress(addressCreateDTO, personId);

        verify(personRepository, times(1)).findById(personId);
        verify(adapter, times(1)).map(personModel, Person.class);
        verify(adapter, times(1)).map(addressCreateDTO, Address.class);
        verify(adapter, times(1)).map(address, AddressModel.class);
        verify(addressRepository, times(1)).save(addressModel);
        verify(adapter, times(1)).map(addressModel, AddressViewDTO.class);
        assertEquals(expectedAddressViewDTO, result);
    }

    @Test
    public void testIsPrimary() {
        Person person = new Person();
        Address newAddress = new Address();
        PersonModel personModel = new PersonModel();
        List<Address> addresses = new ArrayList<>();
        Address address = new Address();
        addresses.add(address);
        person.setAddresses(addresses);
        newAddress.setIsPrimary(true);

        when(adapter.map(person, PersonModel.class)).thenReturn(personModel);
        when(adapter.map(address, AddressModel.class)).thenReturn(new AddressModel());

        personAddressService.isPrimary(person, newAddress);

        assertTrue(newAddress.getIsPrimary());
        assertEquals(2, person.getAddresses().size());
        verify(personRepository, times(1)).save(personModel);
        verify(addressRepository, times(1)).save(any(AddressModel.class));
    }

    @Test
    public void testSetAllAddressesToNotPrimary() {
        Person person = new Person();
        Address address1 = new Address();
        Address address2 = new Address();
        List<Address> addresses = new ArrayList<>();
        addresses.add(address1);
        addresses.add(address2);
        person.setAddresses(addresses);

        when(adapter.map(any(Address.class), eq(AddressModel.class))).thenReturn(new AddressModel());

        personAddressService.setAllAddressesToNotPrimary(person);

        for (Address address : person.getAddresses()) {
            assertFalse(address.getIsPrimary());
        }
        verify(addressRepository, times(person.getAddresses().size())).save(any(AddressModel.class));
    }
}
