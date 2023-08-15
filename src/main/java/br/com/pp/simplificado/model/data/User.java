package br.com.pp.simplificado.model.data;

import java.math.BigDecimal;

import br.com.pp.simplificado.model.dto.UserDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7764539611416669701L;

	@Column(name = "first_name")
	private String firstName;

    @Column(name = "last_name")
	private String lastName;

    @Column(name = "email", unique = true)
	private String email;

    @Column(name = "user_passwd")
	private String password;

    @Column(name = "balance")
	private BigDecimal balance;

	@Column(name = "document", unique = true)
	private String document;

    @Enumerated(EnumType.STRING)
	private UserType userType;
   
    public User(UserDto userDto) {
    	this(userDto.firstName(), userDto.lastName(), userDto.email(), userDto.password(), 
    			userDto.balance(), userDto.document(), userDto.userType());
    }
}
