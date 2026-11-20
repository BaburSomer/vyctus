package com.vyctus.localization.api.model;

import lombok.Data;

@Data
public class LocaleDTO {

	private String  locale;
	private String  name;
	private String  description;
	private String  fallbackLocale;
	private boolean active;
}
