package org.ufpr.dac.builder;

import java.util.Date;

import org.ufpr.dac.model.PessoaJuridicaSummary;
import org.ufpr.dac.model.ProdutoSummary;

public class ProdutoSummaryBuilder {
	
	private Long id = 1l;
	private String descricao = "TEST";
	private double valorCompra = 9.99;
	private double valorVenda = 9.99;
	private double valorEstoque = 1d;
	private PessoaJuridicaSummary fornecedor;
	
	public ProdutoSummary asProduto() {
		ProdutoSummary produto = new ProdutoSummary();
		produto.setDescricao(descricao);
		produto.setFornecedor(fornecedor != null ? fornecedor : makeDefaultFornecedor());
		produto.setId(id);
		produto.setValorCompra(valorCompra);
		produto.setValorVenda(valorVenda);
		produto.setEstoque(valorEstoque);
		return produto;
	}
	
	private PessoaJuridicaSummary makeDefaultFornecedor() {
		PessoaJuridicaSummary pj = new PessoaJuridicaSummary();
		pj.setRootId(new Date().getTime());
		return pj;
	}

	public ProdutoSummaryBuilder withId(Long id) {
		this.id = id;
		return this;
	}
	public ProdutoSummaryBuilder withDescricao(String descricao) {
		this.descricao = descricao;
		return this;
	}
	public ProdutoSummaryBuilder withValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
		return this;
	}
	public ProdutoSummaryBuilder withValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
		return this;
	}
	public ProdutoSummaryBuilder withFornecedor(PessoaJuridicaSummary fornecedor) {
		this.fornecedor = fornecedor;
		return this;
	}

}
