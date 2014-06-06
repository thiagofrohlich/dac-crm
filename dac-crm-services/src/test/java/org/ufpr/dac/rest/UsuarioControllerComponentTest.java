package org.ufpr.dac.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import orc.ufpr.dac.rest.UsuarioController;
import orc.ufpr.dac.transformer.impl.UsuarioTransformer;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ufpr.dac.builder.PessoaDomainBuilder;
import org.ufpr.dac.builder.UsuarioDomainBuilder;
import org.ufpr.dac.builder.UsuarioSummaryBuilder;
import org.ufpr.dac.domain.Pessoa;
import org.ufpr.dac.domain.Usuario;
import org.ufpr.dac.model.UsuarioSummary;
import org.ufpr.dac.repository.PessoaRepository;
import org.ufpr.dac.repository.UsuarioRepository;
import org.ufpr.dac.wrapper.UsuarioWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-services-test.xml"})
public class UsuarioControllerComponentTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private UsuarioController usuarioController;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Test
	public void shouldCreateUsuarioGivenValidUsuarioSummary() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
//		Given
		givenValidPersistedPessoa();
		UsuarioSummary summary = newSummaryBuilder().asUsuario();
		
//		When
		UsuarioSummary result = usuarioController.create(summary);
		
//		Then
		assertUsuario(summary, result);
	}

	@Test
	@Ignore
	public void shouldUpdateUsuario() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
//		Given
		Usuario usuario = newDomainBuilder().withPessoa(givenValidPersistedPessoa()).persisted().asUsuario();
		UsuarioSummary summary = new UsuarioSummary();
		new UsuarioTransformer().transform(usuario, summary);
		
		summary.setNome("NEW TEST NAME");
		
//		When
		UsuarioSummary result = usuarioController.update(summary);
		
//		Then
		assertUsuario(summary, result);
	}
	
	@Test
	public void shouldDeleteUsuario() {
//		Given
		Long id = newDomainBuilder().withPessoa(givenValidPersistedPessoa()).persisted().asUsuario().getId();
		
//		When
		usuarioController.delete(id);
		
//		Then
		Usuario Usuario = usuarioRepository.findOne(id);
		assertTrue(Usuario == null);
	}
	
	@Test
	public void shouldReturnUsuarioGivenExistingId() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
//		Given
		Usuario domain = newDomainBuilder().withPessoa(givenValidPersistedPessoa()).persisted().asUsuario();
		UsuarioSummary persisted = new UsuarioSummary();
		new UsuarioTransformer().transform(domain, persisted);
		
//		When
		UsuarioSummary summary = usuarioController.getOne(domain.getId());
		
//		Then
		assertUsuario(persisted, summary);
	}
	
	@Test
	@Ignore
	public void shouldReturnAllUsuarios() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
//		Given
		Pessoa pessoa = givenValidPersistedPessoa();
		Pessoa pessoa2 = givenValidPersistedPessoa("TEST ADDRESS");
		newDomainBuilder().withId(new Date().getTime()).withPessoa(pessoa).persisted().asUsuario();
		newDomainBuilder().withId(new Date().getTime()).withPessoa(pessoa2).withLogin("anothertestlogin").persisted().asUsuario();
		
//		When
		UsuarioWrapper wrapper = usuarioController.getAll(0);
		
//		Then
		Long long0 = 0l;
		Long long1 = 1l;
		Long long2 = 2l;
		assertEquals(long0, wrapper.getCurrentPage());
		assertEquals(long2, wrapper.getFoundQuantity());
		assertEquals(long2, wrapper.getReturnedQuantity());
		assertEquals(long1, wrapper.getTotalPages());
	}
	
	@Test
	public void shouldReturnTrueGivenValidLoginAndPassword() throws IllegalAccessException, InstantiationException, InvocationTargetException {
//		Given
		Pessoa pessoa = givenValidPersistedPessoa();
		Usuario usuario = newDomainBuilder().withId(new Date().getTime()).withPessoa(pessoa).persisted().asUsuario();
		
		UsuarioSummary summary = new UsuarioSummary();
		summary.setLogin(usuario.getLogin());
		
//		When
		UsuarioSummary user = usuarioController.getByLogin(usuario.getLogin());
		
//		Then
		assertNotNull(user);
	}
	
	@Test
	public void shouldReturnFalseGivenInvalidLoginAndPassword() throws IllegalAccessException, InstantiationException, InvocationTargetException {
//		Given
		Pessoa pessoa = givenValidPersistedPessoa();
		Usuario usuario = newDomainBuilder().withId(new Date().getTime()).withPessoa(pessoa).persisted().asUsuario();
		
		UsuarioSummary summary = new UsuarioSummary();
		summary.setLogin(usuario.getLogin());
		
//		When
		UsuarioSummary user = usuarioController.getByLogin(usuario.getLogin()+"1");
		
//		Then
		assertNull(user);
	}
	
	private void assertUsuario(UsuarioSummary expected, UsuarioSummary actual) {
		assertNotNull(actual);
		assertEquals(expected.getLogin(), actual.getLogin());
		assertEquals(expected.getSenha(), actual.getSenha());
	}
	
	private UsuarioSummaryBuilder newSummaryBuilder() {
		return new UsuarioSummaryBuilder();
	}
	
	private UsuarioDomainBuilder newDomainBuilder() {
		return new UsuarioDomainBuilder(usuarioRepository);
	}
	
	private Pessoa givenValidPersistedPessoa() {
		return new PessoaDomainBuilder(pessoaRepository).persisted().asPessoa();
	}
	private Pessoa givenValidPersistedPessoa(String endereco) {
		return new PessoaDomainBuilder(pessoaRepository).withEndereco(endereco).persisted().asPessoa();
	}
	
}
