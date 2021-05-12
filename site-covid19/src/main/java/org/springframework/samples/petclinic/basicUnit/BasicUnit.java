package org.springframework.samples.petclinic.basicUnit;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.NamedEntity;

@Entity
@Table(name = "basic_units")
public class BasicUnit extends NamedEntity implements Comparable<BasicUnit>  {

	private String telephone;
	private int zipCode;
	private String street;
	private int number;
	private String city;
	private String state;
	private String country;
	private Time openingTime;
	private Time closingTime;

	public String getTelephone() {
		return telephone;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Time getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(Time openingTime) {
		this.openingTime = openingTime;
	}

	public Time getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(Time closingTime) {
		this.closingTime = closingTime;
	}

	@Override
	public int compareTo(BasicUnit b) {
		return super.getName().compareTo(b.getName());
	}
	
}
