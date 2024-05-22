package com.person.management.services;

import com.person.management.adapters.Adapter;
import com.person.management.dtos.address.AddressEditDTO;
import com.person.management.dtos.address.AddressViewDTO;
import com.person.management.entities.Address;
import com.person.management.entities.Person;
import com.person.management.models.AddressModel;
import com.person.management.repositories.address.AddressRepository;
import com.person.management.repositories.address.AddressRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    private final Adapter adapter;
    private final PersonAddressService personAddressService;
    private final AddressRepository addressRepository;
    private final AddressRepositoryImpl addressRepositoryImpl;

    public AddressService(AddressRepository addressRepository, AddressRepositoryImpl addressRepositoryImpl, Adapter adapter, PersonAddressService personAddressService) {
        this.addressRepository = addressRepository;
        this.addressRepositoryImpl = addressRepositoryImpl;
        this.adapter = adapter;
        this.personAddressService = personAddressService;
    }

    public List<AddressViewDTO> findAddressByPersonId(Long personId) {
        List<AddressModel> list = addressRepositoryImpl.findAddressByPersonId(personId);
        return list.stream()
                .map(address -> adapter.map(address, AddressViewDTO.class))
                .collect(Collectors.toList());
    }

    public AddressViewDTO findByAddressId(Long addressId) {
        return adapter.map(addressRepositoryImpl.findAddressById(addressId), AddressViewDTO.class);
    }

    public AddressViewDTO editAddress(Long addressId, AddressEditDTO addressEditDTO) {
        Address persistedAddress = adapter.map(addressRepositoryImpl.findAddressById(addressId), Address.class);
        Address editedAddress = adapter.map(addressEditDTO, Address.class);
        adapter.updateTargetFromSource(editedAddress, persistedAddress);
        personAddressService.isPrimary(persistedAddress.getPerson(), persistedAddress);
        AddressModel addressModel = adapter.map(persistedAddress, AddressModel.class);
        addressRepository.save(addressModel);
        return adapter.map(persistedAddress, AddressViewDTO.class);
    }

    public void deleteAddress(Long addressId) {
        Address address = adapter.map(addressRepositoryImpl.findAddressById(addressId), Address.class);
        Person person = address.getPerson();

        addressRepositoryImpl.deleteAddress(addressId);

        List<AddressModel> remainingAddresses = addressRepositoryImpl.findAddressByPersonId(person.getPersonId());
        if (remainingAddresses.size() == 1) {
            AddressModel remainingAddress = remainingAddresses.get(0);
            remainingAddress.setIsPrimary(true);
            addressRepository.save(remainingAddress);
        }
    }
}
