package com.vyctus.localization.internal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "locales", schema = "localization")
public class Locale {

	@Id
	@Column(name = "locale", length = 20, updatable = false, nullable = false, unique = true)
	private String locale;

	@Column(name = "name", length = 100, nullable = false)
	private String  name;

	@Column(name = "description", length = 100, nullable = false)
	private String  description;

	@Column(name = "fallback_locale", length = 20)
	private String  fallbackLocale;

	@Column(name = "active")
	private boolean active;
}