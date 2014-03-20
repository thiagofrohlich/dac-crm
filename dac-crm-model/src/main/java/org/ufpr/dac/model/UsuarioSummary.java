package org.ufpr.dac.model;

public class UsuarioSummary extends PessoaSummary {

	private static final long serialVersionUID = 1L;
	
	private String login;
	private String senha;
	private PessoaSummary pessoa;

	@Override
	public boolean isPessoaFisica() {
		return pessoa.isPessoaFisica();
	}

	@Override
	public boolean isPessoaJuridica() {
		return pessoa.isPessoaJuridica();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public PessoaSummary getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaSummary pessoa) {
		this.pessoa = pessoa;
	}

}
