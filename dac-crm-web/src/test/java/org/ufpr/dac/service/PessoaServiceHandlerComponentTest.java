package org.ufpr.dac.service;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.ufpr.dac.model.PessoaFisicaSummary;
import org.ufpr.dac.model.PessoaSummary;

//@Ignore
public class PessoaServiceHandlerComponentTest {
	
	private PessoaServiceHandler pessoaServiceHandler;
	
	@Before
	public void setUp() {
		pessoaServiceHandler = new PessoaServiceHandler();
	}
	
	@Test
	public void shouldCallGetOne() {
		pessoaServiceHandler.getOne(1l);
	}

	@Test
	public void shouldCallGetAll() {
		pessoaServiceHandler.getAll(0);
	}

	@Test
	public void shouldCallCreate() {
		PessoaSummary ps = new PessoaFisicaSummary();
		ps.setNome("AAAA");
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
