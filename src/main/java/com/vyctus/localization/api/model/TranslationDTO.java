package com.vyctus.localization.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TranslationDTO {

	private String  key;
	private String  locale;
	private String  translation;
}
