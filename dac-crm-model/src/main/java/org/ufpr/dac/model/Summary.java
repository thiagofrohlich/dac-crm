package org.ufpr.dac.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso({EnderecoSummary.class, NotaFiscalSummary.class, OperacaoSummary.class, PessoaFisicaSummary.class, PessoaJuridicaSummary.class, PessoaSummary.class, ProdutoNfSummary.class,
			ProdutoSummary.class, UsuarioSummary.class})
public interface Summary extends Model, Serializable {
	
}
