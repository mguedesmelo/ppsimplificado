package br.com.pp.simplificado.model.repository;

import org.springframework.security.core.userdetails.UserDetails;

import br.com.pp.simplificado.model.data.User;

public interface UserRepository extends BaseRepository<User> {
	public User findByDocument(String document);
	
	public UserDetails findByEmail(String email);
}
