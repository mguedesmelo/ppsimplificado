package br.com.pp.simplificado.model.dto;

import java.math.BigDecimal;

import br.com.pp.simplificado.model.data.UserRole;
import br.com.pp.simplificado.model.data.UserType;

public record UserDto(String firstName, String lastName, String document, String email, 
		String password, BigDecimal balance, UserType userType, UserRole role) {
	// Empty
}
