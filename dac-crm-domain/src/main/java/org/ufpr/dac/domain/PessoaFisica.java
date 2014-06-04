package org.ufpr.dac.domain;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;


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
	@Size(min=14, max=14, message="org.ufpr.dac.cpfMustHave14Digits")
	private String cpf;

	@Temporal(TemporalType.DATE)
	@Column(name="data_nascimento")
	private Date dataNascimento;

	private String email;

	@Column(name="pessoa_id")
	private Long pessoa;

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

	public Date getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Long pessoa) {
		this.pessoa = pessoa;
	}

}