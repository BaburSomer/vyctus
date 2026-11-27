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
class UserRepositoryTest {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private UserRoleRepository userRoleRepo;

	private User user;
	private Role role;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		this.user = User.builder()
				.firstName("Bab")
				.lastName("Som")
				.birthDate(Date.valueOf(LocalDate.of(1964, 12, 19)))
				.height(new BigDecimal("170.3"))
				.createdBy("Babre Somre")
				.build();
		
		this.role = Role.builder()
				.name("TESTER")
				.createdBy("The Tester")
				.build();
	}

	@Test
	void shouldSaveAndFindUserById() {

		User saved = repo.saveAndFlush(this.user);

		assertThat(saved.getOid()).isNotNull();

		Optional<User> read = repo.findById(saved.getOid());

		assertThat(read).isPresent();

		User found = read.orElseThrow();

		assertThat(found.getFirstName()).isEqualTo("Bab");
		assertThat(found.getLastName()).isEqualTo("Som");
		assertThat(found.getBirthDate()).isEqualTo(Date.valueOf(LocalDate.of(1964, 12, 19)));
		assertThat(found.getHeight()).isEqualByComparingTo("170.3");
		assertThat(found.getCreatedBy()).isEqualTo("Babre Somre");

		assertThat(found.getVersion()).isNotNull();
		assertThat(found.getCreated()).isNotNull();
	}
	
	@Test
	void shouldReturnEmptyWhenUserDoesNotExist() {

		Optional<User> result = repo.findById(java.util.UUID.randomUUID());

		assertThat(result).isEmpty();
	}

	@Test
	void shouldUpdateAndReadNewValues() {
		User saved = repo.saveAndFlush(this.user.clone());
		Integer originalVersion = saved.getVersion();
		
		saved.setFirstName("Elf");
		saved.setLastName("Şen");
		saved.setBirthDate(Date.valueOf(LocalDate.of(1967, 3, 5)));
		saved.setHeight(new BigDecimal("165.6"));
		saved.setUpdatedBy("Another Tester");
		
		User updated = repo.saveAndFlush(saved.clone());
		
		Optional<User> read = repo.findById(updated.getOid());
		User found = read.orElseThrow();
		
		assertThat(found.getFirstName()).isNotEqualTo(this.user.getFirstName());
		assertThat(found.getLastName()).isNotEqualTo(this.user.getLastName());
		assertThat(found.getBirthDate()).isNotEqualTo(this.user.getBirthDate());
		assertThat(found.getHeight()).isNotEqualTo(this.user.getHeight());

		assertThat(found.getVersion()).isNotEqualTo(originalVersion);
		assertThat(found.getCreatedBy()).isEqualTo(this.user.getCreatedBy());
		assertThat(found.getCreated()).isEqualTo(updated.getCreated());

		assertThat(found.getUpdated()).isNotNull();
		assertThat(found.getUpdatedBy()).isEqualTo("Another Tester");

	}
	
	@Test
	void shouldNotFindDeletedRecord() {
		User saved = repo.saveAndFlush(this.user);
		
		repo.delete(saved);
		Optional<User> read = repo.findById(saved.getOid());

		assertThat(read).isNotPresent();
	}
	
	@Test
	void shouldAddRoleToUserNoPersistance() {
		User savedUser = repo.saveAndFlush(this.user);
		Role savedRole = roleRepo.saveAndFlush(this.role);

		savedUser.addRole(savedRole);

	    assertThat(savedUser.getUserRoles()).hasSize(1);

	    UserRole userRole = savedUser.getUserRoles().iterator().next();

	    assertThat(userRole.getUser()).isSameAs(savedUser);
	    assertThat(userRole.getRole()).isSameAs(savedRole);
	}
	
	@Test
	void shouldPersistRoleAddedToUser() {
		User savedUser = repo.saveAndFlush(this.user);
		Role savedRole = roleRepo.saveAndFlush(this.role);

		int initialSizeOfUserRoles = userRoleRepo.findAll().size();
		savedUser.addRole(savedRole);

		UserRole userRole = user.getUserRoles().iterator().next();
		userRole.setCreatedBy("The Tester");

		userRoleRepo.saveAndFlush(userRole);

		assertThat(userRoleRepo.findAll()).hasSize(initialSizeOfUserRoles + 1);
	}
}