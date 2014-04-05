package org.ufpr.dac.service;

import org.ufpr.dac.model.PessoaSummary;
import org.ufpr.dac.wrapper.PessoaWrapper;

public class PessoaServiceHandler extends AbstractServiceHandler<PessoaSummary, PessoaWrapper, Long> {

	@Override
	public String getPath() {
		return super.getPath() + "pessoa/";
	}
	
}
