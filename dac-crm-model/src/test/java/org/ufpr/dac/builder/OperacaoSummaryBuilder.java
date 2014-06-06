package org.ufpr.dac.builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ufpr.dac.model.NotaFiscalSummary;
import org.ufpr.dac.model.OperacaoSummary;
import org.ufpr.dac.model.PessoaFisicaSummary;
import org.ufpr.dac.model.PessoaSummary;
import org.ufpr.dac.model.ProdutoNfSummary;
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
		nf.setProdutosNf(makeDefaultProdutosNfList(nf.getId()));
		nf.setPessoa(makeDefaultPessoa());
		return nf;
	}
	private PessoaSummary makeDefaultPessoa() {
		PessoaSummary pessoa = new PessoaFisicaSummary();
		pessoa.setRootId(new Date().getTime());
		return pessoa;
	}
	private List<ProdutoNfSummary> makeDefaultProdutosNfList(Long nfId) {
		List<ProdutoNfSummary> produtosNf = new ArrayList<ProdutoNfSummary>();
		produtosNf.add(makeDefaultProdutoNf(nfId));
		return produtosNf;
	}
	private ProdutoNfSummary makeDefaultProdutoNf(Long nfId) {
		ProdutoNfSummary produtoNfSummary = new ProdutoNfSummary();
		produtoNfSummary.setProdutoId(new Date().getTime());
		produtoNfSummary.setNfId(nfId);
		produtoNfSummary.setQuantidade(valorTotal);
		return produtoNfSummary;
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
