package br.com.pp.simplificado.model.dto;

import java.math.BigDecimal;

public record TransactionDto(Long payerId, Long payeeId, BigDecimal value) {
	// Empty
}
