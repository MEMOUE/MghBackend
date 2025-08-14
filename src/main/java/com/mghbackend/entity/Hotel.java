package com.mghbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hotels")
@Data
@EqualsAndHashCode(callSuper = true)
public class Hotel extends BaseEntity {

	@Column(nullable = false, length = 100)
	private String name;

	@Column(nullable = false, unique = true, length = 100)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(length = 20)
	private String phone;

	@Column(columnDefinition = "TEXT")
	private String address;

	@Column(length = 255)
	private String logoUrl;

	@Column(length = 50)
	private String taxNumber;

	@Column(nullable = false)
	private Boolean active = true;

	private LocalDateTime subscriptionEnd;

	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<User> users = new ArrayList<>();
}