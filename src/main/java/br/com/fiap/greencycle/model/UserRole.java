package br.com.fiap.greencycle.model;


public enum UserRole {
	
	ADMIN("admin"),
	USER("user");
	
	private String role;
	
	UserRole(String role){
		this.role = role;
	}

}
