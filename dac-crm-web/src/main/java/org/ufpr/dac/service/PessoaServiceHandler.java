package org.ufpr.dac.service;

import org.ufpr.dac.model.PessoaJuridicaSummary;
import org.ufpr.dac.model.PessoaSummary;
import org.ufpr.dac.wrapper.PessoaWrapper;

public class PessoaServiceHandler extends AbstractServiceHandler<PessoaSummary, PessoaWrapper, Long> {

	@Override
	public String getPath() {
		return super.getPath() + "pessoa/";
	}
	
	public PessoaJuridicaSummary getByCnpj(String cnpj) {
		return  (PessoaJuridicaSummary) getRestTemplate().getForObject(getPath()+"cnpj/{cnpj}", PessoaJuridicaSummary.class, cnpj);
	}
	
}
