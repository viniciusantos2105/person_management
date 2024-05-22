package com.person.management.repositories;

import com.person.management.exceptions.commons.NotFoundException;
import com.person.management.models.PersonModel;
import com.person.management.repositories.person.PersonRepositoryImpl;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class PersonRepositoryImplTest {

    @InjectMocks
    private PersonRepositoryImpl personRepositoryImpl;

    @Mock
    private EntityManager entityManager;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindPersonById() {
        // Arrange
        Long personId = 1L;
        PersonModel personModel = new PersonModel();

        TypedQuery<PersonModel> typedQuery = mock(TypedQuery.class);
        when(entityManager.createQuery(anyString(), eq(PersonModel.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(anyString(), any())).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Collections.singletonList(personModel));
        when(typedQuery.getSingleResult()).thenReturn(personModel); // Adicionado para garantir que getSingleResult() retorne o objeto esperado

        // Act
        PersonModel result = personRepositoryImpl.findPersonById(personId);

        // Assert
        verify(entityManager, times(1)).createQuery(anyString(), eq(PersonModel.class));
        verify(typedQuery, times(1)).setParameter(anyString(), any());
        assertEquals(personModel, result);
    }

    @Test
    public void testFindPersonByIdThrowsException() {
        // Arrange
        Long personId = 1L;

        TypedQuery<PersonModel> typedQuery = mock(TypedQuery.class);
        when(entityManager.createQuery(anyString(), eq(PersonModel.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(anyString(), any())).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Collections.emptyList());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> personRepositoryImpl.findPersonById(personId));
        verify(entityManager, times(1)).createQuery(anyString(), eq(PersonModel.class));
        verify(typedQuery, times(1)).setParameter(anyString(), any());
    }

    @Test
    public void testFindAll() {
        // Arrange
        PersonModel personModel = new PersonModel();
        List<PersonModel> expectedPersons = Collections.singletonList(personModel);

        TypedQuery<PersonModel> typedQuery = mock(TypedQuery.class);
        when(entityManager.createQuery("SELECT p FROM PersonModel p", PersonModel.class)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(expectedPersons);

        // Act
        List<PersonModel> actualPersons = personRepositoryImpl.findAll();

        // Assert
        verify(entityManager, times(1)).createQuery("SELECT p FROM PersonModel p", PersonModel.class);
        assertEquals(expectedPersons, actualPersons);
    }

    @Test
    public void testDeletePerson() {
        Long personId = 1L;

        Query addressQuery = mock(Query.class);
        Query personQuery = mock(Query.class);

        when(entityManager.createQuery("DELETE FROM AddressModel a WHERE a.person.id = :id")).thenReturn(addressQuery);
        when(entityManager.createQuery("DELETE FROM PersonModel p WHERE p.id = :id")).thenReturn(personQuery);
        when(addressQuery.setParameter("id", personId)).thenReturn(addressQuery);
        when(personQuery.setParameter("id", personId)).thenReturn(personQuery);

        // Act
        personRepositoryImpl.deletePerson(personId);

        // Assert
        verify(entityManager, times(1)).createQuery("DELETE FROM AddressModel a WHERE a.person.id = :id");
        verify(entityManager, times(1)).createQuery("DELETE FROM PersonModel p WHERE p.id = :id");
        verify(addressQuery, times(1)).setParameter("id", personId);
        verify(personQuery, times(1)).setParameter("id", personId);
        verify(addressQuery, times(1)).executeUpdate();
        verify(personQuery, times(1)).executeUpdate();
    }
}