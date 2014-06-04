package org.ufpr.dac.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.ufpr.dac.domain.Operacao;

@Repository
public interface OperacaoRepository extends JpaRepository<Operacao, Long> {

	@Query("Select o from Operacao o where o.tipoOperacao = 'COMPRA' and o.dataOperacao between ?1 and ?2")
	List<Operacao> buscaCompraRelatorio(Date dataini, Date datafim);
	
	@Query("Select o from Operacao o inner join o.notaFiscal.pessoa where o.tipoOperacao = 'COMPRA' and o.dataOperacao between ?1 and ?2 and o.notaFiscal.pessoa.pessoaJuridica.cnpj = ?3")
	List<Operacao> buscaCompraRelatorio(Date dataini, Date datafim, String cnpj);
	
	@Query("Select o from Operacao o inner join o.notaFiscal.produtosNf pnf where o.tipoOperacao = 'COMPRA' and o.dataOperacao between ?1 and ?2 and o.notaFiscal.pessoa.pessoaJuridica.cnpj = ?3 and pnf.id.produtoId = ?4")
	List<Operacao> buscaCompraRelatorio(Date dataini, Date datafim, String cnpj, Long codProduto);
	
	@Query("Select o from Operacao o inner join o.notaFiscal.produtosNf pnf where o.tipoOperacao = 'COMPRA' and o.dataOperacao between ?1 and ?2  and pnf.id.produtoId = ?4")
	List<Operacao> buscaCompraRelatorio(Date dataini, Date datafim, Long codProduto);
	
	@Query("Select o from Operacao o where o.tipoOperacao = 'VENDA' and o.dataOperacao between ?1 and ?2")
	List<Operacao> buscaVendaRelatorio(Date dataini, Date datafim);
	
	@Query("Select o from Operacao o where o.tipoOperacao = 'VENDA' and o.dataOperacao between ?1 and ?2 and o.notaFiscal.pessoa.pessoaFisica.cpf = ?3")
	List<Operacao> buscaVendaRelatorio(Date dataini, Date datafim, String cpf);
	
	@Query("Select o from Operacao o inner join o.notaFiscal.produtosNf pnf where o.tipoOperacao = 'VENDA' and o.dataOperacao between ?1 and ?2 and o.notaFiscal.pessoa.pessoaFisica.cpf = ?3 and pnf.id.produtoId = ?4")
	List<Operacao> buscaVendaRelatorio(Date dataini, Date datafim, String cpf, Long codProduto);
	
	@Query("Select o from Operacao o inner join o.notaFiscal.produtosNf pnf where o.tipoOperacao = 'VENDA' and o.dataOperacao between ?1 and ?2  and pnf.id.produtoId = ?4")
	List<Operacao> buscaVendaRelatorio(Date dataini, Date datafim, Long codProduto);
	
	
}
