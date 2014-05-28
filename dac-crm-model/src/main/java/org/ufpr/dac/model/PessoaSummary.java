package org.ufpr.dac.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso({PessoaFisicaSummary.class, PessoaJuridicaSummary.class, UsuarioSummary.class})
public abstract class PessoaSummary implements Summary {

	private static final long serialVersionUID = 1L;
	
	protected Long rootId;
	protected String nome;
	protected String telefone="";
	protected EnderecoSummary endereco = new EnderecoSummary();
	
	
	public abstract boolean isPessoaFisica();
	public abstract boolean isPessoaJuridica();
	
	public Long getRootId() {
		return rootId;
	}
	public void setRootId(Long rootId) {
		this.rootId = rootId;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public EnderecoSummary getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoSummary endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
