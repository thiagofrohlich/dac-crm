package org.ufpr.dac.transformer.impl;

import static org.junit.Assert.*;
import java.lang.reflect.InvocationTargetException;

import org.junit.Before;
import org.junit.Test;
import org.ufpr.dac.builder.UsuarioDomainBuilder;
import org.ufpr.dac.builder.UsuarioSummaryBuilder;
import org.ufpr.dac.domain.Usuario;
import org.ufpr.dac.model.UsuarioSummary;

import orc.ufpr.dac.transformer.impl.UsuarioTransformer;

public class UsuarioTransformerTest {
	
	private UsuarioTransformer usuarioTransformer;
	
	@Before
	public void setUp() {
		usuarioTransformer = new UsuarioTransformer();
	}
	
	@Test
	public void shouldTransformUsuarioSummaryIntoDomain() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
//		Given
		UsuarioSummary summary = newSummaryBuilder().asUsuario();
		Usuario domain = new Usuario();
		
//		When
		usuarioTransformer.transform(summary, domain);
		
//		Then
		assertEquals(summary.getLogin(), domain.getLogin());
		assertEquals(summary.getSenha(), domain.getSenha());
	}
	
	@Test
	public void shouldTransformUsuarioDomainIntoSummary() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
//		Given
		Usuario domain = newDomainBuilder().asUsuario();
		UsuarioSummary summary = new UsuarioSummary();
		
//		When
		usuarioTransformer.transform(domain, summary);
		
//		Then
		assertEquals(domain.getLogin(), summary.getLogin());
		assertEquals(domain.getSenha(), summary.getSenha());
	}
	
	private UsuarioSummaryBuilder newSummaryBuilder() {
		return new UsuarioSummaryBuilder();
	}
	
	private UsuarioDomainBuilder newDomainBuilder() {
		return new UsuarioDomainBuilder(null);
	}

}
