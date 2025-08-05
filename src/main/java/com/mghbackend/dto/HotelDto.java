package com.mghbackend.dto;

import com.mghbackend.entity.StatutCompte;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelDto {
	private Long id;

	@NotBlank(message = "Le nom de l'hôtel est obligatoire")
	@Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caractères")
	private String nom;

	@NotBlank(message = "L'email est obligatoire")
	@Email(message = "Format d'email invalide")
	private String email;

	private String telephone;
	private String adresse;
	private String logo;
	private StatutCompte statut;
	private LocalDateTime dateCreation;
	private LocalDateTime dateMiseAJour;
	private List<UserDto> employes;
}
