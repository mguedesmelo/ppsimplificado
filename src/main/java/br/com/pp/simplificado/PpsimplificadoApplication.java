package br.com.pp.simplificado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PpsimplificadoApplication {
	public static void main(String[] args) {
		/**
		 * TODO Tarefas
		 * - Habilitar spring security para permitir autenticação e geração de tokens
		 * - RuntimeExceptions são melhores do que Exceptions para tratar exceções de negócio
		 * - Quando o desafio fala de transactions, provavelmente está falando de transações de banco de dados também
		 * - Retornar statuscode 500 é errado, geralmente retornamos ou 2xx = sucesso, ou 4xx = erro
		 * - Os serviços mockados seriam melhores aproveitados criando uma interface e uma classe que implementa
		 * - Usar FeignClient no lugar do RestTemplate
		 * - Docker para o banco de dados
		 * - Usar Notification Pattern ao inves de lançar exceções?
		 * - Usar um ci/cd com actions ou travis?
		 */
		SpringApplication.run(PpsimplificadoApplication.class, args);
	}
}
