package com.lilium.testing.service;

import com.lilium.testing.entity.Address;
import com.lilium.testing.entity.Person;
import com.lilium.testing.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private PersonRepository repository;
    private AddressService addressService;

    @Autowired
    public PersonService(PersonRepository repository, AddressService addressService) {
        this.repository = repository;
        this.addressService = addressService;
    }

    public Person createPerson(String addressId, String personName) {
        Address address = addressService.findAddress(addressId);
        if (address == null) {
            return null;
        }

        Person person = new Person();
        person.setAddress(address);
        person.setName(personName);

        return repository.save(person);
    }
}
