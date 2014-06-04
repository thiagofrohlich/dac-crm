package org.ufpr.dac.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * The persistent class for the pessoa database table.
 * 
 */
@Entity
@NamedQuery(name="Pessoa.findAll", query="SELECT p FROM Pessoa p")
public class Pessoa implements Domain, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="pessoa_seq_generator", sequenceName="pessoa_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pessoa_seq_generator")
	@Column(name="id", nullable=false, unique=true)
	private Long rootId;

	@NotNull(message="org.ufpr.dac.nomePessoaCannotBeNull")
	@NotEmpty(message="org.ufpr.dac.nomePessoaCannotBeNull")
	private String nome;
	
	
	private String telefone;
	
	@OneToOne(mappedBy="pessoa", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Endereco endereco;

	@OneToMany(mappedBy="pessoa", fetch=FetchType.LAZY)
	private List<NotaFiscal> notaFiscals;
	@OneToOne(mappedBy="pessoa", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private PessoaFisica pessoaFisica;
	@OneToOne(mappedBy="pessoa", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private PessoaJuridica pessoaJuridica;

	@OneToOne(mappedBy="pessoaUsuario")
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

	/*public List<NotaFiscal> getNotaFiscals() {
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
*/
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<NotaFiscal> getNotaFiscals() {
		return notaFiscals;
	}

	public void setNotaFiscals(List<NotaFiscal> notaFiscals) {
		this.notaFiscals = notaFiscals;
	}

}