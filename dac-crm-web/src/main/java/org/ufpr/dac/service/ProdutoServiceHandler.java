package org.ufpr.dac.service;

import org.ufpr.dac.model.ProdutoSummary;
import org.ufpr.dac.wrapper.ProdutoWrapper;

public class ProdutoServiceHandler extends AbstractServiceHandler<ProdutoSummary, ProdutoWrapper, Long> {
	
	@Override
	public String getPath() {
		return super.getPath() + "produto/";
	}

}