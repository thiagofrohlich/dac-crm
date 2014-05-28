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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * The persistent class for the endereco database table.
 * 
 */
@Entity
@NamedQuery(name="Endereco.findAll", query="SELECT e FROM Endereco e")
public class Endereco implements Domain, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull(message="org.ufpr.dac.cepMustNotBeNull")
	@NotEmpty(message="org.ufpr.dac.cepMustNotBeNull")
	private String cep;

	@NotNull(message="org.ufpr.dac.cidadeMustNotBeNull")
	@NotEmpty(message="org.ufpr.dac.cidadeMustNotBeNull")
	private String cidade;

	private String complemento;

	@NotNull(message="org.ufpr.dac.enderecoMustNotBeNull")
	@NotEmpty(message="org.ufpr.dac.enderecoMustNotBeNull")
	private String endereco;

	@NotNull(message="org.ufpr.dac.estadoMustNotBeNull")
	@NotEmpty(message="org.ufpr.dac.estadoMustNotBeNull")
	private String estado;

	@NotNull(message="org.ufpr.dac.numeroMustNotBeNull")
	@NotEmpty(message="org.ufpr.dac.numeroMustNotBeNull")
	private String numero;

	@NotNull(message="org.ufpr.dac.paisMustNotBeNull")
	@NotEmpty(message="org.ufpr.dac.paisMustNotBeNull")
	private String pais;
	
	@Column(name="pessoa_id")
	private Long pessoaId;

	public Endereco() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Long getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(Long pessoaId) {
		this.pessoaId = pessoaId;
	}

	
	

}