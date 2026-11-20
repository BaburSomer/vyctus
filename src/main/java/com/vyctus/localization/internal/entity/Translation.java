package com.vyctus.localization.internal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
@Table(name = "translations", schema = "localization")
public class Translation {

	@EmbeddedId
	private TranslationId id;

	@MapsId("locale")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "locale", referencedColumnName = "name")
	private Locale locale;

	@Column(name = "translation", length = 100, nullable = false)
	private String translation;

}