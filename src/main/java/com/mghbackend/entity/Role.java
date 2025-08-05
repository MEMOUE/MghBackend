package com.mghbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.security.Permission;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {

	@Column(nullable = false, unique = true, length = 50)
	private String name;

	@Column(length = 255)
	private String description;

	@ManyToMany(mappedBy = "roles")
	private Set<User> users;

	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	@CollectionTable(name = "role_permissions", joinColumns = @JoinColumn(name = "role_id"))
	@Column(name = "permission")
	private Set<Permission> permissions;
}
