package org.ufpr.dac.transformer.impl;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;

import org.junit.Before;
import org.junit.Test;
import org.ufpr.dac.builder.ProdutoDomainBuilder;
import org.ufpr.dac.builder.ProdutoSummaryBuilder;
import org.ufpr.dac.domain.PessoaJuridica;
import org.ufpr.dac.domain.Produto;
import org.ufpr.dac.model.PessoaJuridicaSummary;
import org.ufpr.dac.model.ProdutoSummary;

import orc.ufpr.dac.transformer.impl.ProdutoTransformer;

public class ProdutoTransformerTest {
	
	private ProdutoTransformer produtoTransformer;
	
	@Before
	public void setUp() {
		produtoTransformer = new ProdutoTransformer();
	}
	
	@Test
	public void shouldTransformProdutoSummaryIntoDomain() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
//		Given
		ProdutoSummary summary = newSummaryBuilder().asProduto();
		Produto domain = new Produto();
		
//		When
		produtoTransformer.transform(summary, domain);
		
//		Then
		assertProduto(summary, domain);
	}

	@Test
	public void shouldTransformProdutoDomainIntoSummary() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
//		Given
		Produto domain = newDomainBuilder().asProduto();
		ProdutoSummary summary = new ProdutoSummary();
		
//		When
		produtoTransformer.transform(domain, summary);
		
//		Then
		assertProduto(domain, summary);
	}

	private void assertProduto(ProdutoSummary expected, Produto actual) {
		assertEquals(expected.getDescricao(), actual.getDescricao());
		assertEquals(expected.getId(), actual.getId());
		assertTrue(expected.getValorCompra() == actual.getValorCompra());
		assertTrue(expected.getValorVenda() == actual.getValorVenda());
		assertFornecedor(expected.getFornecedor(), actual.getFornecedor());
	}
	
	private void assertFornecedor(PessoaJuridicaSummary expected, PessoaJuridica actual) {
		assertEquals(expected.getAtivo(), actual.getAtivo());
		assertEquals(expected.getCnpj(), actual.getCnpj());
	}
	
	private void assertProduto(Produto expected, ProdutoSummary actual) {
		assertEquals(expected.getDescricao(), actual.getDescricao());
		assertEquals(expected.getId(), actual.getId());
		assertTrue(expected.getValorCompra() == actual.getValorCompra());
		assertTrue(expected.getValorVenda() == actual.getValorVenda());
		assertFornecedor(expected.getFornecedor(), actual.getFornecedor());
	}
	
	private void assertFornecedor(PessoaJuridica expected, PessoaJuridicaSummary actual) {
		assertEquals(expected.getAtivo(), actual.getAtivo());
		assertEquals(expected.getCnpj(), actual.getCnpj());
	}
	
	private ProdutoSummaryBuilder newSummaryBuilder() {
		return new ProdutoSummaryBuilder();
	}
	
	private ProdutoDomainBuilder newDomainBuilder() {
		return new ProdutoDomainBuilder(null);
	}

}
