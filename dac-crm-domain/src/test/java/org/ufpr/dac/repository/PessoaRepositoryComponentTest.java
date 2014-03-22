package org.ufpr.dac.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ufpr.dac.domain.Pessoa;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-test.xml"})
public class PessoaRepositoryComponentTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired private PessoaRepository pessoaRepository;
	
	@Test
	public void shouldSaveNewPessoa() {
//		Given
		Pessoa p = new Pessoa();
		p.setNome("teste");
		
//		When
		Long id = pessoaRepository.save(p).getRootId();
		
//		Then
		Pessoa result = pessoaRepository.findOne(id);
		assertNotNull(result);
		assertEquals(p.getNome(), result.getNome());
	}

}
