package org.ufpr.dac.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * The persistent class for the pessoa_juridica database table.
 * 
 */
@Entity
@Table(name="pessoa_juridica")
@NamedQuery(name="PessoaJuridica.findAll", query="SELECT p FROM PessoaJuridica p")
public class PessoaJuridica implements Domain, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private Boolean ativo;

	@NotNull(message="org.ufpr.dac.cnpjCannotBeNull")
	@NotEmpty(message="org.ufpr.dac.cnpjCannotBeNull")
	@Size(min=18, max=18, message="org.ufpr.dac.cnpjMustHave18Digits")
	private String cnpj;

	@Column(name="pessoa_id")
	private Long pessoa;

	public PessoaJuridica() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Long getPessoa() {
		return pessoa;
	}

	public void setPessoa(Long pessoa) {
		this.pessoa = pessoa;
	}


}