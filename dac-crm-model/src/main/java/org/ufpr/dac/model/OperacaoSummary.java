package org.ufpr.dac.model;

public class OperacaoSummary implements Summary {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private double valorTotal;
	private TipoOperacao tipoOperacao;
	private NotaFiscalSummary notaFiscal;
	
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
	public NotaFiscalSummary getNotaFiscal() {
		return notaFiscal;
	}
	public void setNotaFiscal(NotaFiscalSummary notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

}
