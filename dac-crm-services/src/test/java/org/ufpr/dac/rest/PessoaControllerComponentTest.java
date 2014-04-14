package org.ufpr.dac.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.validation.ConstraintViolationException;

import orc.ufpr.dac.rest.PessoaController;
import orc.ufpr.dac.transformer.impl.PessoaTransformer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ufpr.dac.builder.PessoaDomainBuilder;
import org.ufpr.dac.builder.PessoaSummaryBuilder;
import org.ufpr.dac.domain.Pessoa;
import org.ufpr.dac.model.EnderecoSummary;
import org.ufpr.dac.model.PessoaFisicaSummary;
import org.ufpr.dac.model.PessoaSummary;
import org.ufpr.dac.repository.PessoaRepository;
import org.ufpr.dac.wrapper.PessoaWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-services-test.xml"})
public class PessoaControllerComponentTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private PessoaController pessoaController;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Test
	public void shouldCreatePessoaGivenValidPessoaSummary() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
//		Given
		PessoaSummary summary = newSummaryBuilder().asPessoaFisica();
		
//		When
		PessoaSummary result = pessoaController.create(summary);
		
//		Then
		assertPessoa(summary, result);
	}

	@Test
	public void shouldNotCreatePessoaGivenInvalidCpf() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
//		Given
		PessoaSummary summary = newSummaryBuilder().withCpf("").asPessoaFisica();
		
		try {
	//		When
			pessoaController.create(summary);
			fail();
		} catch (ConstraintViolationException cve) {
//			Then
			assertTrue(cve.getMessage().contains("org.ufpr.dac.cpfCannotBeNull"));
		}
		
	}

	@Test
	public void shouldUpdatePessoa() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
//		Given
		Pessoa pessoa = newDomainBuilder().persisted().asPessoa();
		PessoaSummary summary = new PessoaFisicaSummary();
		new PessoaTransformer().transform(pessoa, summary);
		
		summary.setNome("NEW TEST NAME");
		
//		When
		PessoaSummary result = pessoaController.update(summary);
		
//		Then
		assertPessoa(summary, result);
	}
	
	@Test
	public void shouldDeletePessoa() {
//		Given
		Long id = newDomainBuilder().persisted().asPessoa().getRootId();
		
//		When
		pessoaController.delete(id);
		
//		Then
		Pessoa pessoa = pessoaRepository.findOne(id);
		assertTrue(pessoa == null);
	}
	
	@Test
	public void shouldReturnPessoaGivenExistingId() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
//		Given
		Pessoa domain = newDomainBuilder().persisted().asPessoaWithPessoaFisica();
		PessoaSummary persisted = new PessoaFisicaSummary();
		new PessoaTransformer().transform(domain, persisted);
		
//		When
		PessoaSummary summary = pessoaController.getOne(domain.getRootId());
		
//		Then
		assertPessoa(persisted, summary);
	}
	
	@Test
	public void shouldReturnAllPessoas() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
//		Given
		newDomainBuilder().withId(new Date().getTime()).persisted().asPessoaWithPessoaFisica();
		newDomainBuilder().withId(new Date().getTime()).withCpf("111.111.111-11").persisted().asPessoaWithPessoaFisica();
		
//		When
		PessoaWrapper wrapper = pessoaController.getAll(0);
		
//		Then
		Long long0 = 0l;
		Long long1 = 1l;
		Long long2 = 2l;
		assertEquals(long0, wrapper.getCurrentPage());
		assertEquals(long2, wrapper.getFoundQuantity());
		assertEquals(long2, wrapper.getReturnedQuantity());
		assertEquals(long1, wrapper.getTotalPages());
	}
	
	private void assertPessoa(PessoaSummary expected, PessoaSummary actual) {
		assertNotNull(actual);
		assertNotNull(actual.getRootId());
		assertEquals(expected.getNome(), actual.getNome());
		assertEndereco(expected.getEndereco(), actual.getEndereco());
	}
	
	private void assertEndereco(EnderecoSummary expected, EnderecoSummary actual) {
		assertNotNull(actual);
		assertEquals(expected.getCep(), actual.getCep());
		assertEquals(expected.getCidade(), actual.getCidade());
		assertEquals(expected.getComplemento(), actual.getComplemento());
		assertEquals(expected.getEndereco(), actual.getEndereco());
		assertEquals(expected.getEstado(), actual.getEstado());
		assertEquals(expected.getNumero(), actual.getNumero());
		assertEquals(expected.getPais(), actual.getPais());
	}
	
	public PessoaSummaryBuilder newSummaryBuilder() {
		return new PessoaSummaryBuilder();
	}
	
	public PessoaDomainBuilder newDomainBuilder() {
		return new PessoaDomainBuilder(pessoaRepository);
	}
	
}
