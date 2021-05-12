package org.springframework.samples.petclinic.patient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.petclinic.basicUnit.BasicUnit;
import org.springframework.samples.petclinic.basicUnit.BasicUnitController;
import org.springframework.samples.petclinic.basicUnit.BasicUnitRepository;
import org.springframework.samples.petclinic.functionary.Functionary;
import org.springframework.samples.petclinic.functionary.FunctionaryController;
import org.springframework.samples.petclinic.functionary.FunctionaryRepository;
import org.springframework.samples.petclinic.medicalAppointment.MedicalAppointmentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

@RunWith(SpringRunner.class)
@WebMvcTest(PatientController.class)
class PatientControllerTest {

	private static final int TEST_PATIENT_ID = 1;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PatientRepository patientRepository;

	@MockBean
	private MedicalAppointmentRepository medicalAppointmentRepository;

	private Patient teste;

	@BeforeEach
	void setup() {
		teste = new Patient();
		teste.setId(TEST_PATIENT_ID);
		teste.setName("Jonh");
		teste.setCpf("12365478999");
		teste.setDateBirth(Date.valueOf("1970-05-12"));
		teste.setTelephone("(11)111115555");
		given(patientRepository.findById(TEST_PATIENT_ID)).willReturn(teste);
	}

	@Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/patients/new")).andExpect(status().isOk()).andExpect(model().attributeExists("patient"))
				.andExpect(view().name("patients/createOrUpdatePatientForm"));
	}

	@Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/patients/new").param("name", "ze carlos").param("telephone", "01316761638")
				.param("dateBirth", "1970-05-05").param("cpf", "12345678999")).andExpect(status().is3xxRedirection());
	}

	@Test
	void testShowPatient() throws Exception {
		mockMvc.perform(get("/patients/{patientId}", TEST_PATIENT_ID))
				.andExpect(view().name("patients/patientDetails"));
	}
	
	@Test
	void testInitUpdatePatientForm() throws Exception {
		mockMvc.perform(get("/patients/{patientId}/edit", TEST_PATIENT_ID)).andExpect(status().isOk())
				.andExpect(model().attributeExists("patient"))
				.andExpect(view().name("patients/createOrUpdatePatientForm"));
	}
	
	@Test
	void testProcessUpdatePatientFormSuccess() throws Exception {
		mockMvc.perform(post("/patients/{patientId}/edit", TEST_PATIENT_ID).param("name", "ze carlos").param("telephone", "01316761638")
				.param("dateBirth", "1970-05-05").param("cpf", "12345678999")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/patients"));
	}
}
