package org.ufpr.dac.repository;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ufpr.dac.builder.OperacaoDomainBuilder;
import org.ufpr.dac.domain.NotaFiscal;
import org.ufpr.dac.domain.Operacao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-test.xml"})
public class OperacaoRepositoryComponentTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired private OperacaoRepository operacaoRepository;
	
	@Test
	public void shouldCreateNewOperacao() {
//		Given
		Operacao compra = newBuilder().asCompra();
		
//		When
		Long id = operacaoRepository.save(compra).getId();
		
//		Then
		Operacao result = operacaoRepository.findOne(id);
		
		assertOperacao(compra, result);
	}
	
	@Test
	public void shouldUpdateOperacao() {
//		Given
		Long id = newBuilder().persisted().asVenda().getId();
		Operacao venda = operacaoRepository.findOne(id);
		venda.setValorTotal(1.99);
		
//		When
		id = operacaoRepository.save(venda).getId();
		
//		Then
		Operacao result = operacaoRepository.findOne(id);
		
		assertOperacao(venda, result);
		
	}
	
	@Test
	public void shouldDeleteOperacao() {
//		Given
		Long id = newBuilder().persisted().asCompra().getId();
		
//		When
		operacaoRepository.delete(id);
		
//		Then
		Operacao op = operacaoRepository.findOne(id);
		
		assertNull(op);
	}
	
	@Test public void shouldFindAll() {
//		Given
		newBuilder().withId(new Date().getTime()).persisted().asCompra();
		newBuilder().withId(new Date().getTime()).persisted().asVenda();
		
//		When
		List<Operacao> ops = operacaoRepository.findAll();
		
//		Then
		assertNotNull(ops);
		assertFalse(ops.isEmpty());
		assertTrue(ops.size() >= 2);
	}
	
	private void assertOperacao(Operacao compra, Operacao result) {
		assertNotNull(result);
		assertEquals(compra.getTipoOperacao(), result.getTipoOperacao());
		assertTrue(compra.getValorTotal() == result.getValorTotal());
		assertNf(compra.getNotaFiscal(), result.getNotaFiscal());
	}
	
	private void assertNf(NotaFiscal expected, NotaFiscal actual) {
		assertNotNull(actual);
		assertEquals(expected.getObservacao(), actual.getObservacao());
	}
	
	private OperacaoDomainBuilder newBuilder() {
		return new OperacaoDomainBuilder(operacaoRepository);
	}

}
