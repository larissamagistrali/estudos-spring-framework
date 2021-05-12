package org.springframework.samples.petclinic.basicUnit;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.patient.Patient;
import org.springframework.transaction.annotation.Transactional;

public interface  BasicUnitRepository extends  Repository<BasicUnit, Integer> {
	//Pesquisa todas as unidades cadastradas
	@Transactional(readOnly = true)
	Collection<BasicUnit> findAll() throws DataAccessException;
	
	@Transactional(readOnly = true)
	BasicUnit findById(Integer id);
	
	//Altera e Adiciona novas unidades Basicas
	void save(BasicUnit unidadeBasica);
	
	void delete(BasicUnit unidadeBasica);
	
}
