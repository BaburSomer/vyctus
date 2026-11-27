/**
 * @Autor: 	 Babür Somer
 * @Date:	 27.November 2026
 * @Version: 1.0
 *
 */
package com.vyctus.auth.internal.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
import org.springframework.dao.DataIntegrityViolationException;

import com.vyctus.auth.internal.entity.User;
import com.vyctus.auth.internal.entity.UserPreference;
import com.vyctus.config.PostgresTestConfiguration;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PostgresTestConfiguration.class)
class UserPreferenceRepositoryTest {

	@Autowired
	private UserPreferenceRepository repo;

	@Autowired
	private UserRepository userRepo;

	private UserPreference preference;
	private User           user;

	@BeforeEach
	void setUp() throws Exception {
		this.user = userRepo.saveAndFlush(User.builder()
				.firstName("Bab")
				.lastName("Som")
				.birthDate(Date.valueOf(LocalDate.of(1964, 12, 19)))
				.height(new BigDecimal("169.3"))
				.createdBy("The Tester").build());

		this.preference = UserPreference.builder()
				.localeId("tr")
				.user(this.user)
				.timezone("GMT +3")
				.measurementSystem("metric")
				.energyUnit("Kcal")
				.createdBy("The Tester").build();
	}

	@Test
	void shouldSaveAndFindUserPreferenceById() {
		UserPreference saved = repo.saveAndFlush(this.preference);
		assertThat(saved.getOid()).isNotNull();

		Optional<UserPreference> read = repo.findById(saved.getOid());
		assertThat(read).isPresent();

		UserPreference found = read.orElseThrow();

		assertThat(found.getLocaleId()).isEqualTo("tr");
		assertThat(found.getUser()).isEqualTo(this.user);
		assertThat(found.getTimezone()).isEqualTo("GMT +3");
		assertThat(found.getMeasurementSystem()).isEqualTo("metric");
		assertThat(found.getEnergyUnit()).isEqualTo("Kcal");
		assertThat(found.getCreatedBy()).isEqualTo("The Tester");

		assertThat(found.getVersion()).isNotNull();
		assertThat(found.getCreated()).isNotNull();
		assertThat(found.getUpdatedBy()).isNull();

	}

	@Test
	void shouldReturnEmptyWhenUserPreferenceDoesNotExist() {

		Optional<UserPreference> result = repo.findById(java.util.UUID.randomUUID());

		assertThat(result).isEmpty();
	}

	@Test
	void shouldUpdateAndReadNewValues() {
		UserPreference saved           = repo.saveAndFlush(this.preference.clone());
		Integer        originalVersion = saved.getVersion();

		saved.setLocaleId("de");
		saved.setTimezone("UTS");
		saved.setMeasurementSystem("royal");
		saved.setEnergyUnit("Joule");
		saved.setUpdatedBy("Another Tester");

		UserPreference updated = repo.saveAndFlush(saved.clone());

		Optional<UserPreference> read  = repo.findById(updated.getOid());
		UserPreference           found = read.orElseThrow();

		assertThat(found.getLocaleId()).isNotEqualTo(this.preference.getLocaleId());
		assertThat(found.getTimezone()).isNotEqualTo(this.preference.getTimezone());
		assertThat(found.getMeasurementSystem()).isNotEqualTo(this.preference.getMeasurementSystem());
		assertThat(found.getEnergyUnit()).isNotEqualTo(this.preference.getEnergyUnit());

		assertThat(found.getVersion()).isNotEqualTo(originalVersion);
		assertThat(found.getCreatedBy()).isEqualTo(this.preference.getCreatedBy());
		assertThat(found.getCreated()).isEqualTo(updated.getCreated());

		assertThat(found.getUpdated()).isNotNull();
		assertThat(found.getUpdatedBy()).isEqualTo("Another Tester");

	}

	@Test
	void shouldNotFindDeletedRecord() {
		UserPreference saved = repo.saveAndFlush(this.preference);

		repo.delete(saved);
		Optional<UserPreference> read = repo.findById(saved.getOid());

		assertThat(read).isNotPresent();
	}

	@Test
	void shouldNotAllowDuplicateUserLocalePreference() {
		UserPreference first = createUserPreference(user, "en-US");
		repo.saveAndFlush(first);

		UserPreference duplicate = createUserPreference(user, "en-US");

		assertThatThrownBy(() -> repo.saveAndFlush(duplicate)).isInstanceOf(DataIntegrityViolationException.class);
	}

	@Test
	void shouldAllowDifferentLocalesForSameUser() {

		int numOfEnties = repo.findAll().size();
		UserPreference english = createUserPreference(user, "en-US");
		UserPreference german  = createUserPreference(user, "de-AT");

		repo.saveAndFlush(english);
		repo.saveAndFlush(german);

		assertThat(repo.findAll()).hasSize(numOfEnties + 2);
	}

	@Test
	void shouldAllowSameLocaleForDifferentUsers() {
		User user2 = userRepo.saveAndFlush(User.builder().firstName("Elf").lastName("Somi").birthDate(Date.valueOf(LocalDate.of(1967, 3, 5)))
				.height(new BigDecimal("163.3")).createdBy("The Tester").build());

		int numOfEnties = repo.findAll().size();

		UserPreference preference1 = createUserPreference(user, "en-US");
		UserPreference preference2 = createUserPreference(user2, "en-US");

		repo.saveAndFlush(preference1);
		repo.saveAndFlush(preference2);

		assertThat(repo.findAll()).hasSize(numOfEnties + 2);
	}
	
	private UserPreference createUserPreference(User user, String locale) {
		return UserPreference.builder()
				.user(user)
				.localeId(locale)
				.timezone("Europe/Istanbul")
				.measurementSystem("METRIC")
				.energyUnit("KCAL")
				.createdBy("The Tester").build();
	}
}
