package org.ufpr.dac.repository;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ufpr.dac.builder.PessoaDomainBuilder;
import org.ufpr.dac.builder.UsuarioBuilder;
import org.ufpr.dac.domain.Pessoa;
import org.ufpr.dac.domain.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-test.xml"})
public class UsuarioRepositoryComponentTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PessoaDomainBuilder pessoaDomainBuilder;
	private Pessoa validPessoa;
	
	@Before
	public void setUp() {
		validPessoa = pessoaDomainBuilder.persisted().asPessoa();
	}
	
	@Test
	public void shouldCreateUsuario() {
//		Given
		Usuario user = newBuilder().withPessoa(validPessoa).asUsuario();
		
//		When
		Long id = usuarioRepository.save(user).getId();
		
//		Then
		Usuario result = usuarioRepository.findOne(id);
		
		assertUsuario(user, result);
	}
	
	@Test
	public void shouldUpdateUsuario() {
//		Given
		Usuario user = newBuilder().withPessoa(validPessoa).persisted().asUsuario();
		user.setSenha("NEW PASSWORD");
		
//		When
		Long id = usuarioRepository.save(user).getId();
		
//		Then
		Usuario result = usuarioRepository.findOne(id);
		
		assertUsuario(user, result);
	}
	
	@Test
	public void shouldDeleteUsuario() {
//		Given
		Long id = newBuilder().withPessoa(validPessoa).persisted().asUsuario().getId();
		
//		When
		usuarioRepository.delete(id);
		
//		Then
		Usuario result = usuarioRepository.findOne(id);
		
		assertNull(result);
	}
	
	@Test
	public void shouldFindAllUsuarios() {
//		Given
		newBuilder().withId(new Date().getTime()).withPessoa(validPessoa).persisted().asUsuario();
		newBuilder().withId(new Date().getTime()).withPessoa(validPessoa).persisted().asUsuario();
		
//		When
		List<Usuario> result = usuarioRepository.findAll();
		
//		Then
		assertNotNull(result);
		assertFalse(result.isEmpty());
		assertTrue(result.size() >= 2);
	}

	private void assertUsuario(Usuario expected, Usuario actual) {
		assertNotNull(actual);
		assertEquals(expected.getLogin(), actual.getLogin());
		assertEquals(expected.getSenha(), actual.getSenha());
		assertPessoa(expected.getPessoa(), actual.getPessoa());
	}
	
	private void assertPessoa(Pessoa expected, Pessoa actual) {
		assertNotNull(actual);
		assertEquals(expected.getRootId(), actual.getRootId());
		assertEquals(expected.getNome(), actual.getNome());
	}
	
	private UsuarioBuilder newBuilder() {
		return new UsuarioBuilder(usuarioRepository);
	}

}
