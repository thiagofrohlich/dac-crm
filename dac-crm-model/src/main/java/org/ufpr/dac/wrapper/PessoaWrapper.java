package org.ufpr.dac.wrapper;

import org.springframework.data.domain.Page;
import org.ufpr.dac.model.PessoaSummary;

public class PessoaWrapper extends Wrapper<PessoaSummary> {

	public PessoaWrapper(Page<? extends Object> page) {
		super(page);
	}
	
}
