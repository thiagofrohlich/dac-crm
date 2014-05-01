package org.ufpr.dac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.ufpr.dac.domain.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	@Query("select p from Pessoa p inner join p.pessoaJuridica pj where pj.cnpj = ?1 and p.id = pj.pessoa.id ")
	Pessoa findByCNPJ(String cnpj);
}
