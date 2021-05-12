package org.springframework.samples.petclinic.basicUnit;

import org.assertj.core.util.Lists;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@WebMvcTest(BasicUnitController.class)
class BasicUnitControllerTest {

	private static final int TEST_BASIC_UNIT_ID = 1;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BasicUnitRepository basicUnitRepository;

	private BasicUnit teste;

	@BeforeEach
	void setup() {
		teste = new BasicUnit();
		teste.setId(TEST_BASIC_UNIT_ID);
		teste.setName("Unidade");
		teste.setTelephone("(11)11111111");
		teste.setZipCode(12700110);
		teste.setNumber(123);
		teste.setCity("Porto Alegre");
		teste.setState("Rio Grande do Sul");
		teste.setCountry("Brazil");
		teste.setOpeningTime(Time.valueOf("08:00:00"));
		teste.setClosingTime(Time.valueOf("22:00:00"));
		given(basicUnitRepository.findById(TEST_BASIC_UNIT_ID)).willReturn(teste);
	}

	@Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/basicUnits/new")).andExpect(status().isOk())
				.andExpect(model().attributeExists("basicUnit"))
				.andExpect(view().name("basicUnits/createOrUpdateBasicUnitForm"));
	}

	@Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/basicUnits/new").param("name", "unidade de teste 2").param("telephone", "01316761638")
				.param("zipCode", "123").param("city", "London").param("numer", "013").param("city", "poa")
				.param("state", "RS").param("country", "brazil").param("openingTime", "08:00:00")
				.param("closingTime", "18:00:00")).andExpect(status().is3xxRedirection());
	}
	
	@Test void testShowBasicUnit() throws Exception {
		mockMvc.perform(get("/basicUnits/{basicUnitId}",TEST_BASIC_UNIT_ID))
		.andExpect(view().name("basicUnits/basicUnitDetails"));
	}
	
	@Test
	void testInitUpdateBasicUnitForm() throws Exception {
		mockMvc.perform(get("/basicUnits/{basicUnitId}/edit", TEST_BASIC_UNIT_ID)).andExpect(status().isOk())
				.andExpect(model().attributeExists("basicUnit"))
				.andExpect(view().name("basicUnits/createOrUpdateBasicUnitForm"));
	}
	
	@Test
	void testProcessUpdateBasicUnitFormSuccess() throws Exception {
		mockMvc.perform(post("/basicUnits/{basicUnitId}/edit", TEST_BASIC_UNIT_ID).param("name", "unidade de teste 2").param("telephone", "01316761638")
				.param("zipCode", "123").param("city", "London").param("numer", "013").param("city", "poa")
				.param("state", "RS").param("country", "brazil").param("openingTime", "08:00:00")
				.param("closingTime", "18:00:00")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/basicUnits"));
	}
	
}
