package org.ufpr.dac.model;

// FIXME Add operation and product complex type
public class NotaFiscalSummary implements Summary {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String observacao;
	private OperacaoSummary operacao;
	private ProdutoNfSummary produtosNf;
	
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
	public OperacaoSummary getOperacao() {
		return operacao;
	}
	public void setOperacao(OperacaoSummary operacao) {
		this.operacao = operacao;
	}
	public ProdutoNfSummary getProdutosNf() {
		return produtosNf;
	}
	public void setProdutosNf(ProdutoNfSummary produtosNf) {
		this.produtosNf = produtosNf;
	}

}
