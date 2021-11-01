package com.lilium.testing.service;

import com.lilium.testing.entity.Address;
import com.lilium.testing.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private AddressRepository repository;

    @Autowired
    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    public Address createAddress(String name) {
        Address address = new Address();
        address.setName(name);

        return repository.save(address);
    }

    public Address findAddress(String id) {
        return repository.findById(id).orElse(null);
    }
}
