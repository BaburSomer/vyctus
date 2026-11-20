package com.vyctus.localization.internal.mapper;

import org.springframework.stereotype.Component;

import com.vyctus.localization.api.model.TranslationDTO;
import com.vyctus.localization.internal.entity.Locale;
import com.vyctus.localization.internal.entity.Translation;
import com.vyctus.localization.internal.entity.TranslationId;

@Component
public class TranslationMapper {

	public TranslationDTO toDTO(Translation entity) {
		if (entity == null) {
			return null;
		}

		return new TranslationDTO(entity.getId().getKey(), entity.getId().getLocale(), entity.getTranslation());
	}

	public Translation toEntity(TranslationDTO dto, Locale locale) {

		if (dto == null) {
			return null;
		}

		Translation entity = new Translation();

		entity.setId(new TranslationId(dto.getKey(), dto.getLocale()));

		entity.setLocale(locale);
		entity.setTranslation(dto.getTranslation());

		return entity;
	}
}
