/**
 * @Autor: 	 Babür Somer
 * @Date:	 26.November 2026
 * @Version: 1.0
 *
 */
package com.vyctus.auth.internal.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.vyctus.auth.internal.entity.Role;
import com.vyctus.auth.internal.entity.User;
import com.vyctus.auth.internal.entity.UserRole;
import com.vyctus.config.PostgresTestConfiguration;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PostgresTestConfiguration.class)
class UserRoleRepositoryTest {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private UserRoleRepository userRoleRepo;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void shouldCreateUserRole() {

		User savedUser = createAndSaveUser();
		Role savedRole = createAndSaveRole();

		UserRole userRole = new UserRole();
		userRole.setUser(savedUser);
		userRole.setRole(savedRole);
		userRole.setCreatedBy("The Tester");
		UserRole savedUserRole = userRoleRepo.saveAndFlush(userRole);

		assertThat(savedUserRole.getOid()).isNotNull();
		assertThat(savedUserRole.getVersion()).isNotNull();

		assertThat(savedUserRole.getUser().getOid()).isEqualTo(savedUser.getOid());

		assertThat(savedUserRole.getRole().getOid()).isEqualTo(savedRole.getOid());

		assertThat(savedUserRole.getCreated()).isNotNull();
	}

	@Test
	void shouldNotAllowDuplicateUserRoleAssignment() {

		User user = createAndSaveUser();
		Role role = createAndSaveRole();

		UserRole first = new UserRole();
		first.setUser(user);
		first.setRole(role);
		first.setCreatedBy("The Tester");

		userRoleRepo.saveAndFlush(first);

		UserRole duplicate = new UserRole();
		duplicate.setUser(user);
		duplicate.setRole(role);
		duplicate.setCreatedBy("The Tester");

		assertThatThrownBy(() -> userRoleRepo.saveAndFlush(duplicate)).isInstanceOf(DataIntegrityViolationException.class);
	}

	@Test
	void shouldAllowSameRoleForMultipleUsers() {

		int initialUserRepoSize = userRoleRepo.findAll().size();
		Role role = createAndSaveRole();

		User user1 = createAndSaveUser();
		User user = User.builder()
				.firstName("Elfi").lastName("Som,")
				.birthDate(Date.valueOf(LocalDate.of(1968, 4, 6)))
				.height(new BigDecimal("163.5"))
				.createdBy("The Tester")
				.build();
		
		User user2 =  userRepo.saveAndFlush(user);

		UserRole assignment1 = new UserRole();
		assignment1.setUser(user1);
		assignment1.setRole(role);
		assignment1.setCreatedBy("The Tester");

		UserRole assignment2 = new UserRole();
		assignment2.setUser(user2);
		assignment2.setRole(role);
		assignment2.setCreatedBy("The Tester");

		userRoleRepo.saveAndFlush(assignment1);
		userRoleRepo.saveAndFlush(assignment2);

		assertThat(userRoleRepo.findAll()).hasSize(initialUserRepoSize + 2);
	}
	
	@Test
	void shouldDeleteUserRoleWithoutDeletingUserOrRole() {

	    User user = createAndSaveUser();
	    Role role = createAndSaveRole();

	    UserRole userRole = new UserRole();
	    userRole.setUser(user);
	    userRole.setRole(role);
	    userRole.setCreatedBy("The Tester");

	    UserRole saved = userRoleRepo.saveAndFlush(userRole);

	    userRoleRepo.delete(saved);
	    userRoleRepo.flush();

	    assertThat(userRoleRepo.findById(saved.getOid())).isEmpty();

	    assertThat(userRepo.findById(user.getOid())).isPresent();
	    assertThat(roleRepo.findById(role.getOid())).isPresent();
	}
	
	@Test
	void shouldNotDeleteUserWithExistingRoleAssignments() {

		User user = createAndSaveUser();
		Role role = createAndSaveRole();

		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		userRole.setCreatedBy("The Tester");

		userRoleRepo.saveAndFlush(userRole);

//	    assertThatThrownBy(() -> {
//	        userRepo.deleteById(user.getOid());
//	        userRepo.flush(); }
//	    ).isInstanceOf(DataIntegrityViolationException.class);

		assertThatThrownBy(() -> jdbcTemplate.update("DELETE FROM auth.users WHERE oid = ?", user.getOid()))
				.isInstanceOf(DataIntegrityViolationException.class);
	}
	
	@Test
	void shouldNotDeleteRoleWithExistingUserAssignments() {

	    User user = createAndSaveUser();
	    Role role = createAndSaveRole();

	    UserRole userRole = new UserRole();
	    userRole.setUser(user);
	    userRole.setRole(role);
	    userRole.setCreatedBy("The Tester");

	    userRoleRepo.saveAndFlush(userRole);

//			assertThatThrownBy(() -> jdbcTemplate.update("DELETE FROM auth.roles WHERE oid = ?", role.getOid()))
//			.isInstanceOf(DataIntegrityViolationException.class);

	    /*
	     * Bu şekli ile olunca JPA yakalıyor refere edilmiş bir entity olduğunu ve aşağıdaki exception'ı atıyor. 
	     * Halbuki doğrudan yukarıdaki gibi SQL ile silindiğinde PostGres exception gönderiyor ve onu yukarıdaki 
	     * exception ile yakalayabiliyoruz. 
	     */
	    assertThatThrownBy(() -> {
	        roleRepo.deleteById(role.getOid());
	        roleRepo.flush(); }
	    ).isInstanceOf(InvalidDataAccessApiUsageException.class);
	}
	
	private User createAndSaveUser() {
		User user = User.builder()
				.firstName("Bab").lastName("Som")
				.birthDate(Date.valueOf(LocalDate.of(1965, 6, 16)))
				.height(new BigDecimal("169.5"))
				.createdBy("The Tester")
				.build();
		
		return userRepo.saveAndFlush(user);
	}
	
	private Role createAndSaveRole() {
		Role role = Role.builder()
				.name("TESTER")
				.createdBy("The Tester")
				.build();

		return roleRepo.saveAndFlush(role);
	}
}