package org.ufpr.dac.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;


/**
 * The persistent class for the pessoa database table.
 * 
 */
@Entity
@NamedQuery(name="Pessoa.findAll", query="SELECT p FROM Pessoa p")
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="pessoa_seq_generator", sequenceName="pessoa_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pessoa_seq_generator")
	@Column(name="id", nullable=false, unique=true)
	private Long rootId;

	private String nome;

	@OneToOne(mappedBy="pessoa")
	private Endereco endereco;

	@OneToMany(mappedBy="pessoa")
	private List<NotaFiscal> notaFiscals;

	@OneToOne(mappedBy="pessoa")
	private PessoaFisica pessoaFisica;

	@OneToOne(mappedBy="pessoa")
	private PessoaJuridica pessoaJuridica;

	@OneToOne(mappedBy="pessoa")
	private Usuario usuario;

	public Pessoa() {
	}

	public Long getRootId() {
		return this.rootId;
	}

	public void setRootId(Long id) {
		this.rootId = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<NotaFiscal> getNotaFiscals() {
		return this.notaFiscals;
	}

	public void setNotaFiscals(List<NotaFiscal> notaFiscals) {
		this.notaFiscals = notaFiscals;
	}

	public NotaFiscal addNotaFiscal(NotaFiscal notaFiscal) {
		getNotaFiscals().add(notaFiscal);
		notaFiscal.setPessoa(this);

		return notaFiscal;
	}

	public NotaFiscal removeNotaFiscal(NotaFiscal notaFiscal) {
		getNotaFiscals().remove(notaFiscal);
		notaFiscal.setPessoa(null);

		return notaFiscal;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public PessoaJuridica getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}