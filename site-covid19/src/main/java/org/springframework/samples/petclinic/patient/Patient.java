package org.springframework.samples.petclinic.patient;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.samples.petclinic.medicalAppointment.MedicalAppointment;
import org.springframework.samples.petclinic.model.NamedEntity;

@Entity 
@Table(name="patients")
public class Patient extends NamedEntity implements Comparable<Patient> {
	@Column(name="date_of_birth")
	private Date dateBirth;
	
	@Column(name="date_of_death")
	private Date dateDeath;
	
	@Column(name="cpf")
	private String cpf;
	
	@Column(name="telephone")
	private String telephone;
	
	@Transient
	private Set<MedicalAppointment> medicalAppointments = new LinkedHashSet<>();

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}

	public Date getDateDeath() {
		return dateDeath;
	}

	public void setDateDeath(Date dateDeath) {
		this.dateDeath = dateDeath;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Override
	public int compareTo(Patient p) {
		return super.getName().compareTo(p.getName());
	}

	public void setMedicalAppointmentsInternal(Collection<MedicalAppointment> medicalAppointments) {
		this.medicalAppointments=new LinkedHashSet<MedicalAppointment>();
		
	}

	public void addMedicalAppointment(MedicalAppointment medicalAppointment) {
		getMedicalAppointmentsInternal().add(medicalAppointment);
		medicalAppointment.getPatient().setId(this.getId());
		
	}

	public Set<MedicalAppointment> getMedicalAppointmentsInternal() {
		if (this.medicalAppointments == null) {
			this.medicalAppointments = new HashSet<>();
		}
		return this.medicalAppointments;
	}
	
	 public List<MedicalAppointment> getMedicalAppointments() {
		List<MedicalAppointment> list = new ArrayList<>(getMedicalAppointmentsInternal());
		PropertyComparator.sort(list, new MutableSortDefinition("dateMedicalAppointment", false, false));
		return Collections.unmodifiableList(list);
	}

}

