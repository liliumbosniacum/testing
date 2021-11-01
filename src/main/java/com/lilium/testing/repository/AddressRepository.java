package com.lilium.testing.repository;

import com.lilium.testing.entity.Address;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends DistributedRepository<Address> {
}
