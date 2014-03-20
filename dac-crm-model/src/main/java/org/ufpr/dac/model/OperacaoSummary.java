package org.ufpr.dac.model;

public class OperacaoSummary extends AbstractSummary {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private double valorTotal;
	private TipoOperacao tipoOperacao;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public TipoOperacao getTipoOperacao() {
		return tipoOperacao;
	}
	public void setTipoOperacao(TipoOperacao tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

}
