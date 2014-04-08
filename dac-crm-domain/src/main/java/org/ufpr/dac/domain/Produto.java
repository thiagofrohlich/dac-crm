package org.ufpr.dac.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * The persistent class for the produto database table.
 * 
 */
@Entity
@NamedQuery(name="Produto.findAll", query="SELECT p FROM Produto p")
public class Produto implements Domain, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull(message="org.ufpr.dac.descricaoProdutoCannotBeNull")
	@NotEmpty(message="org.ufpr.dac.descricaoProdutoCannotBeNull")
	private String descricao;

	@Column(name="valor_compra")
	@NotNull(message="org.ufpr.dac.valorCompraCannotBeNull")
	@NotEmpty(message="org.ufpr.dac.valorCompraCannotBeNull")
	private double valorCompra;

	@Column(name="valor_venda")
	@NotNull(message="org.ufpr.dac.valorVendaCannotBeNull")
	@NotEmpty(message="org.ufpr.dac.valorVendaCannotBeNull")
	private double valorVenda;

	//bi-directional many-to-one association to PessoaJuridica
	@ManyToOne
	@JoinColumn(name="pessoa_juridica_id")
	@NotNull(message="org.ufpr.dac.fornecedorCannotBeNull")
	@NotEmpty(message="org.ufpr.dac.fornecedorCannotBeNull")
	private PessoaJuridica fornecedor;

	public Produto() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValorCompra() {
		return this.valorCompra;
	}

	public void setValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
	}

	public double getValorVenda() {
		return this.valorVenda;
	}

	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public PessoaJuridica getFornecedor() {
		return this.fornecedor;
	}

	public void setFornecedor(PessoaJuridica pessoaJuridica) {
		this.fornecedor = pessoaJuridica;
	}

}