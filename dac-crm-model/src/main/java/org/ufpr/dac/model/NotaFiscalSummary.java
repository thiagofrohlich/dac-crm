package org.ufpr.dac.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlRootElement
@XmlSeeAlso({PessoaFisicaSummary.class, PessoaJuridicaSummary.class})
public class NotaFiscalSummary implements Summary {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String observacao="";
	private PessoaSummary pessoa;
	private List<ProdutoNfSummary> produtosNf = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public List<ProdutoNfSummary> getProdutosNf() {
		return produtosNf;
	}
	public void setProdutosNf(List<ProdutoNfSummary> produtosNf) {
		this.produtosNf = produtosNf;
	}
	
	
	public PessoaSummary getPessoa() {
		return pessoa;
	}
	public void setPessoa(PessoaSummary pessoa) {
		this.pessoa = pessoa;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
		result = prime * result
				+ ((produtosNf == null) ? 0 : produtosNf.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotaFiscalSummary other = (NotaFiscalSummary) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		if (produtosNf == null) {
			if (other.produtosNf != null)
				return false;
		} else if (!produtosNf.equals(other.produtosNf))
			return false;
		return true;
	}

}
