package org.springframework.samples.petclinic.medicalAppointment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.basicUnit.BasicUnit;
import org.springframework.samples.petclinic.basicUnit.BasicUnitRepository;
import org.springframework.samples.petclinic.basicUnit.EntityUtils;
import org.springframework.samples.petclinic.patient.Patient;
import org.springframework.samples.petclinic.patient.PatientRepository;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MedicalAppointmentRepositoryTest {

	@Autowired
	protected MedicalAppointmentRepository medicalAppointmentRepository;
	
	@Autowired
	protected BasicUnitRepository basicUnitRepository;
	
	@Autowired
	protected PatientRepository patientRepository;
	
	@Test
	void shouldfindByName() {
		MedicalAppointment medicalAppointment = this.medicalAppointmentRepository.findById(1);
		assertThat(medicalAppointment.getDateMedicalAppointment()).isEqualTo(Date.valueOf("2020-07-02"));
	}
	
	@Test
	void shouldfindbyAll() {
		Collection<MedicalAppointment> medicalAppointments = this.medicalAppointmentRepository.findAll();
		assertThat(medicalAppointments.size()).isEqualTo(27);
	}
	
	@Test
	void shouldfindbyDate() {
		Collection<MedicalAppointment> medicalAppointments = this.medicalAppointmentRepository.findByDate(Date.valueOf("2020-07-02"));
		assertThat(medicalAppointments.size()).isEqualTo(2);
		for (MedicalAppointment medicalAppointment : medicalAppointments) {
			assertThat(medicalAppointment.getDateMedicalAppointment()).isEqualTo(Date.valueOf("2020-07-02"));
		}
	}
	
	@Test
	void shouldfindbyWeek() {
		List<MedicalAppointment> medicalAppointments = this.medicalAppointmentRepository.findWeek(Date.valueOf("2020-06-10"), Date.valueOf("2020-06-17"));
		assertThat(medicalAppointments.size()).isEqualTo(3);
		for (MedicalAppointment medicalAppointment : medicalAppointments) {
			BasicUnit basicUnit = this.basicUnitRepository.findById(medicalAppointment.getBasicUnit().getId());
			assertThat(basicUnit.getId()).isNotNull();
		}
	}
	
	@Test
	void shouldfindByIdBasicUnit() {
		List<MedicalAppointment> medicalAppointments = this.medicalAppointmentRepository.findByIdBasicUnit(1);
		assertThat(medicalAppointments.size()).isEqualTo(4);
		for (MedicalAppointment medicalAppointment : medicalAppointments) {
			BasicUnit basicUnit = this.basicUnitRepository.findById(medicalAppointment.getBasicUnit().getId());
			assertThat(basicUnit.getId()).isEqualTo(1);
			assertThat(basicUnit.getTelephone()).isEqualTo("(51)3359-8685");
		}
	}
	
	@Test
	void shouldfindByIdPatient() {
		List<MedicalAppointment> medicalAppointments = this.medicalAppointmentRepository.findByIdPatient(5);
		assertThat(medicalAppointments.size()).isEqualTo(3);
		for (MedicalAppointment medicalAppointment : medicalAppointments) {
			Patient patient = this.patientRepository.findById(medicalAppointment.getPatient().getId());
			assertThat(patient.getId()).isEqualTo(5);
			assertThat(patient.getName()).isEqualTo("Finn Cole");
		}
	}
	
	@Test
	@Transactional
	void shouldInsert() {
		MedicalAppointment medicalAppointment = new MedicalAppointment();
		medicalAppointment.getPatient().setId(1);
		medicalAppointment.getFunctionary().setId(1);
		medicalAppointment.getBasicUnit().setId(10);
		medicalAppointment.setDateMedicalAppointment(Date.valueOf("2020-08-19"));
		assertThat(medicalAppointment.getId()).isNull();
		this.medicalAppointmentRepository.save(medicalAppointment);
		assertThat(medicalAppointment.getId()).isNotNull();
		Collection<MedicalAppointment> medicalAppointments = this.medicalAppointmentRepository.findAll();
		MedicalAppointment ma = EntityUtils.getById(medicalAppointments, MedicalAppointment.class, medicalAppointment.getId());
		assertThat(ma.getDateMedicalAppointment()).isEqualTo(Date.valueOf("2020-08-19"));
	}
	
	@Test
	@Transactional
	void shouldDelete() {
		MedicalAppointment medicalAppointment = new MedicalAppointment();
		medicalAppointment.getPatient().setId(1);
		medicalAppointment.getFunctionary().setId(1);
		medicalAppointment.getBasicUnit().setId(10);
		medicalAppointment.setDateMedicalAppointment(Date.valueOf("2020-08-19"));
		assertThat(medicalAppointment.getId()).isNull();
		this.medicalAppointmentRepository.save(medicalAppointment);
		assertThat(medicalAppointment.getId()).isNotNull();
		Collection<MedicalAppointment> medicalAppointments = this.medicalAppointmentRepository.findAll();
		MedicalAppointment ma = EntityUtils.getById(medicalAppointments, MedicalAppointment.class, medicalAppointment.getId());
		assertThat(ma.getDateMedicalAppointment()).isEqualTo(Date.valueOf("2020-08-19"));
		this.medicalAppointmentRepository.delete(ma);
		MedicalAppointment ma2 = this.medicalAppointmentRepository.findById(ma.getId());
		assertThat(ma2).isNull();
	}
	
}
	