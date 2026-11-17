package com.vyctus.auth.internal.entity;

import java.sql.Timestamp;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(
    name = "user_roles",
    schema = "auth",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "idx_user_roles_1",
            columnNames = {"user_oid", "role_oid"}
        )
    }
)
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserRole {
	@Id
	@GeneratedValue(generator = "UUID")
	@UuidGenerator
	@Column(name = "oid", length = 36, columnDefinition = "UUID", updatable = false, nullable = false)
	private UUID oid;

	@ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "user_oid", nullable = false)
   private User user;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "role_oid", nullable = false)
   private Role role;

	@Version
	private Integer version;

	@NotNull
	@CreationTimestamp
	private Timestamp created;

	@NotNull
	@NotBlank
	@Size(max = 50)
	@Column(length = 50)
	private String createdBy;

	@UpdateTimestamp
	private Timestamp updated;

	@Size(max = 50)
	@Column(length = 50)
	private String updatedBy;
}