package org.ufpr.dac.builder;

import java.util.Date;

import org.ufpr.dac.model.NotaFiscalSummary;
import org.ufpr.dac.model.OperacaoSummary;
import org.ufpr.dac.model.TipoOperacao;

public class OperacaoSummaryBuilder {

	private Long id = 1l;
	private double valorTotal = 99.99;
	private NotaFiscalSummary notaFiscal;
	
	public OperacaoSummary asCompra() {
		OperacaoSummary compra = new OperacaoSummary();
		compra.setTipoOperacao(TipoOperacao.COMPRA);
		setDefaultFields(compra);
		return compra;
	}
	public OperacaoSummary asVenda() {
		OperacaoSummary venda = new OperacaoSummary();
		venda.setTipoOperacao(TipoOperacao.VENDA);
		setDefaultFields(venda);
		return venda;
	}
	private void setDefaultFields(OperacaoSummary op) {
		op.setId(id);
		op.setNotaFiscal(notaFiscal != null ? notaFiscal : makeDefaultNf(op));
		op.setValorTotal(valorTotal);
	}
	private NotaFiscalSummary makeDefaultNf(OperacaoSummary op) {
		NotaFiscalSummary nf = new NotaFiscalSummary();
		nf.setId(new Date().getTime());
		nf.setObservacao("TEST");
		return nf;
	}
	public OperacaoSummaryBuilder withId(Long id) {
		this.id = id;
		return this;
	}
	public OperacaoSummaryBuilder withValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
		return this;
	}
	public OperacaoSummaryBuilder withNotaFiscal(NotaFiscalSummary notaFiscal) {
		this.notaFiscal = notaFiscal;
		return this;
	}
	
}
