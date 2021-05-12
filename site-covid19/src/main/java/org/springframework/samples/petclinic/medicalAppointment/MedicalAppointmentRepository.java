package org.springframework.samples.petclinic.medicalAppointment;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.basicUnit.BasicUnit;
import org.springframework.samples.petclinic.functionary.Functionary;
import org.springframework.samples.petclinic.patient.Patient;
import org.springframework.transaction.annotation.Transactional;

public interface MedicalAppointmentRepository extends  Repository<MedicalAppointment, Integer> {

		@Transactional(readOnly = true)
		Collection<MedicalAppointment> findAll() throws DataAccessException;
		
		@Transactional(readOnly = true)
		MedicalAppointment findById(Integer id);
		
		@Query("select medicalAppointment from MedicalAppointment medicalAppointment where medicalAppointment.dateMedicalAppointment = :dateMedicalAppointment")
		@Transactional(readOnly = true)
		Collection<MedicalAppointment> findByDate(Date dateMedicalAppointment) throws DataAccessException;
		
		@Query("select medicalAppointment from MedicalAppointment medicalAppointment where medicalAppointment.dateMedicalAppointment between :startDate AND :endDate ")
		@Transactional(readOnly = true)
		List<MedicalAppointment> findWeek(@Param("startDate")Date startDate,@Param("endDate")Date endDate) throws DataAccessException;
		
		@Query("select medicalAppointment from MedicalAppointment medicalAppointment where medicalAppointment.basicUnit.id = :idBasicUnit")
		@Transactional(readOnly = true)
		List<MedicalAppointment> findByIdBasicUnit(int idBasicUnit) throws DataAccessException;
		
		void save(MedicalAppointment medicalConsultation);
		
		void delete(MedicalAppointment medicalConsultation);
		
		
		@Query("select medicalAppointment from MedicalAppointment medicalAppointment where medicalAppointment.patient.id = :idPatient")
		@Transactional(readOnly = true)
		List<MedicalAppointment> findByIdPatient(Integer idPatient);
		
		//-----------------------------------------------------------------------------------
		
		@Query("SELECT x FROM Functionary x ORDER BY x.name")
		@Transactional(readOnly = true)
		List<Functionary> findFunctionaries();
		
		@Query("SELECT x FROM BasicUnit x ORDER BY x.name")
		@Transactional(readOnly = true)
		List<BasicUnit> findBasicUnits();
		
		@Query("SELECT x FROM Patient x ORDER BY x.name")
		@Transactional(readOnly = true)
		List<Patient> findPatients();
		
}
