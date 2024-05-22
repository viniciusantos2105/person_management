package com.person.management.repositories;

import com.person.management.exceptions.commons.NotFoundException;
import com.person.management.models.AddressModel;
import com.person.management.repositories.address.AddressRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AddressRepositoryImplTest {

    @InjectMocks
    private AddressRepositoryImpl addressRepositoryImpl;

    @Mock
    private EntityManager entityManager;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAddressByPersonId() {
        Long personId = 1L;
        AddressModel addressModel = new AddressModel();
        List<AddressModel> expectedAddresses = Collections.singletonList(addressModel);

        TypedQuery<AddressModel> typedQuery = mock(TypedQuery.class);
        when(entityManager.createQuery("SELECT a FROM AddressModel a WHERE a.person.personId = :personId", AddressModel.class)).thenReturn(typedQuery);
        when(typedQuery.setParameter("personId", personId)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(expectedAddresses);

        List<AddressModel> actualAddresses = addressRepositoryImpl.findAddressByPersonId(personId);

        verify(entityManager, times(1)).createQuery("SELECT a FROM AddressModel a WHERE a.person.personId = :personId", AddressModel.class);
        verify(typedQuery, times(1)).setParameter("personId", personId);
        assertEquals(expectedAddresses, actualAddresses);
    }

    @Test
    public void testFindAddressByPersonId_NotFound() {
        Long personId = 1L;

        TypedQuery<AddressModel> typedQuery = mock(TypedQuery.class);
        when(entityManager.createQuery("SELECT a FROM AddressModel a WHERE a.person.personId = :personId", AddressModel.class)).thenReturn(typedQuery);
        when(typedQuery.setParameter("personId", personId)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Collections.emptyList());

        assertThrows(NotFoundException.class, () -> addressRepositoryImpl.findAddressByPersonId(personId));
    }

    @Test
    public void testFindAddressById() {
        Long addressId = 1L;
        AddressModel expectedAddress = new AddressModel();

        TypedQuery<AddressModel> typedQuery = mock(TypedQuery.class);
        when(entityManager.createQuery("SELECT a FROM AddressModel a WHERE a.addressId = :addressId", AddressModel.class)).thenReturn(typedQuery);
        when(typedQuery.setParameter("addressId", addressId)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Collections.singletonList(expectedAddress));
        when(typedQuery.getSingleResult()).thenReturn(expectedAddress);

        AddressModel actualAddress = addressRepositoryImpl.findAddressById(addressId);

        verify(entityManager, times(1)).createQuery("SELECT a FROM AddressModel a WHERE a.addressId = :addressId", AddressModel.class);
        verify(typedQuery, times(1)).setParameter("addressId", addressId);
        assertEquals(expectedAddress, actualAddress);
    }

    @Test
    public void testFindAddressById_NotFound() {
        Long addressId = 1L;

        TypedQuery<AddressModel> typedQuery = mock(TypedQuery.class);
        when(entityManager.createQuery("SELECT a FROM AddressModel a WHERE a.addressId = :addressId", AddressModel.class)).thenReturn(typedQuery);
        when(typedQuery.setParameter("addressId", addressId)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Collections.emptyList());

        assertThrows(NotFoundException.class, () -> addressRepositoryImpl.findAddressById(addressId));
    }

    @Test
    public void testDeleteAddress() {
        Long addressId = 1L;

        Query query = mock(Query.class);
        when(entityManager.createQuery("DELETE FROM AddressModel a WHERE a.addressId = :addressId")).thenReturn(query);
        when(query.setParameter("addressId", addressId)).thenReturn(query);

        addressRepositoryImpl.deleteAddress(addressId);

        verify(entityManager, times(1)).createQuery("DELETE FROM AddressModel a WHERE a.addressId = :addressId");
        verify(query, times(1)).setParameter("addressId", addressId);
        verify(query, times(1)).executeUpdate();
    }
}
