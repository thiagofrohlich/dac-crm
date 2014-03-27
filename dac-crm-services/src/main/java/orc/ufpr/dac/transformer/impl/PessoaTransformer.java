package orc.ufpr.dac.transformer.impl;

import java.lang.reflect.InvocationTargetException;

import org.springframework.stereotype.Component;
import org.ufpr.dac.domain.Domain;
import org.ufpr.dac.domain.Pessoa;
import org.ufpr.dac.domain.PessoaFisica;
import org.ufpr.dac.domain.PessoaJuridica;
import org.ufpr.dac.model.PessoaFisicaSummary;
import org.ufpr.dac.model.PessoaJuridicaSummary;
import org.ufpr.dac.model.PessoaSummary;

@Component
public class PessoaTransformer extends AbstractTransformer {
	
	@Override
	public void transform(Object objectFrom, Object objectTo)
			throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		
		super.transform(objectFrom, objectTo);
		
		if(isSummary(objectFrom)) {
			transformSummary(objectFrom, objectTo);
		}
		
		if(isDomain(objectFrom)){
			transformDomain(objectFrom, objectTo);
		}
		
	}
	
	private void transformSummary(Object objectFrom, Object objectTo)
			throws IllegalAccessException, InstantiationException,
			InvocationTargetException {
		Pessoa p = (Pessoa) objectTo;
		transformPessoaFisica(objectFrom, p);
		transformPessoaJuridica(objectFrom, p);
	}

	private void transformPessoaJuridica(Object objectFrom, Pessoa p)
			throws IllegalAccessException, InstantiationException,
			InvocationTargetException {
		
		if(objectFrom instanceof PessoaJuridicaSummary) {
			PessoaJuridica pj = new PessoaJuridica();
			super.transform(objectFrom, pj);
			p.setPessoaJuridica(pj);
		}
	}

	private void transformPessoaFisica(Object objectFrom, Pessoa p)
			throws IllegalAccessException, InstantiationException,
			InvocationTargetException {
		
		if(objectFrom instanceof PessoaFisicaSummary) {
			PessoaFisica pf = new PessoaFisica();
			super.transform(objectFrom, pf);
			p.setPessoaFisica(pf);
		}
	}
	
	private void transformDomain(Object objectFrom, Object objectTo) {
		try {
			Pessoa p = (Pessoa) objectFrom;
			transformPessoaFisicaIfProvided(objectTo, p);
			transformPessoaJuridicaIfProvided(objectTo, p);
		} catch (Exception e) {
			doNothing();
		}
	}

	private void transformPessoaJuridicaIfProvided(Object objectTo, Pessoa p)
			throws IllegalAccessException, InstantiationException,
			InvocationTargetException {
		if(!isNull(p.getPessoaJuridica())) {
			super.transform(p.getPessoaJuridica(), objectTo);
		}
	}

	private void transformPessoaFisicaIfProvided(Object objectTo, Pessoa p)
			throws IllegalAccessException, InstantiationException,
			InvocationTargetException {
		if(!isNull(p.getPessoaFisica())) {
			super.transform(p.getPessoaFisica(), objectTo);
		}
	}

	private boolean isNull(Object o) {
		return o == null;
	}
	
	private boolean isDomain(Object objectFrom) {
		return objectFrom instanceof Domain;
	}
	
	private boolean isSummary(Object objectFrom) {
		return objectFrom instanceof PessoaSummary;
	}
	
	private void doNothing() {
	}

}

