package br.com.pp.simplificado.model.service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.pp.simplificado.configuration.Constants;
import br.com.pp.simplificado.model.data.User;

@Service
public class AuthorizeTransactionService {
	@Autowired
	private RestTemplate restTemplate;

	@SuppressWarnings("rawtypes")
	public boolean authorize(User payer, BigDecimal ammount) {
		ResponseEntity<Map> response = this.restTemplate.getForEntity(
				Constants.URL_REST_AUTH_TRANSACTION, Map.class);
		return (response.getStatusCode().equals(HttpStatus.OK) && response.getBody().get("message").toString().equalsIgnoreCase("Autorizado"));
	}
}
