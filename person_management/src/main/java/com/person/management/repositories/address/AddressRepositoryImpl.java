package com.person.management.repositories.address;

import com.person.management.exceptions.commons.NotFoundException;
import com.person.management.models.AddressModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class AddressRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;


    public List<AddressModel> findAddressByPersonId(Long personId) {
        TypedQuery<AddressModel> query = entityManager.createQuery("SELECT a FROM AddressModel a WHERE a.person.personId = :personId", AddressModel.class);
        query.setParameter("personId", personId);

        if (query.getResultList().isEmpty()) {
            throw NotFoundException.createNotFoundException("Address");
        }

        return query.getResultList();
    }

    public AddressModel findAddressById(Long addressId) {
        TypedQuery<AddressModel> query = entityManager.createQuery("SELECT a FROM AddressModel a WHERE a.addressId = :addressId", AddressModel.class);
        query.setParameter("addressId", addressId);

        if (query.getResultList().isEmpty()) {
            throw NotFoundException.createNotFoundException("Address");
        }

        return query.getSingleResult();
    }

    @Transactional
    public void deleteAddress(Long addressId) {
        Query query = entityManager.createQuery("DELETE FROM AddressModel a WHERE a.addressId = :addressId");
        query.setParameter("addressId", addressId);

        query.executeUpdate();
    }
}
