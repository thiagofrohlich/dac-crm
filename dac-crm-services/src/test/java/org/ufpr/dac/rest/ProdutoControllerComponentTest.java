package org.ufpr.dac.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import orc.ufpr.dac.rest.ProdutoController;
import orc.ufpr.dac.transformer.impl.ProdutoTransformer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ufpr.dac.builder.PessoaDomainBuilder;
import org.ufpr.dac.builder.ProdutoDomainBuilder;
import org.ufpr.dac.builder.ProdutoSummaryBuilder;
import org.ufpr.dac.domain.PessoaJuridica;
import org.ufpr.dac.domain.Produto;
import org.ufpr.dac.model.PessoaJuridicaSummary;
import org.ufpr.dac.model.ProdutoSummary;
import org.ufpr.dac.repository.PessoaRepository;
import org.ufpr.dac.repository.ProdutoRepository;
import org.ufpr.dac.wrapper.ProdutoWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-services-test.xml"})
public class ProdutoControllerComponentTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private ProdutoController produtoController;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Test
	public void shouldCreateProdutoGivenValidProdutoSummary() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
//		Given
		ProdutoSummary summary = newSummaryBuilder().asProduto();
		
//		When
		ProdutoSummary result = produtoController.create(summary);
		
//		Then
		assertProduto(summary, result);
	}

	@Test
	public void shouldUpdateProduto() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
//		Given
		Produto Produto = newDomainBuilder().withFornecedor(givenValidFornecedorDomain()).persisted().asProduto();
		ProdutoSummary summary = new ProdutoSummary();
		new ProdutoTransformer().transform(Produto, summary);
		
		summary.setDescricao("NEW TEST NAME");
		
//		When
		ProdutoSummary result = produtoController.update(summary);
		
//		Then
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(summary.getDescricao(), result.getDescricao());
		assertTrue(summary.getValorCompra() == result.getValorCompra());
		assertTrue(summary.getValorVenda() == result.getValorVenda());
	}
	
	@Test
	public void shouldDeleteProduto() {
//		Given
		Long id = newDomainBuilder().withFornecedor(givenValidFornecedorDomain()).persisted().asProduto().getId();
		
//		When
		produtoController.delete(id);
		
//		Then
		Produto Produto = produtoRepository.findOne(id);
		assertTrue(Produto == null);
	}
	
	@Test
	public void shouldReturnProdutoGivenExistingId() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
//		Given
		Produto domain = newDomainBuilder().withFornecedor(givenValidFornecedorDomain()).persisted().asProduto();
		ProdutoSummary persisted = new ProdutoSummary();
		new ProdutoTransformer().transform(domain, persisted);
		
//		When
		ProdutoSummary summary = produtoController.getOne(domain.getId());
		
//		Then
		assertProduto(persisted, summary);
	}
	
	@Test
	public void shouldReturnAllProdutos() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
//		Given
		PessoaJuridica fornecedor = givenValidFornecedorDomain();
		newDomainBuilder().withId(new Date().getTime()).withFornecedor(fornecedor).persisted().asProduto();
		newDomainBuilder().withId(new Date().getTime()).withFornecedor(fornecedor).persisted().asProduto();
		
//		When
		ProdutoWrapper wrapper = produtoController.getAll(0);
		
//		Then
		Long long0 = 0l;
		Long long1 = 1l;
		Long long2 = 2l;
		assertEquals(long0, wrapper.getCurrentPage());
		assertEquals(long2, wrapper.getFoundQuantity());
		assertEquals(long2, wrapper.getReturnedQuantity());
		assertEquals(long1, wrapper.getTotalPages());
	}
	
	private void assertProduto(ProdutoSummary expected, ProdutoSummary actual) {
		assertNotNull(actual);
		assertNotNull(actual.getId());
		assertEquals(expected.getDescricao(), actual.getDescricao());
		assertTrue(expected.getValorCompra() == actual.getValorCompra());
		assertTrue(expected.getValorVenda() == actual.getValorVenda());
		assertFornecedor(expected.getFornecedor(), actual.getFornecedor());
	}
	
	private void assertFornecedor(PessoaJuridicaSummary expected, PessoaJuridicaSummary actual) {
		assertNotNull(actual);
		assertEquals(expected.getAtivo(), actual.getAtivo());
		assertEquals(expected.getCnpj(), actual.getCnpj());
	}
	
	private ProdutoSummaryBuilder newSummaryBuilder() {
		return new ProdutoSummaryBuilder();
	}
	
	private ProdutoDomainBuilder newDomainBuilder() {
		return new ProdutoDomainBuilder(produtoRepository);
	}
	
	private PessoaJuridica givenValidFornecedorDomain() {
		return new PessoaDomainBuilder(pessoaRepository).persisted().asPessoaWithPessoaJuridica().getPessoaJuridica();
	}
	
	
}
