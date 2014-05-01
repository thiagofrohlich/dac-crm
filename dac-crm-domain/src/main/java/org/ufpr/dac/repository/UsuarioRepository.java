package org.ufpr.dac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.ufpr.dac.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query("select count(u) from Usuario u where u.login = ?1 and u.senha = ?2")
	long exists(String login, String senha);
	
	Usuario findByLogin(String login);
}
