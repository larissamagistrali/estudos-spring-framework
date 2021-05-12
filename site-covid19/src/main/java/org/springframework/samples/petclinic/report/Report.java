package org.springframework.samples.petclinic.report;

import java.sql.Date;
import java.util.Calendar;

import javax.persistence.Entity;

import org.springframework.samples.petclinic.model.BaseEntity;

@Entity
public class Report extends BaseEntity {
	private Date dateInit;

	public Date getDateInit() {
		return dateInit;
	}
	public void setDateInit(Date dateInit) {
		this.dateInit = dateInit;
	}

}
