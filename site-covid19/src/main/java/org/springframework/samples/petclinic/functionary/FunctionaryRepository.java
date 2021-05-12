package org.springframework.samples.petclinic.functionary;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface FunctionaryRepository extends  Repository<Functionary, Integer> {

	@Transactional(readOnly = true)
	Collection<Functionary> findAll() throws DataAccessException;
	 
	@Transactional(readOnly = true)
	Functionary findById(Integer id);
	
	void save(Functionary functionary);
	
	void delete(Functionary functionary);
}
