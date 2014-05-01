package org.ufpr.dac.service;

import org.ufpr.dac.model.ProdutoSummary;
import org.ufpr.dac.wrapper.ProdutoWrapper;

public class ProdutoServiceHandler extends AbstractServiceHandler<ProdutoSummary, ProdutoWrapper, Long> {
	
	@Override
	protected Class<?> getSummary() {
		return ProdutoSummary.class;
	}
	
	@Override
	protected Class<?> getWrapper() {
		return ProdutoWrapper.class;
	}
	
	@Override
	public String getPath() {
		return super.getPath() + "produto/";
	}

}