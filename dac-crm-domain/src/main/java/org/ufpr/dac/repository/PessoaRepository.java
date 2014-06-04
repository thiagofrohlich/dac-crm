package org.ufpr.dac.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.ufpr.dac.domain.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	@Query("select p from Pessoa p inner join p.pessoaJuridica pj where pj.cnpj = ?1 and p.id = pj.pessoa.id ")
	Pessoa findByCNPJ(String cnpj);
	
	@Query(value="select p from Pessoa p left outer join p.pessoaFisica left outer join p.pessoaJuridica where p.rootId in (select pj.pessoa from PessoaJuridica pj) or p.rootId in (select pf.pessoa from PessoaFisica pf)")
	Page<Pessoa> findAllExceptUsuario(Pageable page);
}
