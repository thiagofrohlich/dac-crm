package org.ufpr.dac.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ufpr.dac.builder.PessoaDomainBuilder;
import org.ufpr.dac.builder.ProdutoDomainBuilder;
import org.ufpr.dac.domain.PessoaJuridica;
import org.ufpr.dac.domain.Produto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-test.xml"})
public class ProdutoRepositoryComponentTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired private ProdutoRepository produtoRepository;
	@Autowired private PessoaDomainBuilder pessoaDomainBuilder;
	private PessoaJuridica validFornecedor;
	
	@Before
	public void setUp() {
		validFornecedor = pessoaDomainBuilder.persisted().asPessoaWithPessoaJuridica().getPessoaJuridica();
	}
	
	@Test
	public void shouldCreateProduto() {
//		Given
		Produto produto = newBuilder().withFornecedor(validFornecedor).asProduto();
		
//		When
		Long id = produtoRepository.save(produto).getId();
		
//		Then
		Produto result = produtoRepository.findOne(id);
		
		assertProduto(produto, result);
	}
	
	@Test
	public void shouldUpdateProduto() {
//		Given
		Produto produto = newBuilder().withFornecedor(validFornecedor).persisted().asProduto();
		produto.setDescricao("MODIFIED");
		
//		When
		Long id = produtoRepository.save(produto).getId();
		
//		Then
		Produto result = produtoRepository.findOne(id);
		
		assertProduto(produto, result);
	}
	
	@Test
	public void shouldDeleteProduto() {
//		Given
		Long id = newBuilder().withFornecedor(validFornecedor).persisted().asProduto().getId();
		
//		When
		produtoRepository.delete(id);
		
//		Then
		Produto result = produtoRepository.findOne(id);
		
		assertNull(result);
	}
	
	@Test public void shouldFindAllProdutos() {
//		Given
		newBuilder().withId(new Date().getTime()).withFornecedor(validFornecedor).persisted().asProduto();
		newBuilder().withId(new Date().getTime()).withFornecedor(validFornecedor).persisted().asProduto();
		
//		When
		List<Produto> results = produtoRepository.findAll();
		
//		Then
		assertNotNull(results);
		assertFalse(results.isEmpty());
		assertTrue(results.size() >= 2);
	}

	private void assertProduto(Produto expected, Produto actual) {
		assertNotNull(actual);
		assertEquals(expected.getDescricao(), actual.getDescricao());
		assertTrue(expected.getValorCompra() == actual.getValorCompra());
		assertTrue(expected.getValorVenda() == actual.getValorVenda());
		assertFornecedor(expected.getFornecedor(), actual.getFornecedor());
	}
	
	private void assertFornecedor(PessoaJuridica expected, PessoaJuridica actual) {
		assertNotNull(actual);
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getAtivo(), actual.getAtivo());
		assertEquals(expected.getCnpj(), actual.getCnpj());
	}
	
	private ProdutoDomainBuilder newBuilder() {
		return new ProdutoDomainBuilder(produtoRepository);
	}

}
