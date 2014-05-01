package org.ufpr.dac.domain;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Domain, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull(message="org.ufpr.dac.loginCannotBeNull")
	@NotEmpty(message="org.ufpr.dac.loginCannotBeNull")
	private String login;

	@NotNull(message="org.ufpr.dac.senhaCannotBeNull")
	@NotEmpty(message="org.ufpr.dac.senhaCannotBeNull")
	private String senha;

	@OneToOne
	@JoinColumn(name="pessoa_id")
	private Pessoa pessoaUsuario;
	
	private String acessos;

	public Usuario() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Pessoa getPessoaUsuario() {
		return this.pessoaUsuario;
	}

	public void setPessoaUsuario(Pessoa pessoa) {
		this.pessoaUsuario = pessoa;
	}
	
	public String getAcessos() {
		return acessos;
	}

	public void setAcessos(String acessos) {
		this.acessos = acessos;
	}

}