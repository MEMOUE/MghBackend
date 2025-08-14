package com.mghbackend.service;

import com.mghbackend.dto.LoginRequest;
import com.mghbackend.dto.LoginResponse;
import com.mghbackend.entity.*;
import com.mghbackend.repository.HotelRepository;
import com.mghbackend.repository.UserRepository;
import com.mghbackend.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

	private final HotelRepository hotelRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	public LoginResponse authenticate(LoginRequest request) {
		if ("HOTEL".equals(request.getAccountType())) {
			return authenticateHotel(request);
		} else if ("USER".equals(request.getAccountType())) {
			return authenticateUser(request);
		} else {
			throw new RuntimeException("Type de compte invalide");
		}
	}

	private LoginResponse authenticateHotel(LoginRequest request) {
		Hotel hotel = hotelRepository.findByEmailAndActiveTrue(request.getEmail())
				.orElseThrow(() -> new RuntimeException("Email ou mot de passe incorrect"));

		if (!passwordEncoder.matches(request.getPassword(), hotel.getPassword())) {
			throw new RuntimeException("Email ou mot de passe incorrect");
		}

		String token = jwtUtil.generateToken(hotel.getEmail(), "HOTEL", hotel.getId());

		LoginResponse response = new LoginResponse();
		response.setToken(token);
		response.setType("Bearer");
		response.setId(hotel.getId());
		response.setEmail(hotel.getEmail());
		response.setName(hotel.getName());
		response.setAccountType("HOTEL");
		response.setHotelId(hotel.getId());
		response.setHotelName(hotel.getName());

		return response;
	}

	private LoginResponse authenticateUser(LoginRequest request) {
		User user = userRepository.findByEmailAndActiveTrue(request.getEmail())
				.orElseThrow(() -> new RuntimeException("Email ou mot de passe incorrect"));

		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new RuntimeException("Email ou mot de passe incorrect");
		}

		String token = jwtUtil.generateToken(user.getEmail(), "USER", user.getId());

		Set<TypePermission> permissions = user.getRoles().stream()
				.flatMap(role -> role.getPermissions().stream())
				.collect(Collectors.toSet());

		LoginResponse response = new LoginResponse();
		response.setToken(token);
		response.setType("Bearer");
		response.setId(user.getId());
		response.setEmail(user.getEmail());
		response.setName(user.getUsername());
		response.setFirstName(user.getFirstName());
		response.setLastName(user.getLastName());
		response.setAccountType("USER");
		response.setHotelId(user.getHotel().getId());
		response.setHotelName(user.getHotel().getName());
		response.setRoles(user.getRoles().stream()
				.map(Role::getName)
				.collect(Collectors.toSet()));
		response.setPermissions(permissions);

		return response;
	}
}
