package org.ufpr.dac.domain;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

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

	@OneToMany(mappedBy="id.notaFiscal", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
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
		produtoNf.getId().setNotaFiscal(id);

		return produtoNf;
	}

	public ProdutoNf removeProdutoNf(ProdutoNf produtoNf) {
		getProdutosNf().remove(produtoNf);
		produtoNf.getId().setNotaFiscal(null);

		return produtoNf;
	}

}