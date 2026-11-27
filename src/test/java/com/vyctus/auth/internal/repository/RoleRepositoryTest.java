/**
 * @Autor: 	 Babür Somer
 * @Date:	 25.November 2026
 * @Version: 1.0
 *
 */
package com.vyctus.auth.internal.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import com.vyctus.auth.internal.entity.Role;
import com.vyctus.auth.internal.entity.User;
import com.vyctus.auth.internal.entity.UserRole;
import com.vyctus.config.PostgresTestConfiguration;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PostgresTestConfiguration.class)
class RoleRepositoryTest {

	@Autowired
	private RoleRepository repo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserRoleRepository userRoleRepo;

	private Role role;
	private User user;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		this.role = Role.builder()
				.name("TestRole")
				.description("A role for JUnit testing")
				.createdBy("The Tester")
				.build();
		
		this.user = User.builder()
				.firstName("Bab")
				.lastName("Som")
				.birthDate(Date.valueOf(LocalDate.of(1964, 12, 19)))
				.height(new BigDecimal("169.3"))
				.createdBy("The Tester")
				.build();
	}

	@Test
	void shouldSaveAndFindUserById() {

		Role saved = repo.saveAndFlush(this.role);
		assertThat(saved.getOid()).isNotNull();

		Optional<Role> read = repo.findById(saved.getOid());
		assertThat(read).isPresent();

		Role found = read.orElseThrow();
		assertThat(found.getName()).isEqualTo("TestRole");
		assertThat(found.getDescription()).isEqualTo("A role for JUnit testing");
		assertThat(found.getCreatedBy()).isEqualTo("The Tester");

		assertThat(found.getVersion()).isNotNull();
		assertThat(found.getCreated()).isNotNull();
	}
	
	@Test
	void shouldReturnEmptyWhenUserDoesNotExist() {

		Optional<Role> result = repo.findById(java.util.UUID.randomUUID());

		assertThat(result).isEmpty();
	}

	@Test
	void shouldUpdateAndReadNewValues() {
		Role saved = repo.saveAndFlush(this.role.clone());
		Integer originalVersion = saved.getVersion();
		
		saved.setName("Another TestRole");
		saved.setDescription("Another role for JUnit testing");
		saved.setUpdatedBy("Another Tester");
		
		Role updated = repo.saveAndFlush(saved.clone());
		
		Optional<Role> read = repo.findById(updated.getOid());
		Role found = read.orElseThrow();
		
		assertThat(found.getName()).isNotEqualTo(this.role.getName());
		assertThat(found.getDescription()).isNotEqualTo(this.role.getDescription());

		assertThat(found.getVersion()).isNotEqualTo(originalVersion);
		assertThat(found.getCreatedBy()).isEqualTo(this.role.getCreatedBy());
		assertThat(found.getCreated()).isEqualTo(updated.getCreated());

		assertThat(found.getUpdated()).isNotNull();
		assertThat(found.getUpdatedBy()).isEqualTo("Another Tester");

	}
	
	@Test
	void shouldNotFindDeletedRecord() {
		Role saved = repo.saveAndFlush(this.role);
		
		repo.delete(saved);
		Optional<Role> read = repo.findById(saved.getOid());

		assertThat(read).isNotPresent();
	}
	
	@Test
	void shouldAddUserToRoleNoPersistance() {
		Role savedRole = repo.saveAndFlush(this.role);
		User savedUser = userRepo.saveAndFlush(this.user);

		savedRole.addUser(savedUser);

		assertThat(savedRole.getUserRoles()).hasSize(1);

		UserRole userRole = savedRole.getUserRoles().iterator().next();

		assertThat(userRole.getUser()).isSameAs(savedUser);
		assertThat(userRole.getRole()).isSameAs(savedRole);
	}
	
	@Test
	void shouldPersistUserAddedToRole() {
		Role savedRole = repo.saveAndFlush(this.role);
		User savedUser = userRepo.saveAndFlush(this.user);

		int initialSizeOfUserRoles = userRoleRepo.findAll().size();
		
		savedRole.addUser(savedUser);

		UserRole userRole = role.getUserRoles().iterator().next();
		userRole.setCreatedBy("The Tester");

		userRoleRepo.saveAndFlush(userRole);

		assertThat(userRoleRepo.findAll()).hasSize(initialSizeOfUserRoles + 1);
	}

}