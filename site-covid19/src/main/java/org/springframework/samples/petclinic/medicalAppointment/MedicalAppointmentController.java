package org.springframework.samples.petclinic.medicalAppointment;

import java.sql.Time;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.samples.petclinic.basicUnit.BasicUnit;
import org.springframework.samples.petclinic.basicUnit.BasicUnitRepository;
import org.springframework.samples.petclinic.functionary.Functionary;
import org.springframework.samples.petclinic.functionary.FunctionaryRepository;
import org.springframework.samples.petclinic.patient.Patient;
import org.springframework.samples.petclinic.patient.PatientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MedicalAppointmentController {
	
	private static final String VIEWS_MEDICAL_APPOINTMENT_CREATE_OR_UPDATE_FORM = "medicalAppointments/createOrUpdateMedicalAppointmentForm";
	private Time start;
	private final MedicalAppointmentRepository medicalAppointmentRepository;
	private final FunctionaryRepository functionaryRepository;	
	private final PatientRepository patientRepository;
	private final BasicUnitRepository basicUnitRepository;
	
	public MedicalAppointmentController(MedicalAppointmentRepository medicalAppointmentRepository, FunctionaryRepository functionaryRepository, PatientRepository patientRepository, BasicUnitRepository basicUnitRepository) {
		this.medicalAppointmentRepository = medicalAppointmentRepository;
		this.functionaryRepository = functionaryRepository;
		this.patientRepository = patientRepository;
		this.basicUnitRepository=basicUnitRepository;
	}
	@GetMapping("/medicalAppointments/new")
	public String initCreationForm(Map<String, Object> model) {
		start = new Time(System.currentTimeMillis());
		MedicalAppointment ma = new MedicalAppointment();
		model.put("medicalAppointment", ma);
		return VIEWS_MEDICAL_APPOINTMENT_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping("/medicalAppointments/new")
	public String processCreationForm(MedicalAppointment medicalAppointment) {
		if(medicalAppointment.getStartTime()== null) {
			medicalAppointment.setStartTime(start);
		}
		this.medicalAppointmentRepository.save(medicalAppointment);
		return "redirect:/patients";

	}
	
	@GetMapping("/medicalAppointments/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("medicalAppointment", new MedicalAppointment());
		return "medicalAppointments/findMedicalAppointment";
	}
	
	
	@GetMapping("/medicalAppointments")
	public String processFindForm(MedicalAppointment medicalAppointment, Map<String, Object> model) {
		Collection<MedicalAppointment> results = this.medicalAppointmentRepository.findByDate(medicalAppointment.getDateMedicalAppointment());
		//empty consult
		if(results.isEmpty()) {
			return "redirect:/medicalAppointments/find";
		}
		// multiple medicalAppointment found
		model.put("selections", results);
		return "medicalAppointments/medicalAppointmentList";

	}

	@GetMapping("/medicalAppointments/{medicalAppointmentId}")
	public ModelAndView showMedicalAppointment(@PathVariable("medicalAppointmentId") int medicalAppointmentId) {
		ModelAndView mav = new ModelAndView("medicalAppointments/medicalAppointmentDetails");
		MedicalAppointment medicalAppointment = this.medicalAppointmentRepository.findById(medicalAppointmentId);
		Patient patient = this.patientRepository.findById(medicalAppointment.getPatient().getId());
		Functionary functionary = this.functionaryRepository.findById(medicalAppointment.getFunctionary().getId());
		BasicUnit basicUnit = this.basicUnitRepository.findById(medicalAppointment.getBasicUnit().getId());
		mav.addObject("basicUnit",basicUnit);
		mav.addObject("patient",patient);
		mav.addObject("functionary",functionary);
		mav.addObject("medicalAppointments", medicalAppointment);
		return mav;
	}
	
	@ModelAttribute("allPatients")
	public Collection<Patient> populatePatient() {
		return this.medicalAppointmentRepository.findPatients();
	}
	@ModelAttribute("allBasicUnits")
	public Collection<BasicUnit> populateBasicUnits() {
		return this.medicalAppointmentRepository.findBasicUnits();
	}
	@ModelAttribute("allFunctionaries")
	public Collection<Functionary> populateFunctionaries() {
		return this.medicalAppointmentRepository.findFunctionaries();
	}
	
	
	@GetMapping("/medicalAppointments/{idMedicalAppointment}/edit")
	public String initUpdateMedicalAppointementForm(@PathVariable("idMedicalAppointment") int idMedicalAppointment, Model model) {
		MedicalAppointment ma = this.medicalAppointmentRepository.findById(idMedicalAppointment);
		model.addAttribute(ma);
		return VIEWS_MEDICAL_APPOINTMENT_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/medicalAppointments/{idMedicalAppointment}/edit")
	public String processUpdateMedicalAppointmentForm(@Valid MedicalAppointment ma,@PathVariable("idMedicalAppointment") int idMedicalAppointment) {
		MedicalAppointment funcAux = this.medicalAppointmentRepository.findById(idMedicalAppointment);
		
		funcAux.setDateMedicalAppointment(ma.getDateMedicalAppointment());
		funcAux.setStartTime(ma.getStartTime());
		funcAux.setEndTime(ma.getEndTime());
		funcAux.setDescription(ma.getDescription());
		funcAux.setPatient(ma.getPatient());
		funcAux.setFunctionary(ma.getFunctionary());
		funcAux.setBasicUnit(ma.getBasicUnit());
		funcAux.setTestResult(ma.isTestResult());
		funcAux.setDateMedicalAppointment(ma.getDateMedicalAppointment());
		
		this.medicalAppointmentRepository.save(funcAux);
		return "redirect:/patients";
	}
	
}
