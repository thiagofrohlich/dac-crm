package org.ufpr.dac.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PessoaFisicaSummary extends PessoaSummary {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String cpf;
	private Date dataNascimento;
	private String email;
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public boolean isPessoaFisica() {
		return true;
	}
	@Override
	public boolean isPessoaJuridica() {
		return false;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
