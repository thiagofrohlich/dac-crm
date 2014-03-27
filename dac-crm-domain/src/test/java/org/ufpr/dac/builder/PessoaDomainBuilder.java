package org.ufpr.dac.builder;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ufpr.dac.domain.Endereco;
import org.ufpr.dac.domain.Pessoa;
import org.ufpr.dac.domain.PessoaFisica;
import org.ufpr.dac.domain.PessoaJuridica;
import org.ufpr.dac.repository.PessoaRepository;

@Component
public class PessoaDomainBuilder {
	
	private final PessoaRepository pessoaRepository;

	@Autowired
	public PessoaDomainBuilder(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}
	
	private Long rootId = 1l;
	private String nome = "test name";
	private String endereco = "test addres";
	
	private Long id = 1l;
	
//	Pessoa F�sica
	private String cpf = "999.999.999-99";
	private Date dataNascimento = new Date();
	private String email = "test@email.com";

//	Pessoa Jur�dica
	private Boolean ativo = true;
	private String cnpj = "99.999.999/9999-99";
	
	private boolean persisted = false;
	
	public Pessoa asPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setRootId(rootId);
		pessoa.setNome(nome);
		pessoa.setEndereco(new Endereco());
		pessoa.getEndereco().setEndereco(endereco);
		
		return persist(pessoa);
	}

	public Pessoa asPessoaWithPessoaFisica() {
		Pessoa pessoa = asPessoa();
		PessoaFisica pf = new PessoaFisica();
		pf.setCpf(cpf);
		pf.setDataNascimento(dataNascimento);
		pf.setEmail(email);
		pf.setId(id);
		pf.setPessoa(pessoa);
		
		pessoa.setPessoaFisica(pf);
		return persist(pessoa);
	}
	
	public Pessoa asPessoaWithPessoaJuridica() {
		Pessoa pessoa = asPessoa();
		PessoaJuridica pj = new PessoaJuridica();
		pj.setAtivo(ativo);
		pj.setCnpj(cnpj);
		pj.setId(id);
		pj.setPessoa(pessoa);
		
		pessoa.setPessoaJuridica(pj);
		return persist(pessoa);
	}
	
	private Pessoa persist(Pessoa pessoa) {
		if(persisted) {
			return pessoaRepository.saveAndFlush(pessoa);
		}
		return pessoa;
	}
	
	public PessoaDomainBuilder withRootId(Long rootId) {
		this.rootId = rootId;
		return this;
	}
	public PessoaDomainBuilder withNome(String nome) {
		this.nome = nome;
		return this;
	}
	public PessoaDomainBuilder withEndereco(String endereco) {
		this.endereco = endereco;
		return this;
	}
	public PessoaDomainBuilder withId(Long id) {
		this.id = id;
		return this;
	}
	public PessoaDomainBuilder withCpf(String cpf) {
		this.cpf = cpf;
		return this;
	}
	public PessoaDomainBuilder withDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
		return this;
	}
	public PessoaDomainBuilder withEmail(String email) {
		this.email = email;
		return this;
	}
	public PessoaDomainBuilder withAtivo(Boolean ativo) {
		this.ativo = ativo;
		return this;
	}
	public PessoaDomainBuilder withCnpj(String cnpj) {
		this.cnpj = cnpj;
		return this;
	}
	public PessoaDomainBuilder persisted() {
		persisted = true;
		return this;
	}
	
}
