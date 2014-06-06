package org.ufpr.dac.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


/**
 * The persistent class for the produto_nf database table.
 * 
 */
@Entity
@Table(name="produto_nf")
@NamedQuery(name="ProdutoNf.findAll", query="SELECT p FROM ProdutoNf p")
public class ProdutoNf implements Domain, Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProdutoNfPK id;

	@NotNull(message="org.ufpr.dac.quantidadeProdutoNfCannotBeNull")
//	@NotEmpty(message="org.ufpr.dac.quantidadeProdutoNfCannotBeNull")
	private double quantidade;

	/*//bi-directional many-to-one association to NotaFiscal
	@ManyToOne
	@JoinColumn(name="nf_id", insertable=false, updatable=false)
	private NotaFiscal notaFiscal;*/

/*	//bi-directional many-to-one association to Produto
	@ManyToOne
	@JoinColumn(insertable=false, updatable=false)
	private Produto produto;*/

	public ProdutoNf() {
	}

	public ProdutoNfPK getId() {
		return this.id;
	}

	public void setId(ProdutoNfPK id) {
		this.id = id;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	

	/*public NotaFiscal getNotaFiscal() {
		return this.notaFiscal;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}*/

	/*public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}*/

}