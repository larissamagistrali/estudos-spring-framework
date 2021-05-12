package org.springframework.samples.petclinic.basicUnit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class BasicUnitRepositoryTest {
	
	@Autowired
	protected BasicUnitRepository basicUnitRepository;
	
	@Test
	void shouldfindByName() {
		BasicUnit basicUnit = this.basicUnitRepository.findById(1);
		assertThat(basicUnit.getTelephone()).isEqualTo("(51)3359-8685");
	}
	
	@Test
	void shouldfindbyAll() {
		Collection<BasicUnit> basicUnits = this.basicUnitRepository.findAll();
		assertThat(basicUnits.size()).isEqualTo(10);
	}
	
	@Test
	@Transactional
	void shouldInsert() {
		BasicUnit basicUnit = new BasicUnit();
		basicUnit.setName("Unidada de Teste");
		assertThat(basicUnit.getId()).isNull();
		this.basicUnitRepository.save(basicUnit);
		assertThat(basicUnit.getId()).isNotNull();
		Collection<BasicUnit> basicUnits = this.basicUnitRepository.findAll();
		BasicUnit bu = EntityUtils.getById(basicUnits, BasicUnit.class, basicUnit.getId());
		assertThat(bu.getName()).isEqualTo("Unidada de Teste");
	}
	
	@Test
	@Transactional
	void shouldDelete() {
		BasicUnit basicUnit = new BasicUnit();
		basicUnit.setName("Unidada de Teste");
		assertThat(basicUnit.getId()).isNull();
		this.basicUnitRepository.save(basicUnit);
		assertThat(basicUnit.getId()).isNotNull();
		Collection<BasicUnit> basicUnits = this.basicUnitRepository.findAll();
		BasicUnit bu = EntityUtils.getById(basicUnits, BasicUnit.class, basicUnit.getId());
		assertThat(bu.getName()).isEqualTo("Unidada de Teste");
		this.basicUnitRepository.delete(bu);
		BasicUnit bu2 = this.basicUnitRepository.findById(bu.getId());
		assertThat(bu2).isNull();
	}
}
