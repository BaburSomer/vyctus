/**
 * @Autor: 	 Babür Somer
 * @Date:	 22.November 2026
 * @Version: 1.0
 *
 */
package com.vyctus.localization.api.service.impl;

import java.util.List;
import java.util.Optional;

import com.vyctus.localization.api.model.TranslationDTO;
import com.vyctus.localization.api.service.TranslationService;
import com.vyctus.localization.internal.entity.Translation;
import com.vyctus.localization.internal.entity.TranslationId;

public class TranslationServiceImpl implements TranslationService {

	@Override
	public List<TranslationDTO> listTranslations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Translation> findById(TranslationId id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
}