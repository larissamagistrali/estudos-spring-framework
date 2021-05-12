package org.springframework.samples.petclinic.medicalAppointment;

import java.sql.Date;
import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.samples.petclinic.basicUnit.BasicUnit;
import org.springframework.samples.petclinic.functionary.Functionary;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.patient.Patient;

@Entity
@Table(name = "medical_appointments")
public class MedicalAppointment extends BaseEntity implements Comparable<MedicalAppointment> {

	@Column(name = "date_medical_appointment")
	private Date dateMedicalAppointment;

	@Column(name = "start_time")
	private Time startTime;

	@Column(name = "description")
	private String description;

	@Column(name = "end_time")
	private Time endTime;
	
	@ManyToOne
	@JoinColumn(name = "id_patient")
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name = "id_functionary")
	private Functionary functionary;
	
	@ManyToOne
	@JoinColumn(name = "id_basic_unit")
	private BasicUnit basicUnit;
	
	@Column(name="test_result")
	private boolean testResult;

	public boolean isTestResult() {
		return testResult;
	}


	public void setTestResult(boolean testResult) {
		this.testResult = testResult;
	}

	public Patient getPatient() {
		return patient;
	}


	public void setPatient(Patient patient) {
		this.patient = patient;
	}


	public Functionary getFunctionary() {
		return functionary;
	}


	public void setFunctionary(Functionary functionary) {
		this.functionary = functionary;
	}


	public BasicUnit getBasicUnit() {
		return basicUnit;
	}


	public void setBasicUnit(BasicUnit basicUnit) {
		this.basicUnit = basicUnit;
	}


	public Date getDateMedicalAppointment() {
		return dateMedicalAppointment;
	}


	public void setDateMedicalAppointment(Date dateMedicalAppointment) {
		this.dateMedicalAppointment = dateMedicalAppointment;
	}


	public MedicalAppointment() {
		dateMedicalAppointment = new Date(System.currentTimeMillis());
		endTime  = new Time(System.currentTimeMillis());
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public Time getStartTime() {
		return startTime;
	}


	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}


	public Time getEndTime() {
		return endTime;
	}


	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	@Override
	public int compareTo(MedicalAppointment medicalAppointmentOther) {
		return dateMedicalAppointment.compareTo(medicalAppointmentOther.getDateMedicalAppointment());
	}

}
