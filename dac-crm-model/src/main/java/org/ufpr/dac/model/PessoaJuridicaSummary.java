package org.ufpr.dac.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PessoaJuridicaSummary extends PessoaSummary {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Boolean ativo;
	private String cnpj;
	private String email;
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	@Override
	public boolean isPessoaFisica() {
		return false;
	}
	@Override
	public boolean isPessoaJuridica() {
		return true;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
