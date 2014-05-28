package org.ufpr.dac.wrapper;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.springframework.data.domain.Page;
import org.ufpr.dac.model.PessoaFisicaSummary;
import org.ufpr.dac.model.PessoaJuridicaSummary;
import org.ufpr.dac.model.PessoaSummary;
import org.ufpr.dac.model.UsuarioSummary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@XmlRootElement
@XmlSeeAlso({PessoaFisicaSummary.class, PessoaJuridicaSummary.class, UsuarioSummary.class})
@JsonIgnoreProperties(ignoreUnknown=true)
public class PessoaWrapper extends Wrapper<PessoaSummary> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PessoaWrapper(Page<? extends Object> page) {
		super(page);
	}

	public PessoaWrapper() {
		super();
	}
	
}
