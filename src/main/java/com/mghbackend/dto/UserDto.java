package com.mghbackend.dto;

import com.mghbackend.entity.StatutCompte;
import com.mghbackend.entity.TypeRole;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	private Long id;

	@NotBlank(message = "Le nom est obligatoire")
	@Size(min = 2, max = 50, message = "Le nom doit contenir entre 2 et 50 caractères")
	private String nom;

	@NotBlank(message = "Le prénom est obligatoire")
	@Size(min = 2, max = 50, message = "Le prénom doit contenir entre 2 et 50 caractères")
	private String prenom;

	@NotBlank(message = "L'email est obligatoire")
	@Email(message = "Format d'email invalide")
	private String email;

	private String telephone;
	private StatutCompte statut;
	private Long hotelId;
	private String hotelNom;
	private Set<TypeRole> roles;
	private LocalDateTime dateCreation;
	private LocalDateTime dateMiseAJour;
}
