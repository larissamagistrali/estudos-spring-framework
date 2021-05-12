package org.springframework.samples.petclinic.patient;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.medicalAppointment.MedicalAppointmentRepository;
import org.springframework.stereotype.Component;


@Component
public class PatientFormatter implements Formatter<Patient> {

	private final MedicalAppointmentRepository medicalAppointmentRepository;

	@Autowired
	public PatientFormatter(MedicalAppointmentRepository medicalAppointmentRepository) {
		this.medicalAppointmentRepository = medicalAppointmentRepository;
	}

	@Override
	public String print(Patient p, Locale locale) {
		return p.getName();
	}

	@Override
	public Patient parse(String text, Locale locale) throws ParseException {
		Collection<Patient> patients = this.medicalAppointmentRepository.findPatients();
		for (Patient p : patients) {
			if (p.getName().equals(text)) {
				return p;
			}
		}
		throw new ParseException("type not found: " + text, 0);
	}

}
