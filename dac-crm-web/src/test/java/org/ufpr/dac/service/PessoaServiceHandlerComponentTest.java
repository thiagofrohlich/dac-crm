package org.ufpr.dac.service;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.ufpr.dac.model.PessoaFisicaSummary;
import org.ufpr.dac.model.PessoaSummary;
import org.ufpr.dac.wrapper.PessoaWrapper;

public class PessoaServiceHandlerComponentTest {
	
	private PessoaServiceHandler pessoaServiceHandler;
	
	@Before
	public void setUp() {
		pessoaServiceHandler = new PessoaServiceHandler();
	}
	
	@Test
	public void shouldCallGetOne() {
		PessoaSummary ps = pessoaServiceHandler.getOne(32l);
		System.out.println(ps);
	}

	@Test
	public void shouldCallGetAll() {
		PessoaWrapper wrapper = pessoaServiceHandler.getAll(0);
		System.out.println(wrapper);
	}

	@Test
	public void shouldCallCreate() {
		PessoaSummary ps = new PessoaFisicaSummary();
		pessoaServiceHandler.create(ps);
	}

	@Test
	public void shouldCallUpdate() {
		pessoaServiceHandler.update(new PessoaFisicaSummary());
	}

	@Test
	public void shouldCallDelete() {
		pessoaServiceHandler.delete(1l);
	}

}
