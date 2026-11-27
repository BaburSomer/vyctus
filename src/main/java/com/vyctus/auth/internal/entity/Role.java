package com.vyctus.auth.internal.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "roles", schema = "auth")
public class Role {

	@Id
	@GeneratedValue(generator = "UUID")
	@UuidGenerator
	@Column(name = "oid", updatable = false, nullable = false)
	private UUID oid;

	@NotNull
	@NotBlank
	@Size(max = 50)
	@Column(length = 50)
	private String name;

	private String description;

	@ToString.Exclude
	@OneToMany(mappedBy = "role")
	private final Set<UserRole> userRoles = new HashSet<>();
	
	public void addUser(User user) {
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(this);
		userRoles.add(userRole);
	}

	/* Audit fields */
	@Version
	private Integer version;

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
	
	
	
	@Override
	public Role clone () {
		Role cloned = new Role();
		
		cloned.setOid(this.getOid());
		cloned.setName(this.getName());
		cloned.setDescription(this.getDescription());
		cloned.setVersion(this.getVersion());
		cloned.setCreated(this.getCreated());
		cloned.setCreatedBy(this.getCreatedBy());
		cloned.setUpdated(this.getUpdated());
		cloned.setUpdatedBy(this.getUpdatedBy());
				
		return cloned;
	}
}