package org.ufpr.dac.service;

import org.junit.Before;
import org.junit.Test;
import org.ufpr.dac.model.ProdutoSummary;

public class ProdutoServiceHandlerComponentTest {

private ProdutoServiceHandler produtoServiceHandler;
	
	@Before
	public void setUp() {
		produtoServiceHandler = new ProdutoServiceHandler();
	}
	
	@Test
	public void shouldCallCreate() {
		ProdutoSummary produto = new ProdutoSummary();
		produto.setDescricao("dsc");
		produtoServiceHandler.create(produto);
	}
}
