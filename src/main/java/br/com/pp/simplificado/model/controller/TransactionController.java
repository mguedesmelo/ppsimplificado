package br.com.pp.simplificado.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pp.simplificado.exception.BusinessException;
import br.com.pp.simplificado.model.data.Transaction;
import br.com.pp.simplificado.model.dto.TransactionDto;
import br.com.pp.simplificado.model.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	@Autowired
	private TransactionService transactionService;

	@PostMapping("/save")
	public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDto transactionDto) throws BusinessException {
		Transaction transaction = this.transactionService.save(transactionDto);
		return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Transaction>> findAll() {
		List<Transaction> toReturn = this.transactionService.findAll();
		return new ResponseEntity<List<Transaction>>(toReturn, HttpStatus.OK);
	}
}
