package org.ufpr.dac.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlRootElement
@XmlSeeAlso(PessoaJuridicaSummary.class)
@JsonIgnoreProperties(ignoreUnknown=true)
public class ProdutoSummary implements Summary {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String descricao;
	private double valorCompra;
	private double valorVenda;
	private PessoaJuridicaSummary fornecedor = new PessoaJuridicaSummary();
	private Double estoque;
	private Long qtd; 
	private String categoria;
	
	public Long getQtd() {
		return qtd;
	}
	public void setQtd(Long qtd) {
		this.qtd = qtd;
	}
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
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Double getEstoque() {
		return estoque;
	}
	public void setEstoque(Double estoque) {
		this.estoque = estoque;
	}

}
