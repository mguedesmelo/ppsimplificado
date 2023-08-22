package br.com.pp.simplificado.model.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.pp.simplificado.exception.BusinessException;
import br.com.pp.simplificado.model.data.User;
import br.com.pp.simplificado.model.data.UserType;
import br.com.pp.simplificado.model.dto.UserDto;
import br.com.pp.simplificado.model.repository.UserRepository;
import jakarta.persistence.LockModeType;

@Service
public class UserService extends BaseService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	public void validateTransaction(User payer, BigDecimal ammount) throws BusinessException {
		if (UserType.MERCHANT.equals(payer.getUserType())) {
			throw new BusinessException("user.merchant.cannot.transfer");
		}
		if (payer.getBalance().compareTo(ammount) < 0) {
			throw new BusinessException("user.balance.not.enough");
		}
	}

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public User findById(Long id) throws BusinessException {
		return this.userRepository.findById(id).orElseThrow(() -> new BusinessException("user.not.found"));
	}

	public User save(UserDto userDto) throws BusinessException {
		return this.save(new User(userDto));
	}

	public User save(User user) throws BusinessException {
		if (user.atInsertMode() && this.userRepository.findByDocument(user.getDocument()) != null) {
			if (this.userRepository.findByDocument(user.getDocument()) != null) {
				throw new BusinessException("user.document.exists");
			}
			String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
			user.setPassword(encryptedPassword);
		}
		return this.userRepository.save(user);
	}

	public List<User> findAll() {
		return this.userRepository.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.userRepository.findByEmail(username);
	}
}
