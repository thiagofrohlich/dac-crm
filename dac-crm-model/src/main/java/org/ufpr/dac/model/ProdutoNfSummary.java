package org.ufpr.dac.model;

public class ProdutoNfSummary implements Summary {

	private static final long serialVersionUID = 1L;
	
	private Long nfId;
	private Long produtoId;
	private Long quantidade;
	
	public Long getNfId() {
		return nfId;
	}
	public void setNfId(Long nfId) {
		this.nfId = nfId;
	}
	public Long getProdutoId() {
		return produtoId;
	}
	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

}
