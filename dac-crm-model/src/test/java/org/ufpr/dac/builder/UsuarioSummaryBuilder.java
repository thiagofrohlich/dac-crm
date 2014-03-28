package org.ufpr.dac.builder;

import org.ufpr.dac.model.UsuarioSummary;

public class UsuarioSummaryBuilder {

	private String login = "login";
	private String senha = "password";
	
	public UsuarioSummary asUsuario() {
		UsuarioSummary usuario = new UsuarioSummary();
		usuario.setLogin(login);
		usuario.setSenha(senha);
		return usuario;
	}
	
	public UsuarioSummaryBuilder withLogin(String login) {
		this.login = login;
		return this;
	}
	public UsuarioSummaryBuilder withSenha(String senha) {
		this.senha = senha;
		return this;
	}
	
}
