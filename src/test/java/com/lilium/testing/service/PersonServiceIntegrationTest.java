package com.lilium.testing.service;

import com.lilium.testing.entity.Address;
import com.lilium.testing.entity.Person;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PersonServiceIntegrationTest {
    @Autowired
    private PersonService personService;

    @Autowired
    private AddressService addressService;

    @Test
    void testCreatePerson_NoAddress() {
        Person person = personService.createPerson("does not exist", "James Bond");
        assertThat(person).isNull();
    }

    @Test
    void testCreatePerson() {
        Address address = addressService.createAddress("a1");
        assertThat(address).isNotNull();

        Person person = personService.createPerson(address.getId(), "James Bond");
        assertThat(person).isNotNull();
        assertThat(person.getAddress()).isNotNull();
        assertThat(person.getAddress().getId()).isEqualTo(address.getId());
    }
}
