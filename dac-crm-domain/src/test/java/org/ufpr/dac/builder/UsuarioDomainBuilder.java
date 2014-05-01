package org.ufpr.dac.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ufpr.dac.domain.Pessoa;
import org.ufpr.dac.domain.Usuario;
import org.ufpr.dac.repository.UsuarioRepository;

@Component
public class UsuarioDomainBuilder {

	private final UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioDomainBuilder(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	private Long id;
	private String login = "test";
	private String senha = "test";
	private Pessoa pessoa;
	private boolean persisted = false;
	
	public Usuario asUsuario() {
		Usuario usuario = new Usuario();
		usuario.setId(id);
		usuario.setLogin(login);
		usuario.setSenha(senha);
		usuario.setPessoaUsuario(pessoa != null ? pessoa : makeDefaultPessoa());
		return persist(usuario);
	}

	private Usuario persist(Usuario usuario) {
		if(persisted) {
			return usuarioRepository.saveAndFlush(usuario);
		}
		return usuario;
	}

	private Pessoa makeDefaultPessoa() {
		Pessoa p = new Pessoa();
		p.setRootId(id);
		p.setNome("TESTE");
		return p;
	}

	public UsuarioDomainBuilder withId(Long id) {
		this.id = id;
		return this;
	}
	public UsuarioDomainBuilder withLogin(String login) {
		this.login = login;
		return this;
	}
	public UsuarioDomainBuilder withSenha(String senha) {
		this.senha = senha;
		return this;
	}
	public UsuarioDomainBuilder withPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
		return this;
	}
	public UsuarioDomainBuilder persisted() {
		this.persisted = true;
		return this;
	}
	
}
