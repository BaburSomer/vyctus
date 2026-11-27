/**
 * @Autor: 	 Babür Somer
 * @Date:	 25.November 2026
 * @Version: 1.0
 *
 */
package com.vyctus.auth.internal.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vyctus.auth.internal.entity.Role;

public interface RoleRepository extends JpaRepository<Role, UUID>{

}
