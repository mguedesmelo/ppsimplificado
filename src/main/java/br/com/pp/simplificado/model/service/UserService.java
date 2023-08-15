package br.com.pp.simplificado.model.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pp.simplificado.exception.BusinessException;
import br.com.pp.simplificado.model.data.User;
import br.com.pp.simplificado.model.data.UserType;
import br.com.pp.simplificado.model.dto.UserDto;
import br.com.pp.simplificado.model.repository.UserRepository;

@Service
public class UserService extends BaseService {
	@Autowired
	private UserRepository userRepository;

	public void validateTransaction(User payer, BigDecimal ammount) throws BusinessException {
		if (UserType.MERCHANT.equals(payer.getUserType())) {
			throw new BusinessException("Não é permitido ao lojista realizar transferências");
		}
		if (payer.getBalance().compareTo(ammount) < 0) {
			throw new BusinessException("Saldo insuficiente");
		}
	}
	
	public User findById(Long id) throws BusinessException {
		return this.userRepository.findById(id).orElseThrow(() -> new BusinessException("Usuário não localizado"));
	}

	public User save(UserDto userDto) throws BusinessException {
		return this.save(new User(userDto));
	}
	
	public User save(User user) throws BusinessException {
		if (this.userRepository.findByDocument(user.getDocument()) != null) {
			throw new BusinessException("Já existe um usuário com o documento informado");
		}
		return this.userRepository.save(user);
	}

	public List<User> findAll() {
		return this.userRepository.findAll();
	}
}
