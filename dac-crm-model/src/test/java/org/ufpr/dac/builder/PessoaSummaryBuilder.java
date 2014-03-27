package org.ufpr.dac.builder;

import java.util.Date;

import org.ufpr.dac.model.EnderecoSummary;
import org.ufpr.dac.model.PessoaFisicaSummary;
import org.ufpr.dac.model.PessoaJuridicaSummary;
import org.ufpr.dac.model.PessoaSummary;

public class PessoaSummaryBuilder {
	
	private Long rootId = 1l;
	private String nome = "test name";
	private String endereco = "test addres";
	
//	Pessoa Fisica
	private String cpf = "999.999.999-99";
	private Date dataNascimento = new Date();
	private String email = "test@email.com";

//	Pessoa Juridica
	private Boolean ativo = true;
	private String cnpj = "99.999.999/9999-99";
	
	public PessoaFisicaSummary asPessoaFisica() {
		PessoaFisicaSummary p = new PessoaFisicaSummary();
		setCommomFields(p);
		p.setCpf(cpf);
		p.setDataNascimento(dataNascimento);
		p.setEmail(email);
		
		return p;
	}
	
	public PessoaJuridicaSummary asPessoaJuridica() {
		PessoaJuridicaSummary p = new PessoaJuridicaSummary();
		setCommomFields(p);
		p.setAtivo(ativo);
		p.setCnpj(cnpj);
		
		return p;
	}

	private PessoaSummary setCommomFields(PessoaSummary p) {
		p.setEndereco(new EnderecoSummary());
		p.getEndereco().setEndereco(endereco);
		p.setNome(nome);
		p.setRootId(rootId);
		return p;
	}
	
	public PessoaSummaryBuilder withRootId(Long rootId) {
		this.rootId = rootId;
		return this;
	}

	public PessoaSummaryBuilder withNome(String nome) {
		this.nome = nome;
		return this;
	}

	public PessoaSummaryBuilder withEndereco(String endereco) {
		this.endereco = endereco;
		return this;
	}

	public PessoaSummaryBuilder withCpf(String cpf) {
		this.cpf = cpf;
		return this;
	}

	public PessoaSummaryBuilder withDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
		return this;
	}

	public PessoaSummaryBuilder withEmail(String email) {
		this.email = email;
		return this;
	}

	public PessoaSummaryBuilder withAtivo(Boolean ativo) {
		this.ativo = ativo;
		return this;
	}

	public PessoaSummaryBuilder withCnpj(String cnpj) {
		this.cnpj = cnpj;
		return this;
	}

}
