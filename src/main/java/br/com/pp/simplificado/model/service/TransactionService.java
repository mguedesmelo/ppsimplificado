package br.com.pp.simplificado.model.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pp.simplificado.exception.BusinessException;
import br.com.pp.simplificado.model.data.Transaction;
import br.com.pp.simplificado.model.data.User;
import br.com.pp.simplificado.model.dto.TransactionDto;
import br.com.pp.simplificado.model.repository.TransactionRepository;

@Service
public class TransactionService extends BaseService {
	@Autowired
	private UserService userService;
	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private AuthorizeTransactionService authorizeTransactionService;
	@Autowired
	private NotificationService notificationService;

	@Transactional
	public Transaction save(TransactionDto transactionDto) throws BusinessException {
		User payer = this.userService.findById(transactionDto.payerId());
		User payee = this.userService.findById(transactionDto.payeeId());
		BigDecimal value = transactionDto.value();

		this.userService.validateTransaction(payer, value);

		if (this.authorizeTransactionService.authorize(payer, value) == false) {
			throw new BusinessException("transaction.not.authorized");
		}

		Transaction transaction = new Transaction(payer, payee, value);
		payer.setBalance(payer.getBalance().subtract(transactionDto.value()));
		payee.setBalance(payee.getBalance().add(transactionDto.value()));

		this.userService.save(payer);
		this.userService.save(payee);

		this.notificationService.notify(payer, "Transferência realizada com sucesso, foi debitado " + value + " da sua conta-corrente");
		this.notificationService.notify(payee, "Transferência realizada com sucesso, você recebeu " + value + " na sua conta-corrente");

		return this.transactionRepository.save(transaction);
	}

	public List<Transaction> findAll() {
		return this.transactionRepository.findAll();
	}
}
