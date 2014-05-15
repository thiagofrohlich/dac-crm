package org.ufpr.dac.wrapper;

import org.springframework.data.domain.Page;
import org.ufpr.dac.model.ProdutoSummary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class ProdutoWrapper extends Wrapper<ProdutoSummary> {

	public ProdutoWrapper(Page<? extends Object> page) {
		super(page);
	}

	public ProdutoWrapper(){}
}
