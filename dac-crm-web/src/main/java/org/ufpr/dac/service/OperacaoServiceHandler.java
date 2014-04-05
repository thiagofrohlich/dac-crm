package org.ufpr.dac.service;

import org.ufpr.dac.model.OperacaoSummary;
import org.ufpr.dac.wrapper.OperacaoWrapper;

public class OperacaoServiceHandler extends AbstractServiceHandler<OperacaoSummary, OperacaoWrapper, Long> {

	@Override
	public String getPath() {
		return super.getPath() + "operacao/";
	}
	
}
