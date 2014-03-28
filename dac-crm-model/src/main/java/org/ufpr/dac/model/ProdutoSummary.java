package org.ufpr.dac.model;

public class ProdutoSummary implements Summary {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String descricao;
	private double valorCompra;
	private double valorVenda;
	private PessoaJuridicaSummary fornecedor;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValorCompra() {
		return valorCompra;
	}
	public void setValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
	}
	public double getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}
	public PessoaJuridicaSummary getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(PessoaJuridicaSummary fornecedor) {
		this.fornecedor = fornecedor;
	}

}
