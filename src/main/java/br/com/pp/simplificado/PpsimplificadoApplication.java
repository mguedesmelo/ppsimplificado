package br.com.pp.simplificado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PpsimplificadoApplication {
	public static void main(String[] args) {
		/**
		 * TODO habilitar spring security para permitir autenticação e geracao de tokens
		 */
		SpringApplication.run(PpsimplificadoApplication.class, args);
	}
}
