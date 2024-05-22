package com.person.management.controllers;

import com.person.management.dtos.address.AddressCreateDTO;
import com.person.management.dtos.address.AddressEditDTO;
import com.person.management.dtos.address.AddressViewDTO;
import com.person.management.services.AddressService;
import com.person.management.services.PersonAddressService;
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

public class AddressControllerTest {

    @InjectMocks
    private AddressController addressController;
    @Mock
    private AddressService addressService;
    @Mock
    private PersonAddressService personAddressService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAddressByPersonId() {
        Long personId = 1L;
        AddressViewDTO addressViewDTO = new AddressViewDTO();
        List<AddressViewDTO> expectedAddresses = Collections.singletonList(addressViewDTO);
        when(addressService.findAddressByPersonId(personId)).thenReturn(expectedAddresses);

        ResponseEntity<List<AddressViewDTO>> response = addressController.findAddressByPersonId(personId);

        verify(addressService, times(1)).findAddressByPersonId(personId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedAddresses, response.getBody());
    }

    @Test
    public void testFindByAddressId() {
        Long addressId = 1L;
        AddressViewDTO addressViewDTO = new AddressViewDTO();
        when(addressService.findByAddressId(addressId)).thenReturn(addressViewDTO);

        ResponseEntity<AddressViewDTO> response = addressController.findByAddressId(addressId);

        verify(addressService, times(1)).findByAddressId(addressId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(addressViewDTO, response.getBody());
    }

    @Test
    public void testCreateAddress() {
        Long personId = 1L;
        AddressCreateDTO addressCreateDTO = new AddressCreateDTO();
        AddressViewDTO expectedAddress = new AddressViewDTO();
        when(personAddressService.createAddress(addressCreateDTO, personId)).thenReturn(expectedAddress);

        ResponseEntity<AddressViewDTO> response = addressController.createAddress(personId, addressCreateDTO);

        verify(personAddressService, times(1)).createAddress(addressCreateDTO, personId);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedAddress, response.getBody());
    }

    @Test
    public void testEditAddress() {
        Long addressId = 1L;
        AddressEditDTO addressEditDTO = new AddressEditDTO();
        AddressViewDTO expectedAddress = new AddressViewDTO();
        when(addressService.editAddress(addressId, addressEditDTO)).thenReturn(expectedAddress);

        ResponseEntity<AddressViewDTO> response = addressController.editAddress(addressId, addressEditDTO);

        verify(addressService, times(1)).editAddress(addressId, addressEditDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedAddress, response.getBody());
    }

    @Test
    public void testDeleteAddress() {
        Long addressId = 1L;
        doNothing().when(addressService).deleteAddress(addressId);

        ResponseEntity<Void> response = addressController.deleteAddress(addressId);

        verify(addressService, times(1)).deleteAddress(addressId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}