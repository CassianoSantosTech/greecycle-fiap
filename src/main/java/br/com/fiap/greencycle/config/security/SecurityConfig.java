package br.com.fiap.greencycle.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Classe responsável por definir os filtros de segurança durante a tentativa de consumo 

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private VerificarToken verificarToken;
	
	@Bean
	public SecurityFilterChain filtrarCadeiaDeSeguranca(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				
				// Desabilitando o csrf
				.csrf(csrf -> csrf.disable())
				
				// Definindo que a sessão será criada de forma Stateless
				.sessionManagement(sessao -> sessao.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				
				.authorizeHttpRequests(autorizar ->
					autorizar
						.requestMatchers(HttpMethod.POST, "/auth").permitAll() // Para autenticação do usuário
						.requestMatchers(HttpMethod.POST, "/auth/users").permitAll() // Para cadastrar um usuário novo
						.requestMatchers(HttpMethod.POST, "/api/coletaextra").permitAll() // Para cadastrar uma nova coleta extra
						.requestMatchers(HttpMethod.GET, "/api/coletaextra").permitAll()  // Para listar todas as coletas extras
						.requestMatchers(HttpMethod.GET, "/api/coletaextra/{idColetaE}").permitAll() // Para buscar coleta extra por Id
						.requestMatchers(HttpMethod.DELETE, "/api/coletaextra/{idColetaE}").permitAll() // Para deletar coleta extra por Id
						.requestMatchers(HttpMethod.PUT, "/api/coletaextra/{idColetaE}").permitAll() // Para atualizar coleta extra por Id
						.requestMatchers(HttpMethod.GET, "/api/user").permitAll()  // Para listar todas os usuarios
						.requestMatchers(HttpMethod.POST, "/api/user").permitAll() // Para cadastrar um novo usuário
						.requestMatchers(HttpMethod.PUT, "/api/user/{idUser}").permitAll()  // Para atualizar um usuário por Id
						.requestMatchers(HttpMethod.DELETE, "/api/user/{idUser}").permitAll() // Para deletar usuário por Id
						.requestMatchers(HttpMethod.GET, "/api/login").permitAll()  // Para listar todas os logins
						.requestMatchers(HttpMethod.POST, "/api/login").permitAll() // Para cadastrar um novo login
						.requestMatchers(HttpMethod.PUT, "/api/login/{idLogin}").permitAll() // Para atualizar um login por Id
						.requestMatchers(HttpMethod.DELETE, "/api/login/{idLogin}").permitAll() // Para deletar login por Id
						.requestMatchers(HttpMethod.GET, "/api/notificacoes").permitAll()  // Para listar todas as notificações
						.requestMatchers(HttpMethod.POST, "/api/notificacoes").permitAll() // Para cadastrar uma nova notificação
						.requestMatchers(HttpMethod.PUT, "/api/notificacoes/{idNotificacoes}").permitAll() // Para atualizar uma notificação
						.requestMatchers(HttpMethod.DELETE, "/api/notificacoes/{idNotificacoes}").permitAll() // Para deletar uma notificação
						.requestMatchers(HttpMethod.POST, "/api/coletafixa").permitAll() // Para cadastrar uma nova coleta fixa
						.requestMatchers(HttpMethod.GET, "/api/coletafixa").permitAll()  // Para listar todas as coletas fixa
						.requestMatchers(HttpMethod.GET, "/api/coletafixa/{idColetaF}").permitAll() // Para buscar coleta fixa por Id
						.requestMatchers(HttpMethod.DELETE, "/api/coletafixa/{idColetaF}").permitAll() // Para deletar coleta fixa por Id
						.requestMatchers(HttpMethod.PUT, "/api/coletafixa/{idColetaF}").permitAll() // Para atualizar coleta fixa por Id

						.anyRequest().authenticated()
				)
				.addFilterBefore(verificarToken, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
