package org.springframework.samples.petclinic.basicUnit;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.medicalAppointment.MedicalAppointmentRepository;
import org.springframework.stereotype.Component;


@Component
public class BasicUnitFormatter implements Formatter<BasicUnit> {

	private final MedicalAppointmentRepository medicalAppointmentRepository;

	@Autowired
	public BasicUnitFormatter(MedicalAppointmentRepository medicalAppointmentRepository) {
		this.medicalAppointmentRepository = medicalAppointmentRepository;
	}

	@Override
	public String print(BasicUnit p, Locale locale) {
		return p.getName();
	}

	@Override
	public BasicUnit parse(String text, Locale locale) throws ParseException {
		Collection<BasicUnit> basicUnits = this.medicalAppointmentRepository.findBasicUnits();
		for (BasicUnit p : basicUnits) {
			if (p.getName().equals(text)) {
				return p;
			}
		}
		throw new ParseException("type not found: " + text, 0);
	}

}
