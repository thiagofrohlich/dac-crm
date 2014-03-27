package org.ufpr.dac.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ufpr.dac.domain.PessoaJuridica;
import org.ufpr.dac.domain.Produto;
import org.ufpr.dac.repository.ProdutoRepository;

@Component
public class ProdutoDomainBuilder {

	private final ProdutoRepository produtoRepository;

	@Autowired
	public ProdutoDomainBuilder(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	private Long id = 1l;
	private String descricao = "TESTE";
	private double valorCompra = 99.99;
	private double valorVenda = 99.99;
	private boolean persisted = false;
	private PessoaJuridica fornecedor ;
	
	public Produto asProduto() {
		Produto produto = new Produto();
		produto.setDescricao(descricao);
		produto.setId(id);
		produto.setValorCompra(valorCompra);
		produto.setValorVenda(valorVenda);
		produto.setFornecedor(fornecedor != null ? fornecedor : makeDefaultFornecedor());
		return persist(produto);
	}

	private PessoaJuridica makeDefaultFornecedor() {
		PessoaJuridica pj = new PessoaJuridica();
		pj.setId(id);
		pj.setAtivo(true);
		pj.setCnpj("99.999.999/9999-99");
		return pj;
	}

	private Produto persist(Produto p) {
		if(persisted) {
			return produtoRepository.saveAndFlush(p);
		}
		return p;
	}

	public ProdutoDomainBuilder withId(Long id) {
		this.id = id;
		return this;
	}
	public ProdutoDomainBuilder withDescricao(String descricao) {
		this.descricao = descricao;
		return this;
	}
	public ProdutoDomainBuilder withValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
		return this;
	}
	public ProdutoDomainBuilder withValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
		return this;
	}
	public ProdutoDomainBuilder persisted() {
		this.persisted = true;
		return this;
	}
	public ProdutoDomainBuilder withFornecedor(PessoaJuridica fornecedor) {
		this.fornecedor = fornecedor;
		return this;
	}
	
	
}
