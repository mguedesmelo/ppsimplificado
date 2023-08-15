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

import br.com.pp.simplificado.model.data.User;
import br.com.pp.simplificado.model.dto.UserDto;
import br.com.pp.simplificado.model.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/save")
	public ResponseEntity<User> save(@RequestBody UserDto userDto) {
		User user = this.userService.save(userDto);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<User>> findAll() {
		List<User> toReturn = this.userService.findAll();
		return new ResponseEntity<List<User>>(toReturn, HttpStatus.OK);
	}
}
