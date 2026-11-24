/**
 * @Autor: 	 Babür Somer
 * @Date:	 22.November 2026
 * @Version: 1.0
 *
 */
package com.vyctus.localization.internal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vyctus.localization.internal.entity.Locale;

public interface LocaleRepository extends JpaRepository<Locale, String> {

}
