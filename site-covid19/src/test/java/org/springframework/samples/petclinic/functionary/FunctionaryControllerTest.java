package org.springframework.samples.petclinic.functionary;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.sql.Date;
import java.sql.Time;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.petclinic.basicUnit.BasicUnit;
import org.springframework.samples.petclinic.basicUnit.BasicUnitController;
import org.springframework.samples.petclinic.basicUnit.BasicUnitRepository;
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
@WebMvcTest(FunctionaryController.class)
class FunctionaryControllerTest {

	private static final int TEST_FUNCTIONARY_ID = 1;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FunctionaryRepository functionaryRepository;

	private Functionary teste;

	@BeforeEach
	void setup() {
		teste = new Functionary();
		teste.setId(TEST_FUNCTIONARY_ID);
		teste.setName("Jonh");
		teste.setCpf("12365478999");
		teste.setDateBirth(Date.valueOf("1970-05-12"));
		teste.setTelephone("(11)111115555");
		teste.setFunction("medico");
		given(functionaryRepository.findById(TEST_FUNCTIONARY_ID)).willReturn(teste);
	}
	
	@Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/functionaries/new")).andExpect(status().isOk())
				.andExpect(model().attributeExists("functionary"))
				.andExpect(view().name("functionaries/createOrUpdateFunctionaryForm"));
	}

	@Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/functionaries/new").param("name", "ze carlos").param("telephone", "01316761638")
				.param("dateBirth", "1970-05-05").param("cpf", "12345678999").param("function", "doctor"))
				.andExpect(status().is3xxRedirection());
	}
	
	@Test void testShowBasicUnit() throws Exception {
		mockMvc.perform(get("/functionaries/{functionaryId}",TEST_FUNCTIONARY_ID))
		.andExpect(view().name("functionaries/functionaryDetails"));
	}
	
	@Test
	void testInitUpdateBasicUnitForm() throws Exception {
		mockMvc.perform(get("/functionaries/{functionaryId}/edit", TEST_FUNCTIONARY_ID)).andExpect(status().isOk())
				.andExpect(model().attributeExists("functionary"))
				.andExpect(view().name("functionaries/createOrUpdateFunctionaryForm"));
	}
	
	@Test
	void testProcessUpdateBasicUnitFormSuccess() throws Exception {
		mockMvc.perform(post("/functionaries/{functionaryId}/edit", TEST_FUNCTIONARY_ID).param("name", "ze carlos").param("telephone", "01316761638")
				.param("dateBirth", "1970-05-05").param("cpf", "12345678999").param("function", "doctor")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/functionaries"));
	}

}
