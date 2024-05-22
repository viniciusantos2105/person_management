package com.person.management.services;

import com.person.management.adapters.Adapter;
import com.person.management.dtos.address.AddressEditDTO;
import com.person.management.dtos.address.AddressViewDTO;
import com.person.management.entities.Address;
import com.person.management.entities.Person;
import com.person.management.models.AddressModel;
import com.person.management.models.PersonModel;
import com.person.management.repositories.address.AddressRepository;
import com.person.management.repositories.address.AddressRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AddressRepositoryImpl addressRepositoryImpl;

    @Mock
    private Adapter adapter;

    @Mock
    private PersonAddressService personAddressService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAddressByPersonId() {
        Long personId = 1L;
        AddressModel addressModel = new AddressModel();
        AddressViewDTO expectedAddressViewDTO = new AddressViewDTO();
        List<AddressModel> addressModelList = Arrays.asList(addressModel);

        when(addressRepositoryImpl.findAddressByPersonId(personId)).thenReturn(addressModelList);
        when(adapter.map(addressModel, AddressViewDTO.class)).thenReturn(expectedAddressViewDTO);

        List<AddressViewDTO> result = addressService.findAddressByPersonId(personId);

        verify(addressRepositoryImpl, times(1)).findAddressByPersonId(personId);
        verify(adapter, times(1)).map(addressModel, AddressViewDTO.class);
        assertEquals(1, result.size());
        assertEquals(expectedAddressViewDTO, result.get(0));
    }

    @Test
    public void testFindByAddressId() {
        Long addressId = 1L;
        AddressModel addressModel = new AddressModel();
        AddressViewDTO expectedAddressViewDTO = new AddressViewDTO();

        when(addressRepositoryImpl.findAddressById(addressId)).thenReturn(addressModel);
        when(adapter.map(addressModel, AddressViewDTO.class)).thenReturn(expectedAddressViewDTO);

        AddressViewDTO result = addressService.findByAddressId(addressId);

        verify(addressRepositoryImpl, times(1)).findAddressById(addressId);
        verify(adapter, times(1)).map(addressModel, AddressViewDTO.class);
        assertEquals(expectedAddressViewDTO, result);
    }

    @Test
    public void testEditAddress() {
        Long addressId = 1L;
        AddressEditDTO addressEditDTO = new AddressEditDTO();
        Address persistedAddress = new Address();
        Address editedAddress = new Address();
        AddressModel addressModel = new AddressModel();
        AddressViewDTO expectedAddressViewDTO = new AddressViewDTO();

        when(addressRepositoryImpl.findAddressById(addressId)).thenReturn(addressModel);
        when(adapter.map(addressModel, Address.class)).thenReturn(persistedAddress);
        when(adapter.map(addressEditDTO, Address.class)).thenReturn(editedAddress);
        when(adapter.map(persistedAddress, AddressModel.class)).thenReturn(addressModel);
        when(adapter.map(persistedAddress, AddressViewDTO.class)).thenReturn(expectedAddressViewDTO);

        AddressViewDTO result = addressService.editAddress(addressId, addressEditDTO);

        verify(adapter, times(1)).map(addressModel, Address.class);
        verify(adapter, times(1)).map(addressEditDTO, Address.class);
        verify(adapter, times(1)).updateTargetFromSource(editedAddress, persistedAddress);
        verify(adapter, times(1)).map(persistedAddress, AddressModel.class);
        verify(addressRepository, times(1)).save(addressModel);
        verify(adapter, times(1)).map(persistedAddress, AddressViewDTO.class);
        assertEquals(expectedAddressViewDTO, result);
    }

    @Test
    public void testDeleteAddress() {
        Long addressId = 1L;
        AddressModel addressModel = new AddressModel();
        PersonModel personModel = new PersonModel();
        addressModel.setPerson(personModel);
        when(addressRepositoryImpl.findAddressById(addressId)).thenReturn(addressModel);
        Address address = new Address();
        address.setPerson(new Person());
        when(adapter.map(addressModel, Address.class)).thenReturn(address);

        AddressModel remainingAddress = new AddressModel();
        when(addressRepositoryImpl.findAddressByPersonId(personModel.getPersonId())).thenReturn(Collections.singletonList(remainingAddress));

        addressService.deleteAddress(addressId);

        verify(addressRepositoryImpl, times(1)).findAddressById(addressId);
        verify(addressRepositoryImpl, times(1)).deleteAddress(addressId);
        verify(addressRepositoryImpl, times(1)).findAddressByPersonId(personModel.getPersonId());
        assertTrue(remainingAddress.getIsPrimary());
        verify(addressRepository, times(1)).save(remainingAddress);
    }

}