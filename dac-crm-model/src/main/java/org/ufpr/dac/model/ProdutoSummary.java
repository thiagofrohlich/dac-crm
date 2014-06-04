package org.ufpr.dac.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProdutoSummary implements Serializable {

	private static final long serialVersionUID = 1L;

	public ProdutoSummary() {
		super();
	}
	
	private Long id;
	private String descricao;
	private double valorCompra;
	private double valorVenda;
	private PessoaJuridicaSummary fornecedor = new PessoaJuridicaSummary();
	private Double estoque;
	private Long qtd = new Long(0); 
	
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
	public Double getEstoque() {
		return estoque;
	}
	public void setEstoque(Double estoque) {
		this.estoque = estoque;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((estoque == null) ? 0 : estoque.hashCode());
		result = prime * result
				+ ((fornecedor == null) ? 0 : fornecedor.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((qtd == null) ? 0 : qtd.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorCompra);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorVenda);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoSummary other = (ProdutoSummary) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (estoque == null) {
			if (other.estoque != null)
				return false;
		} else if (!estoque.equals(other.estoque))
			return false;
		if (fornecedor == null) {
			if (other.fornecedor != null)
				return false;
		} else if (!fornecedor.equals(other.fornecedor))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (qtd == null) {
			if (other.qtd != null)
				return false;
		} else if (!qtd.equals(other.qtd))
			return false;
		if (Double.doubleToLongBits(valorCompra) != Double
				.doubleToLongBits(other.valorCompra))
			return false;
		if (Double.doubleToLongBits(valorVenda) != Double
				.doubleToLongBits(other.valorVenda))
			return false;
		return true;
	}

}
