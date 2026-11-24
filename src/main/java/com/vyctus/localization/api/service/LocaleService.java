/**
 * @Autor: 	 Babür Somer
 * @Date:	 22.November 2026
 * @Version: 1.0
 *
 */
package com.vyctus.localization.api.service;

import java.util.List;

import com.vyctus.localization.api.model.LocaleDTO;

public interface LocaleService {

	List<LocaleDTO> listLocales();
}
