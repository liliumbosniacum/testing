package com.lilium.testing.repository;

import com.lilium.testing.entity.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends DistributedRepository<Person> {
}
