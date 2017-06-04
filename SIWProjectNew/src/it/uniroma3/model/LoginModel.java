package it.uniroma3.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginModel {

	@NotNull(message="Campo nullo")
	@Size(min=6, max=20, message="Deve contenere almeno 6 caratteri e fino ad un massimo di 20")
	private String password;
	
	@NotNull(message="Campo nullo")
	@Size(min=2, max=10, message="Il campo deve avere minimo 2 caratteri fino ad un massimo di 10")
	private String username;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
