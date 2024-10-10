package br.com.fiap.greencycle.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User implements UserDetails{
	
	@Id
    private String id;
	
	@JsonProperty("idUsuario")
	@Field("idUsuario")
	private Long idUsuario;
	
	@JsonProperty("nome")
	@Field("nome")
	private String nome;
	
	@JsonProperty("email")
	@Field("email")
    private String email;
	
	@JsonProperty("senha")
	@Field("senha")
    private String senha;
	
	@JsonProperty("role")
	@Field("role")
	private UserRole role;
	
	
	// Configurou o UserRole no mongoDB?
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (this.role == UserRole.ADMIN) {
			return List.of(
					new SimpleGrantedAuthority("ROLE_ADMIN"),
					new SimpleGrantedAuthority("ROLE_USER")
			);
		} else {
			return List.of(new SimpleGrantedAuthority("ROLE_USER"));
		}
	}
	
	@Override
	public String getPassword() {
		return this.senha;
	}
	
	@Override
	public String getUsername() {
		return this.email;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
	
    public void setSenha(String senha) {
        this.senha = senha;
    }

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;		
	}

	public String getEmail() {
		return this.nome;
	}

	public void setEmail(String email) {
		this.email = email;		
	}

	public String getSenha() {
		return this.senha;
	}

	public UserRole getRole() {
		return this.role;
	}

	public void setRole(UserRole role) {
		this.role = role;		
	}
	
	
	
}
