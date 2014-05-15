package org.ufpr.dac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.ufpr.dac.domain.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	@Query("select p from Pessoa p inner join p.pessoaJuridica pj where pj.cnpj = ?1 and p.id = pj.pessoa.id ")
	Pessoa findByCNPJ(String cnpj);
	
	@Query("select p from Pessoa p inner join p.pessoaFisica pf where pf.cpf = ?1 and p.id = pf.pessoa.id ")
	Pessoa findByCPF(String cpf);
	
	@Query("select p from Pessoa p where p.nome like ?1")
	List<Pessoa> findByNome(String nome);
}
