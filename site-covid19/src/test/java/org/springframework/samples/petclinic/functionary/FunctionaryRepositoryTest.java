package org.springframework.samples.petclinic.functionary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.basicUnit.BasicUnit;
import org.springframework.samples.petclinic.basicUnit.EntityUtils;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class FunctionaryRepositoryTest {
	
	@Autowired
	protected FunctionaryRepository functionaryRepository;
	
	@Test
	void shouldfindByName() {
		Functionary functionary = this.functionaryRepository.findById(1);
		assertThat(functionary.getName()).isEqualTo("Packy Lee");
	}
	
	@Test
	void shouldfindbyAll() {
		Collection<Functionary> functionaries = this.functionaryRepository.findAll();
		assertThat(functionaries.size()).isEqualTo(10);
	}
	
	@Test
	@Transactional
	void shouldInsert() {
		Functionary functionary = new Functionary();
		functionary.setName("John");
		functionary.setDateBirth(Date.valueOf("1997-11-11"));
		functionary.setFunction("Medico");
		assertThat(functionary.getId()).isNull();
		this.functionaryRepository.save(functionary);
		assertThat(functionary.getId()).isNotNull();
		Collection<Functionary> functionaries = this.functionaryRepository.findAll();
		Functionary functionary2 = EntityUtils.getById(functionaries, Functionary.class, functionary.getId());
		assertThat(functionary2.getName()).isEqualTo("John");
	}
	
	
	@Test
	@Transactional
	void shouldDelete() {
		Functionary functionary = new Functionary();
		functionary.setName("John");
		functionary.setDateBirth(Date.valueOf("1997-11-11"));
		functionary.setFunction("Medico");
		assertThat(functionary.getId()).isNull();
		this.functionaryRepository.save(functionary);
		assertThat(functionary.getId()).isNotNull();
		Collection<Functionary> functionaries = this.functionaryRepository.findAll();
		Functionary functionary2 = EntityUtils.getById(functionaries, Functionary.class, functionary.getId());
		assertThat(functionary2.getName()).isEqualTo("John");
		this.functionaryRepository.delete(functionary2);
		Functionary bu2 = this.functionaryRepository.findById(functionary2.getId());
		assertThat(bu2).isNull();
	}
	

	

}
