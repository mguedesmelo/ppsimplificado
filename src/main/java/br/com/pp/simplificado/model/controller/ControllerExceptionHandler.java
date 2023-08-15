package br.com.pp.simplificado.model.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.pp.simplificado.exception.BusinessException;
import br.com.pp.simplificado.exception.ExceptionDto;

@RestControllerAdvice
public class ControllerExceptionHandler {
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ExceptionDto> threatBusinessException(BusinessException businessException) {
		ExceptionDto exceptionDto = new ExceptionDto("400", businessException.getMessage());
		return ResponseEntity.badRequest().body(exceptionDto);
	}

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<ExceptionDto> threatThrowable(Throwable throwable) {
		ExceptionDto exceptionDto = new ExceptionDto("400", "Erro inesperado");
		return ResponseEntity.badRequest().body(exceptionDto);
	}
}
