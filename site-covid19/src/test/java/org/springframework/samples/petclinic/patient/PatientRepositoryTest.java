package org.springframework.samples.petclinic.patient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.basicUnit.EntityUtils;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class PatientRepositoryTest {

	@Autowired
	protected PatientRepository patientRepository;
	
	@Test
	void shouldfindByName() {
		Patient functionary = this.patientRepository.findById(1);
		assertThat(functionary.getName()).isEqualTo("Cillian Murphy");
	}
	
	@Test
	void shouldfindbyAll() {
		Collection<Patient> patients = this.patientRepository.findAll();
		assertThat(patients.size()).isEqualTo(10);
	}
	
	@Test
	@Transactional
	void shouldInsert() {
		Patient patient = new Patient();
		patient.setName("John");
		patient.setDateBirth(Date.valueOf("1997-11-11"));
		assertThat(patient.getId()).isNull();
		this.patientRepository.save(patient);
		assertThat(patient.getId()).isNotNull();
		Collection<Patient> patients = this.patientRepository.findAll();
		Patient patient2 = EntityUtils.getById(patients, Patient.class, patient.getId());
		assertThat(patient2.getName()).isEqualTo("John");
	}
	
	
	@Test
	@Transactional
	void shouldDelete() {
		Patient functionary = new Patient();
		functionary.setName("John");
		functionary.setDateBirth(Date.valueOf("1997-11-11"));
		assertThat(functionary.getId()).isNull();
		this.patientRepository.save(functionary);
		assertThat(functionary.getId()).isNotNull();
		Collection<Patient> functionaries = this.patientRepository.findAll();
		Patient functionary2 = EntityUtils.getById(functionaries, Patient.class, functionary.getId());
		assertThat(functionary2.getName()).isEqualTo("John");
		this.patientRepository.delete(functionary2);
		Patient bu2 = this.patientRepository.findById(functionary2.getId());
		assertThat(bu2).isNull();
	}
	
}
