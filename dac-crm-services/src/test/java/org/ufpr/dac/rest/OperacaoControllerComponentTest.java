package org.ufpr.dac.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import orc.ufpr.dac.rest.OperacaoController;
import orc.ufpr.dac.transformer.impl.OperacaoTransformer;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ufpr.dac.builder.OperacaoDomainBuilder;
import org.ufpr.dac.builder.OperacaoSummaryBuilder;
import org.ufpr.dac.domain.Operacao;
import org.ufpr.dac.model.NotaFiscalSummary;
import org.ufpr.dac.model.OperacaoSummary;
import org.ufpr.dac.repository.OperacaoRepository;
import org.ufpr.dac.repository.PessoaRepository;
import org.ufpr.dac.wrapper.OperacaoWrapper;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-services-test.xml"})
public class OperacaoControllerComponentTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private OperacaoController operacaoController;
	@Autowired
	private OperacaoRepository operacaoRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Test
	public void shouldCreateOperacaoGivenValidOperacaoSummary() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
//		Given
		OperacaoSummary summary = newSummaryBuilder().asCompra();
		
//		When
		OperacaoSummary result = operacaoController.create(summary);
		
//		Then
		assertOperacao(summary, result);
	}

	@Test
	public void shouldUpdateOperacao() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
//		Given
		Operacao Operacao = newDomainBuilder().persisted().asVenda();
		OperacaoSummary summary = new OperacaoSummary();
		new OperacaoTransformer().transform(Operacao, summary);
		
		summary.setValorTotal(11.11);;
		
//		When
		OperacaoSummary result = operacaoController.update(summary);
		
//		Then
		assertOperacao(summary, result);
	}
	
	@Test
	public void shouldDeleteOperacao() {
//		Given
		Long id = newDomainBuilder().persisted().asCompra().getId();
		
//		When
		operacaoController.delete(id);
		
//		Then
		Operacao Operacao = operacaoRepository.findOne(id);
		assertTrue(Operacao == null);
	}
	
	@Test
	public void shouldReturnOperacaoGivenExistingId() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
//		Given
		Operacao domain = newDomainBuilder().persisted().asVenda();
		OperacaoSummary persisted = new OperacaoSummary();
		new OperacaoTransformer().transform(domain, persisted);
		
//		When
		OperacaoSummary summary = operacaoController.getOne(domain.getId());
		
//		Then
		assertOperacao(persisted, summary);
	}
	
	@Test
	public void shouldReturnAllOperacaos() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
//		Given
		newDomainBuilder().withId(new Date().getTime()).persisted().asCompra();
		newDomainBuilder().withId(new Date().getTime()).persisted().asVenda();
		
//		When
		OperacaoWrapper wrapper = operacaoController.getAll(0);
		
//		Then
		Long long0 = 0l;
		Long long1 = 1l;
		Long long2 = 2l;
		assertEquals(long0, wrapper.getCurrentPage());
		assertEquals(long2, wrapper.getFoundQuantity());
		assertEquals(long2, wrapper.getReturnedQuantity());
		assertEquals(long1, wrapper.getTotalPages());
	}
	
	private void assertOperacao(OperacaoSummary expected, OperacaoSummary actual) {
		assertNotNull(actual);
		assertNotNull(actual.getId());
		assertEquals(expected.getTipoOperacao().getKey(), actual.getTipoOperacao().getKey());
		assertNotaFiscal(expected.getNotaFiscal(), actual.getNotaFiscal());
	}
	
	private void assertNotaFiscal(NotaFiscalSummary expected, NotaFiscalSummary actual) {
		assertNotNull(actual);
		assertEquals(expected.getObservacao(), actual.getObservacao());
	}
	
	public OperacaoSummaryBuilder newSummaryBuilder() {
		return new OperacaoSummaryBuilder();
	}
	
	public OperacaoDomainBuilder newDomainBuilder() {
		return new OperacaoDomainBuilder(operacaoRepository, pessoaRepository);
	}
	
}
