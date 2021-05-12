package org.springframework.samples.petclinic.functionary;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.springframework.samples.petclinic.model.NamedEntity;

@Entity
@Table(name = "functionaries")
public class Functionary extends NamedEntity implements Comparable<Functionary>{

	@Column(name = "date_of_birth")
	private Date dateBirth;
	
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "telephone")
	private String telephone;
	
	@Column(name = "function")
	private String function;


	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dataBirth) {
		this.dateBirth = dataBirth;
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

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	@Override
	public int compareTo(Functionary f) {
		return super.getName().compareTo(f.getName());
	}
	
	
}
