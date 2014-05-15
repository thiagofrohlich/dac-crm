package org.ufpr.dac.transformer.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;

import orc.ufpr.dac.transformer.impl.OperacaoTransformer;

import org.junit.Before;
import org.junit.Test;
import org.ufpr.dac.builder.OperacaoDomainBuilder;
import org.ufpr.dac.builder.OperacaoSummaryBuilder;
import org.ufpr.dac.domain.NotaFiscal;
import org.ufpr.dac.domain.Operacao;
import org.ufpr.dac.domain.Pessoa;
import org.ufpr.dac.model.NotaFiscalSummary;
import org.ufpr.dac.model.OperacaoSummary;
import org.ufpr.dac.model.PessoaSummary;

public class OperacaoTransformerTest {
	
	private OperacaoTransformer operacaoTransformer;

	@Before
	public void setUp() {
		operacaoTransformer = new OperacaoTransformer();
	}
	
	@Test
	public void shouldTransformCompraIntoOperacaoDomain() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
//		Given
		OperacaoSummary compra = newSummaryBuilder().asCompra();
		Operacao compraDomain = new Operacao();
		
//		When
		operacaoTransformer.transform(compra, compraDomain);
		
//		Then
		assertOperacao(compra, compraDomain);
	}

	@Test
	public void shouldTransformVendaIntoOperacaoDomain() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
//		Given
		OperacaoSummary venda = newSummaryBuilder().asVenda();
		Operacao vendaDomain = new Operacao();
		
//		When
		operacaoTransformer.transform(venda, vendaDomain);
		
//		Then
		assertOperacao(venda, vendaDomain);
	}
	
	@Test
	public void shouldTransformCompraDomainIntoSummary() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
//		Given
		Operacao compraDomain = newDomainBuilder().asCompra();
		OperacaoSummary compra = new OperacaoSummary();
		
//		When
		operacaoTransformer.transform(compraDomain, compra);
		
//		Then
		assertOperacao(compraDomain, compra);
	}

	@Test
	public void shouldTransformVendaDomainIntoSummary() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
//		Given
		Operacao vendaDomain = newDomainBuilder().asVenda();
		OperacaoSummary venda = new OperacaoSummary();
		
//		When
		operacaoTransformer.transform(vendaDomain, venda);
		
//		Then
		assertOperacao(vendaDomain, venda);
	}

	private void assertOperacao(Operacao expected, OperacaoSummary actual) {
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getTipoOperacao(), actual.getTipoOperacao().getKey());
		assertTrue(expected.getValorTotal() == actual.getValorTotal());
		assertNotaFiscal(expected.getNotaFiscal(), actual.getNotaFiscal());
	}
	
	private void assertOperacao(OperacaoSummary expected, Operacao actual) {
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getTipoOperacao().getKey(), actual.getTipoOperacao());
		assertTrue(expected.getValorTotal() == actual.getValorTotal());
		assertNotaFiscal(expected.getNotaFiscal(), actual.getNotaFiscal());
	}
	
	private void assertNotaFiscal(NotaFiscalSummary expected, NotaFiscal actual) {
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getObservacao(), actual.getObservacao());
		assertPessoa(expected.getPessoa(), actual.getPessoa());
		assertEquals(expected.getProdutosNf().size(), actual.getProdutosNf().size());
	}
	
	private void assertNotaFiscal(NotaFiscal expected, NotaFiscalSummary actual) {
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getObservacao(), actual.getObservacao());
		assertPessoa(expected.getPessoa(), actual.getPessoa());
		assertEquals(expected.getProdutosNf().size(), actual.getProdutosNf().size());
	}
	
	private void assertPessoa(PessoaSummary expected, Pessoa actual) {
		assertNotNull(actual);
		assertEquals(expected.getRootId(), actual.getRootId());
	}
	
	private void assertPessoa(Pessoa expected, PessoaSummary actual) {
		assertNotNull(actual);
		assertEquals(expected.getRootId(), actual.getRootId());
	}
	
	private OperacaoSummaryBuilder newSummaryBuilder() {
		return new OperacaoSummaryBuilder();
	}
	
	private OperacaoDomainBuilder newDomainBuilder() {
		return new OperacaoDomainBuilder(null);
	}

}
