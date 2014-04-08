package org.ufpr.dac.domain;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.List;


/**
 * The persistent class for the nota_fiscal database table.
 * 
 */
@Entity
@Table(name="nota_fiscal")
@NamedQuery(name="NotaFiscal.findAll", query="SELECT n FROM NotaFiscal n")
public class NotaFiscal implements Domain, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String observacao;

	@ManyToOne
	@NotNull(message="org.ufpr.dac.pessoaNfCannotBeNull")
	private Pessoa pessoa;

	@OneToMany(mappedBy="notaFiscal", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@NotNull(message="org.ufpr.dac.produtosNfCannotBeNull")
	private List<ProdutoNf> produtosNf;

	public NotaFiscal() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<ProdutoNf> getProdutosNf() {
		return produtosNf;
	}

	public void setProdutosNf(List<ProdutoNf> produtosNf) {
		this.produtosNf = produtosNf;
	}

	public ProdutoNf addProdutoNf(ProdutoNf produtoNf) {
		getProdutosNf().add(produtoNf);
		produtoNf.setNotaFiscal(this);

		return produtoNf;
	}

	public ProdutoNf removeProdutoNf(ProdutoNf produtoNf) {
		getProdutosNf().remove(produtoNf);
		produtoNf.setNotaFiscal(null);

		return produtoNf;
	}

}