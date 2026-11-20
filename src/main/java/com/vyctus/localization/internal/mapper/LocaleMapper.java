package com.vyctus.localization.internal.mapper;

import org.mapstruct.Mapper;

import com.vyctus.localization.api.model.LocaleDTO;
import com.vyctus.localization.internal.entity.Locale;

@Mapper(componentModel = "spring")
public interface LocaleMapper {

	Locale toEntity(LocaleDTO dto);

	LocaleDTO toDto(Locale entity);

}
