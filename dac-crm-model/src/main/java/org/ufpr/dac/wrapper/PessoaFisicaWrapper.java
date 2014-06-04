package org.ufpr.dac.wrapper;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.springframework.data.domain.Page;
import org.ufpr.dac.model.PessoaFisicaSummary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@XmlRootElement
@XmlSeeAlso({PessoaFisicaSummary.class})
@JsonIgnoreProperties(ignoreUnknown=true)
public class PessoaFisicaWrapper extends Wrapper<PessoaFisicaSummary> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PessoaFisicaWrapper(Page<? extends Object> page) {
		super(page);
	}

	public PessoaFisicaWrapper() {
		super();
	}
	
}
