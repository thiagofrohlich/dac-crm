package org.ufpr.dac.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.InvocationTargetException;

import orc.ufpr.dac.rest.PessoaController;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ufpr.dac.builder.PessoaSummaryBuilder;
import org.ufpr.dac.model.PessoaSummary;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-services-test.xml"})
public class PessoaControllerComponentTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private PessoaController pessoaController;
	
	@Test
	public void shouldCreatePessoaGivenValidPessoaSummary() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
//		Given
		PessoaSummary summary = newBuilder().asPessoaFisica();
		
//		When
		HttpEntity<PessoaSummary> entity = pessoaController.create(summary);
		PessoaSummary result = entity.getBody();
		
//		Then
		assertNotNull(result);
		assertNotNull(result.getRootId());
		assertEquals(summary.getNome(), result.getNome());
	}
	
	public PessoaSummaryBuilder newBuilder() {
		return new PessoaSummaryBuilder();
	}

}
