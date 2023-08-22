package br.com.pp.simplificado.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pp.simplificado.exception.BusinessException;
import br.com.pp.simplificado.model.data.User;
import br.com.pp.simplificado.model.dto.AuthenticationDto;
import br.com.pp.simplificado.model.dto.LoginResponseDto;
import br.com.pp.simplificado.model.dto.UserDto;
import br.com.pp.simplificado.model.service.TokenService;
import br.com.pp.simplificado.model.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private TokenService tokenService;

	@PostMapping("/save")
	public ResponseEntity<User> save(@RequestBody UserDto userDto) throws BusinessException {
		User user = this.userService.save(userDto);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<User>> findAll() {
		List<User> toReturn = this.userService.findAll();
		return new ResponseEntity<List<User>>(toReturn, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid AuthenticationDto authenticationDto) {
		var userNamePassword = new UsernamePasswordAuthenticationToken(
				authenticationDto.email(), authenticationDto.password());
		var auth = this.authenticationManager.authenticate(userNamePassword);
		var token = this.tokenService.generateToken((User) auth.getPrincipal());

		return ResponseEntity.ok(new LoginResponseDto(token));
	}
}
