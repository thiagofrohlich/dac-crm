package org.ufpr.dac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.ufpr.dac.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Query("select p from Produto p where p.descricao like ?1 ")
	List<Produto> BuscaPorDescricao(String descricao); 
	
}
