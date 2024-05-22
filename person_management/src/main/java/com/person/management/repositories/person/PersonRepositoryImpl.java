package com.person.management.repositories.person;

import com.person.management.exceptions.commons.NotFoundException;
import com.person.management.models.PersonModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public PersonModel findPersonById(Long id) {
        TypedQuery<PersonModel> query = entityManager.createQuery("SELECT p FROM PersonModel p WHERE p.id = :id", PersonModel.class);
        query.setParameter("id", id);

        if (query.getResultList().isEmpty()) {
            throw NotFoundException.createNotFoundException("Pessoa não encontrada");
        }

        return query.getSingleResult();
    }

    public List<PersonModel> findAll() {
        TypedQuery<PersonModel> query = entityManager.createQuery("SELECT p FROM PersonModel p", PersonModel.class);
        return query.getResultList();
    }

    @Transactional
    public void deletePerson(Long personId) {
        // Delete all addresses associated with the person
        Query addressQuery = entityManager.createQuery("DELETE FROM AddressModel a WHERE a.person.id = :id");
        addressQuery.setParameter("id", personId);
        addressQuery.executeUpdate();

        // Delete the person
        Query personQuery = entityManager.createQuery("DELETE FROM PersonModel p WHERE p.id = :id");
        personQuery.setParameter("id", personId);
        personQuery.executeUpdate();
    }
}
