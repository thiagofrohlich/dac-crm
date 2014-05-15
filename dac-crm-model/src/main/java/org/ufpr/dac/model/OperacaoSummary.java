package org.ufpr.dac.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OperacaoSummary implements Summary {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private double valorTotal;
	private TipoOperacao tipoOperacao;
	private NotaFiscalSummary notaFiscal = new NotaFiscalSummary();
	private Date dataOperacao;
	
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
	public Date getDataOperacao() {
		return dataOperacao;
	}
	public void setDataOperacao(Date dataOperacao) {
		this.dataOperacao = dataOperacao;
	}

}
