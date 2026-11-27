/**
 * @Autor: 	 Babür Somer
 * @Date:	 25.November 2026
 * @Version: 1.0
 *
 */
package com.vyctus.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
@Import(PostgresTestConfiguration.class)
public class FlywayMigrationTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	void shouldCreateAllApplicationSchemas() {
		String[] schemas = { "auth", "diary", "localization", "nutrition", "recipe" };

		for (String schema : schemas) {
			Boolean exists = jdbcTemplate.queryForObject("""
					SELECT EXISTS (
					    SELECT 1
					    FROM information_schema.schemata
					    WHERE schema_name = ?
					)
					""", Boolean.class, schema);

			assertThat(exists).as("Schema '%s' should exist", schema).isTrue();
		}
	}

	@Test
	void shouldHaveSuccessfulFlywayMigrations() {
		Integer total = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM public.flyway_schema_history", Integer.class);

		Integer failed = jdbcTemplate.queryForObject("""
				SELECT COUNT(*)
				FROM public.flyway_schema_history
				WHERE success = false
				""", Integer.class);

		assertThat(total).isPositive();
		assertThat(failed).isZero();
	}
}