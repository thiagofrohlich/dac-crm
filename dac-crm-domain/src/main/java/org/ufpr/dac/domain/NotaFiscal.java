package org.ufpr.dac.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the nota_fiscal database table.
 * 
 */
@Entity
@Table(name="nota_fiscal")
@NamedQuery(name="NotaFiscal.findAll", query="SELECT n FROM NotaFiscal n")
public class NotaFiscal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String observacao;

	@ManyToOne
	private Pessoa pessoa;

	@OneToOne(mappedBy="notaFiscal")
	private Operacao operacao;

	@OneToMany(mappedBy="notaFiscal")
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

	public Operacao getOperacao() {
		return operacao;
	}

	public void setOperacao(Operacao operacao) {
		this.operacao = operacao;
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