package org.ufpr.dac.model;

import java.util.List;

// FIXME Add operation and product complex type
public class NotaFiscalSummary implements Summary {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String observacao;
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

}
