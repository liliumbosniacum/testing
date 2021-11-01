package com.lilium.testing.repository;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;

public class DistributedRepositoryImpl<ENTITY>
        extends SimpleJpaRepository<ENTITY, String>
        implements DistributedRepository<ENTITY> {

    public DistributedRepositoryImpl(JpaEntityInformation<ENTITY, String> entityInformation,
                                     EntityManager entityManager) {
        super(entityInformation, entityManager);
    }
}
