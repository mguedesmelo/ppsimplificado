package br.com.pp.simplificado;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.pp.simplificado.model.data.User;
import br.com.pp.simplificado.model.data.UserRole;
import br.com.pp.simplificado.model.data.UserType;
import br.com.pp.simplificado.model.service.UserService;

@SpringBootApplication
public class PpsimplificadoApplication implements CommandLineRunner {
	@Autowired
	private UserService userService;
	
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

	@Override
	public void run(String... args) throws Exception {
		this.userService.save(new User("Amazon", "Shopping", "amazon@example.com", "123", 
				new BigDecimal(20),	"94916446453", UserType.CUSTOMER, UserRole.USER));
		this.userService.save(new User("Marcio", "Melo", "mguedesmelo@example.com", "123", 
				new BigDecimal(50),	"11111111111", UserType.MERCHANT, UserRole.USER));
	}
}
