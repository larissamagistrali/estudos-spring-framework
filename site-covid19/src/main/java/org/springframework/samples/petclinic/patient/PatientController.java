package org.springframework.samples.petclinic.patient;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.samples.petclinic.medicalAppointment.MedicalAppointment;
import org.springframework.samples.petclinic.medicalAppointment.MedicalAppointmentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PatientController {
	private static final String VIEWS_PATIENT_CREATE_OR_UPDATE_FORM="patients/createOrUpdatePatientForm";
	private final PatientRepository patientRepository;
	private final MedicalAppointmentRepository medicalAppointmentRepository;
	
	public PatientController(PatientRepository patientRepository, MedicalAppointmentRepository medicalAppointmentRepository) {
		this.patientRepository = patientRepository;
		this.medicalAppointmentRepository = medicalAppointmentRepository;
	}

	@GetMapping("/patients")
	public String showPatientsList(Model model) {
		List<Patient> patientList = new ArrayList<Patient>();
		patientList.addAll(this.patientRepository.findAll());
		Collections.sort(patientList);
		model.addAttribute("patients", patientList);
		return "/patients/patientsList";
	}

	@GetMapping("/patients/{patientId}")
	public ModelAndView showPatient(@PathVariable("patientId") int patientId) {
		ModelAndView mav = new ModelAndView("patients/patientDetails");
		
		Patient patient = this.patientRepository.findById(patientId);
		List<MedicalAppointment> medicalAppointments = this.medicalAppointmentRepository.findByIdPatient(patientId);
		mav.addObject("patients", patient);
		mav.addObject("medicalAppointments", medicalAppointments);
		
		return mav;
	}

	@GetMapping("/patients/new")
	public String initCreationForm(Map<String, Object> model) {
		Patient patient = new Patient();
		model.put("patient", patient);
		return VIEWS_PATIENT_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/patients/new")
	public String processCreationForm(Patient patient) {
		this.patientRepository.save(patient);
		return "redirect:/patients";

	}

	@GetMapping("/patients/{patientId}/edit")
	public String initUpdatePatientForm(@PathVariable("patientId") int patientId, Model model) {
		Patient patient = this.patientRepository.findById(patientId);
		model.addAttribute(patient);
		return VIEWS_PATIENT_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/patients/{patientId}/edit")
	public String processUpdatePatientForm(@Valid Patient patient,@PathVariable("patientId") int patientId) {
		Patient funcAux = this.patientRepository.findById(patientId);
		
		funcAux.setName(patient.getName());
		funcAux.setDateBirth(patient.getDateBirth());
		funcAux.setDateDeath(patient.getDateDeath());
		funcAux.setCpf(patient.getCpf());
		funcAux.setTelephone(patient.getTelephone());
		
		this.patientRepository.save(funcAux);
		return "redirect:/patients";
	}

	@GetMapping("/patients/{patientId}/del")
	public String initDeletePatientForm(@PathVariable("patientId") int patientId, Model model) {
			Patient funcAux = this.patientRepository.findById(patientId);
			this.patientRepository.delete(funcAux);
			return "redirect:/patients";
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

}

