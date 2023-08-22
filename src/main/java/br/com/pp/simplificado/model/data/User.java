package br.com.pp.simplificado.model.data;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class User extends BaseEntity implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7764539611416669701L;

	@Column(name = "first_name", nullable = false)
	private String firstName;

    @Column(name = "last_name", nullable = false)
	private String lastName;

    @Column(name = "email", unique = true, nullable = false)
	private String email;

    @Column(name = "user_passwd", nullable = false)
	private String password;

    @Column(name = "balance", nullable = false)
	private BigDecimal balance;

	@Column(name = "document", unique = true, nullable = false)
	private String document;

    @Enumerated(EnumType.STRING)
	@Column(name = "user_type", nullable = false)
	private UserType userType;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;

    public User(UserDto userDto) {
    	this(userDto.firstName(), userDto.lastName(), userDto.email(), userDto.password(), 
    			userDto.balance(), userDto.document(), userDto.userType(), userDto.role());
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> toReturn = List.of(
				new SimpleGrantedAuthority(UserRole.USER.toString()));
		if (this.role.equals(UserRole.ADMIN)) {
			toReturn.add(new SimpleGrantedAuthority(UserRole.ADMIN.toString()));
		}
		return toReturn;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
