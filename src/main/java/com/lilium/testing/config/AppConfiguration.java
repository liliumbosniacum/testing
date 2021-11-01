package com.lilium.testing.config;

import com.lilium.testing.repository.DistributedRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        value = "com.lilium.testing.repository",
        repositoryBaseClass = DistributedRepositoryImpl.class
)
public class AppConfiguration {
}
