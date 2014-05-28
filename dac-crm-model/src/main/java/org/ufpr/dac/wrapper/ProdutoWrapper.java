package org.ufpr.dac.wrapper;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.springframework.data.domain.Page;
import org.ufpr.dac.model.PessoaJuridicaSummary;
import org.ufpr.dac.model.ProdutoSummary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@XmlRootElement
@XmlSeeAlso(PessoaJuridicaSummary.class)
@JsonIgnoreProperties(ignoreUnknown=true)
public class ProdutoWrapper extends Wrapper<ProdutoSummary> {

	public ProdutoWrapper(Page<? extends Object> page) {
		super(page);
	}

	public ProdutoWrapper(){
		super();
	}
}
