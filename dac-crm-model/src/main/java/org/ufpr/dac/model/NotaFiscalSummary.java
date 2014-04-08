package org.ufpr.dac.model;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NotaFiscalSummary implements Summary {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String observacao;
	private Date dataOperacao;
	private PessoaSummary pessoa;
	private List<ProdutoNfSummary> produtosNf;
	
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
	public Date getDataOperacao() {
		return dataOperacao;
	}
	public void setDataOperacao(Date dataOperacao) {
		this.dataOperacao = dataOperacao;
	}
	public PessoaSummary getPessoa() {
		return pessoa;
	}
	public void setPessoa(PessoaSummary pessoa) {
		this.pessoa = pessoa;
	}

}
