/**
 * @Autor: 	 Babür Somer
 * @Date:	 22.November 2026
 * @Version: 1.0
 *
 */
package com.vyctus.localization.api.service;

import java.util.List;
import java.util.Optional;

import com.vyctus.localization.api.model.TranslationDTO;
import com.vyctus.localization.internal.entity.Translation;
import com.vyctus.localization.internal.entity.TranslationId;

public interface TranslationService {

	List<TranslationDTO> listTranslations();

	Optional<Translation> findById(TranslationId id);
}
