package br.com.fiap.greencycle.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "login")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Login {
	
	@Id
    private String idLogin;
	
	@JsonProperty("idUsuario")
    @Field("id_usuario")
    private Long idUsuario;

	@JsonProperty("emailLogin")
    @Field("email_login")
    private String emailLogin;

	@JsonProperty("senhaLogin")
    @Field("senha_login")
    private String senhaLogin;
    
	@Field("role")
	private UserRole role;
	
	public String getEmailLogin() {
        return emailLogin;
    }

    public void setEmailLogin(String emailLogin) {
        this.emailLogin = emailLogin;
    }

    public String getSenhaLogin() {
        return senhaLogin;
    }

    public void setSenhaLogin(String senhaLogin) {
        this.senhaLogin = senhaLogin;
    }

	public UserRole getRole() {
        return role;

	}

	public void setRole(UserRole role) {
        this.role = role;
	}
	

}
