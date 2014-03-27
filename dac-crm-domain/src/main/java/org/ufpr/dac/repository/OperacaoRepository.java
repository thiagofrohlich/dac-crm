package org.ufpr.dac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ufpr.dac.domain.Operacao;

@Repository
public interface OperacaoRepository extends JpaRepository<Operacao, Long> {

}
