package org.ufpr.dac.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * The persistent class for the pessoa_fisica database table.
 * 
 */
@Entity
@Table(name="pessoa_fisica")
@NamedQuery(name="PessoaFisica.findAll", query="SELECT p FROM PessoaFisica p")
public class PessoaFisica implements Domain, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull(message="org.ufpr.dac.cpfCannotBeNull")
	@NotEmpty(message="org.ufpr.dac.cpfCannotBeNull")
	private String cpf;

	
	private String email;
	@OneToOne
	@JoinColumn(name="pessoa_id", referencedColumnName="id")
	private Pessoa pessoa;

	public PessoaFisica() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}