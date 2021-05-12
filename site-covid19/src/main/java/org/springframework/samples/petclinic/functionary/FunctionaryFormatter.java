package org.springframework.samples.petclinic.functionary;


import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.medicalAppointment.MedicalAppointmentRepository;
import org.springframework.stereotype.Component;


@Component
public class FunctionaryFormatter implements Formatter<Functionary> {

	private final MedicalAppointmentRepository medicalAppointmentRepository;

	@Autowired
	public FunctionaryFormatter(MedicalAppointmentRepository medicalAppointmentRepository) {
		this.medicalAppointmentRepository = medicalAppointmentRepository;
	}

	@Override
	public String print(Functionary p, Locale locale) {
		return p.getName();
	}

	@Override
	public Functionary parse(String text, Locale locale) throws ParseException {
		Collection<Functionary> functionaries = this.medicalAppointmentRepository.findFunctionaries();
		for (Functionary p : functionaries) {
			if (p.getName().equals(text)) {
				return p;
			}
		}
		throw new ParseException("type not found: " + text, 0);
	}

}
