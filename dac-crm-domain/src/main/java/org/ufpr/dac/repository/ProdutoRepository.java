package org.ufpr.dac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.ufpr.dac.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Query("select p from Produto p where p.descricao like ?1 ")
	List<Produto> BuscaPorDescricao(String descricao); 
	

	@Modifying
	@Query("update Produto p set p.estoque = (p.estoque - ?1) where p.id = ?2")
	void updateEstoqueVenda(Double qtd, Long id);
	
	@Modifying
	@Query("update Produto p set p.estoque = (p.estoque + ?1) where p.id = ?2")
	void updateEstoqueCompra(Double qtd, Long id);
	
}
