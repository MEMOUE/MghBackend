package com.mghbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "permissions")
@Data
@EqualsAndHashCode(callSuper = true)
public class Permission extends BaseEntity {

	@Enumerated(EnumType.STRING)
	@Column(unique = true, nullable = false)
	private TypePermission name;

	private String description;
}
