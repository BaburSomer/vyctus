package com.vyctus.localization.internal.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Getter
public class TranslationId implements Serializable {

	private static final long serialVersionUID = 7535409108913582689L;

	@Column(name = "key", length = 100, updatable = false, nullable = false)
	private String key;

	@Column(name = "locale", length = 20, nullable = false)
	private String locale;

}