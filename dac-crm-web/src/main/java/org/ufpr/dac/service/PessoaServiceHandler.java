package org.ufpr.dac.service;

import org.ufpr.dac.model.PessoaFisicaSummary;
import org.ufpr.dac.model.PessoaJuridicaSummary;
import org.ufpr.dac.model.PessoaSummary;
import org.ufpr.dac.wrapper.PessoaWrapper;

public class PessoaServiceHandler extends AbstractServiceHandler<PessoaSummary, PessoaWrapper, Long> {

	@Override
	protected Class<?> getWrapper() {
		return PessoaWrapper.class;
	}
	
	@Override
	protected Class<?> getSummary() {
		return PessoaSummary.class;
	}
	
	public PessoaFisicaSummary getPessoaFisica(Long id) {
		return getRestTemplate().getForObject(getPath()+"{id}", PessoaFisicaSummary.class, id);
	}
	
	public PessoaJuridicaSummary getPessoaJuridica(Long id) {
		return getRestTemplate().getForObject(getPath()+"{id}", PessoaJuridicaSummary.class, id);
	}
	
	@Override
	public String getPath() {
		return super.getPath() + "pessoa/";
	}
	
	public PessoaJuridicaSummary getByCnpj(String cnpj) {
		return  (PessoaJuridicaSummary) getRestTemplate().getForObject(getPath()+"cnpj/{cnpj}", PessoaJuridicaSummary.class, cnpj);
	}
	
}
