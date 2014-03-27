package org.ufpr.dac.transformer.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.lang.reflect.InvocationTargetException;

import orc.ufpr.dac.transformer.impl.PessoaTransformer;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ufpr.dac.builder.PessoaDomainBuilder;
import org.ufpr.dac.builder.PessoaSummaryBuilder;
import org.ufpr.dac.domain.Pessoa;
import org.ufpr.dac.domain.PessoaFisica;
import org.ufpr.dac.domain.PessoaJuridica;
import org.ufpr.dac.model.PessoaFisicaSummary;
import org.ufpr.dac.model.PessoaJuridicaSummary;
import org.ufpr.dac.repository.PessoaRepository;

public class PessoaTransformerTest {

	private PessoaTransformer pessoaTransformer;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Before
	public void setUp() {
		pessoaTransformer = new PessoaTransformer();
	}
	
	@Test
	public void shouldTransformPessoaSummaryGivenValidPessoaFisicaSummary() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		PessoaFisicaSummary summary = newSummaryBuilder().asPessoaFisica();
		Pessoa domain = new Pessoa();
		
		pessoaTransformer.transform(summary, domain);

		assertNotNull(domain.getEndereco());
		assertEquals(summary.getEndereco().getEndereco(), domain.getEndereco().getEndereco());
		assertEquals(summary.getNome(), domain.getNome());
		assertNotNull(domain.getPessoaFisica());
		PessoaFisica pf = domain.getPessoaFisica();
		assertEquals(summary.getCpf(), pf.getCpf());
		assertEquals(summary.getDataNascimento(), pf.getDataNascimento());
		assertEquals(summary.getEmail(), pf.getEmail());
		assertNull(domain.getPessoaJuridica());
	}
	
	@Test
	public void shouldTransformPessoaSummaryGivenValidPessoaJuridicaSummary() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		PessoaJuridicaSummary summary = newSummaryBuilder().asPessoaJuridica();
		Pessoa domain = new Pessoa();
		
		pessoaTransformer.transform(summary, domain);

		assertNotNull(domain.getEndereco());
		assertEquals(summary.getEndereco().getEndereco(), domain.getEndereco().getEndereco());
		assertEquals(summary.getNome(), domain.getNome());
		assertNotNull(domain.getPessoaJuridica());
		PessoaJuridica pj = domain.getPessoaJuridica();
		assertEquals(summary.getAtivo(), pj.getAtivo());
		assertEquals(summary.getCnpj(), pj.getCnpj());
		assertNull(domain.getPessoaFisica());
	}
	
	@Test
	public void shouldTransformDomainGivenValidPessoaFisica() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		Pessoa pessoa = newBuilder().asPessoaWithPessoaFisica();
		PessoaFisicaSummary summary = new PessoaFisicaSummary();
		
		pessoaTransformer.transform(pessoa, summary);
		
		assertNotNull(summary.getEndereco());
		assertEquals(pessoa.getEndereco().getEndereco(), summary.getEndereco().getEndereco());
		assertEquals(pessoa.getNome(), summary.getNome());
		PessoaFisica pf = pessoa.getPessoaFisica();
		assertEquals(pf.getCpf(), summary.getCpf());
		assertEquals(pf.getDataNascimento(), summary.getDataNascimento());
		assertEquals(pf.getEmail(), summary.getEmail());
	}
	
	@Test
	public void shouldTransformDomainGivenValidPessoaJuridica() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		Pessoa pessoa = newBuilder().asPessoaWithPessoaJuridica();
		PessoaJuridicaSummary summary = new PessoaJuridicaSummary();
		
		pessoaTransformer.transform(pessoa, summary);
		
		assertNotNull(summary.getEndereco());
		assertEquals(pessoa.getEndereco().getEndereco(), summary.getEndereco().getEndereco());
		assertEquals(pessoa.getNome(), summary.getNome());
		PessoaJuridica pj = pessoa.getPessoaJuridica();
		assertEquals(pj.getAtivo(), summary.getAtivo());
		assertEquals(pj.getCnpj(), summary.getCnpj());
	}
	
	public PessoaSummaryBuilder newSummaryBuilder() {
		return new PessoaSummaryBuilder();
	}
	
	public PessoaDomainBuilder newBuilder() {
		return new PessoaDomainBuilder(pessoaRepository);
	}
	
}
