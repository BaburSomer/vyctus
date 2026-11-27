package com.vyctus.auth.internal.entity;

import java.math.BigDecimal;
import java.sql.Date;
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
@Table(name = "users", schema = "auth")
public class User {

	@Id
	@GeneratedValue(generator = "UUID")
	@UuidGenerator
	@Column(name = "oid", updatable = false, nullable = false)
	private UUID oid;

	@NotNull
	@NotBlank
	@Size(max = 50)
	@Column(length = 50)
	private String firstName;

	@NotNull
	@NotBlank
	@Size(max = 50)
	@Column(length = 50)
	private String lastName;

	@NotNull
	@Column(name = "birthdate")
	private Date birthDate;

	@NotNull
	private BigDecimal height;

	@ToString.Exclude
	@OneToMany(mappedBy = "user")
	private final Set<UserRole> userRoles = new HashSet<>();

   public void addRole(Role role) {
       UserRole userRole = new UserRole();
       userRole.setUser(this);
       userRole.setRole(role);
       userRoles.add(userRole);
   }
	
	/* Audit fields */
	@Version
	private Integer version;

/*	Hibernate manages both fields:
		- @CreationTimestamp: Hibernate generates the creation timestamp.
		- @Version: Hibernate manages the optimistic-locking version.
		However, Jakarta Bean Validation can run before Hibernate has populated these fields.

		Consequently:
		1. Validation sees created == null and rejects the entity.
		2. If you manually set created, validation then sees version == null and rejects it.
 */
//	@NotNull
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
	public User clone() {
		User cloned = new User();
			
		cloned.setOid(this.getOid());
		cloned.setFirstName(this.getFirstName());
		cloned.setLastName(this.getLastName());
		cloned.setBirthDate(this.getBirthDate());
		cloned.setHeight(this.getHeight());
		cloned.setVersion(this.getVersion());
		cloned.setCreated(this.getCreated());
		cloned.setCreatedBy(this.getCreatedBy());
		cloned.setUpdated(this.getUpdated());
		cloned.setUpdatedBy(this.getUpdatedBy());
				
		return cloned;
	}
}