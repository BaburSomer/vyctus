/**
 * @Autor: 	 Babür Somer
 * @Date:	 22.November 2026
 * @Version: 1.0
 *
 */
package com.vyctus.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class PostgresTestConfiguration {

	@Bean
	@ServiceConnection
	PostgreSQLContainer postgresContainer() {

		return new PostgreSQLContainer(DockerImageName.parse("postgres:15.5-alpine"))
				.withDatabaseName("vyctus_test")
				.withUsername("vyctus_admin")
				.withPassword("test")
				.withCommand("postgres", "-c", "fsync=off", "-c", "datestyle=ISO, DMY")
				.withInitScript("init-test-db.sql");
	}

}