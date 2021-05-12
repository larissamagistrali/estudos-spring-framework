package org.springframework.samples.petclinic.patient;

import java.util.Collection;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface PatientRepository extends Repository<Patient, Integer> {

	@Transactional(readOnly = true)
	Collection<Patient> findAll() throws DataAccessException;
	 
	@Transactional(readOnly = true)
	Patient findById(Integer id);
	
	void save(Patient patient);
	
	void delete(Patient patient);
}
