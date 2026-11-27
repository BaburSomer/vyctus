/**
 * @Autor: 	 Babür Somer
 * @Date:	 22.November 2026
 * @Version: 1.0
 *
 */
package com.vyctus.localization.api.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.context.annotation.Import;

import com.vyctus.config.PostgresTestConfiguration;
import com.vyctus.localization.internal.entity.Locale;
import com.vyctus.localization.internal.entity.Translation;
import com.vyctus.localization.internal.entity.TranslationId;
import com.vyctus.localization.internal.repository.TranslationRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PostgresTestConfiguration.class)
class TranslationServiceImplTest {

   @Autowired
   private TranslationRepository repository;

   @Autowired
   private TestEntityManager entityManager;

	@BeforeEach
	void setUp() throws Exception {
	}

   @Test
   void shouldFindTranslationByKeyAndLocale() {
       // Arrange
       Locale locale = new Locale();
       locale.setLocale("tr");

//       entityManager.persist(locale);

       Translation translation = new Translation();
       translation.setId(new TranslationId(
           "FOOD_CATEGORY_FRUITS", "tr"
       ));
       translation.setLocale(locale);
       translation.setTranslation("Meyvalar");

       entityManager.persist(translation);
       entityManager.flush();
       entityManager.clear();

       // Act
       Optional<Translation> result = repository.findById(
           new TranslationId("FOOD_CATEGORY_FRUITS", "tr")
       );

       // Assert
       assertThat(result).isPresent();
       assertThat(result.get().getTranslation()).isEqualTo("Meyvalar");
   }
   
   @Test
	void testFindById() {
      Optional<Translation> result = repository.findById(
            new TranslationId("UNKNOWN_KEY", "en")
        );

        assertThat(result).isEmpty();
	}
}