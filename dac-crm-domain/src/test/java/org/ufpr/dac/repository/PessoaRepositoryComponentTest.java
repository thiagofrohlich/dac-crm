package org.ufpr.dac.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ufpr.dac.builder.PessoaDomainBuilder;
import org.ufpr.dac.domain.Pessoa;
import org.ufpr.dac.domain.PessoaFisica;
import org.ufpr.dac.domain.PessoaJuridica;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-test.xml"})
public class PessoaRepositoryComponentTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired private PessoaRepository pessoaRepository;
	
	@Test
	public void shouldSaveNewPessoaWithPessoaFisicaGivenValidPessoa() {
//		Given
		Pessoa p = newPessoaBuilder().asPessoaWithPessoaFisica();
		
//		When
		Long id = pessoaRepository.save(p).getRootId();
		
//		Then
		Pessoa result = pessoaRepository.findOne(id);
		
		assertPessoa(p, result);
		assertPessoaFisica(p.getPessoaFisica(), result.getPessoaFisica());
		assertNull(p.getPessoaJuridica());
	}

	@Test
	public void shouldSaveNewPessoaWithPessoaJuridicaGivenValidPessoa() {
//		Given
		Pessoa p = newPessoaBuilder().asPessoaWithPessoaJuridica();
		
//		When
		Long id = pessoaRepository.save(p).getRootId();
		
//		Then
		Pessoa result = pessoaRepository.findOne(id);
		
		assertPessoa(p, result);
		assertPessoaJuridica(p.getPessoaJuridica(), result.getPessoaJuridica());
		assertNull(p.getPessoaFisica());
	}
	
	@Test
	public void shouldUpdatePessoaFisicaGivenValidPessoa() {
//		Given
		Long id = newPessoaBuilder().persisted().asPessoaWithPessoaFisica().getRootId();
		Pessoa p = pessoaRepository.findOne(id);
		p.getPessoaFisica().setEmail("teste");
		
//		When
		id = pessoaRepository.save(p).getRootId();
		
//		Then
		Pessoa result = pessoaRepository.findOne(id);
		
		assertPessoa(p, result);
		assertPessoaFisica(p.getPessoaFisica(), result.getPessoaFisica());
		assertNull(p.getPessoaJuridica());
	}

	@Test
	public void shouldUpdatePessoaJuridicaGivenValidPessoa() {
//		Given
		Pessoa p = newPessoaBuilder().persisted().asPessoaWithPessoaJuridica();
		p.getPessoaJuridica().setAtivo(false);
		
//		When
		Long id = pessoaRepository.save(p).getRootId();
		
//		Then
		Pessoa result = pessoaRepository.findOne(id);
		
		assertPessoa(p, result);
		assertPessoaJuridica(p.getPessoaJuridica(), result.getPessoaJuridica());
		assertNull(p.getPessoaFisica());
	}
	
	@Test
	public void shouldDeletePessoaFisica() {
//		Given
		Pessoa p = newPessoaBuilder().persisted().asPessoaWithPessoaFisica();
		Long id = p.getRootId();
		
//		When
		pessoaRepository.delete(id);
		
//		Then
		Pessoa result = pessoaRepository.findOne(id);
		
		assertNull(result);
	}

	@Test
	public void shouldDeletePessoaJuridica() {
//		Given
		Pessoa p = newPessoaBuilder().persisted().asPessoaWithPessoaJuridica();
		Long id = p.getRootId();
		
//		When
		pessoaRepository.delete(id);
		
//		Then
		Pessoa result = pessoaRepository.findOne(id);
		
		assertNull(result);
	}
	
	@Test
	public void shouldRetrieveAllPessoas() {
//		Given
		newPessoaBuilder().withRootId(new Date().getTime()).persisted().asPessoa();
		newPessoaBuilder().withRootId(new Date().getTime()).persisted().asPessoa();
		
//		When
		List<Pessoa> result = pessoaRepository.findAll();
		
//		Then
		assertNotNull(result);
		assertFalse(result.isEmpty());
		assertTrue(result.size() >= 2);
	}
	
	private void assertPessoa(Pessoa expected, Pessoa result) {
		assertNotNull(result);
		assertNotNull(result.getRootId());
		assertEquals(expected.getNome(), result.getNome());
		assertNotNull(result.getEndereco());
		assertEquals(expected.getEndereco().getEndereco(), result.getEndereco().getEndereco());
	}
	
	private void assertPessoaFisica(PessoaFisica expected, PessoaFisica result) {
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(expected.getCpf(), result.getCpf());
		assertEquals(expected.getEmail(), result.getEmail());
	}
	
	private void assertPessoaJuridica(PessoaJuridica expected, PessoaJuridica result) {
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(expected.getAtivo(), result.getAtivo());
		assertEquals(expected.getCnpj(), result.getCnpj());
	}

	private PessoaDomainBuilder newPessoaBuilder() {
		return new PessoaDomainBuilder(pessoaRepository);
	}

}
