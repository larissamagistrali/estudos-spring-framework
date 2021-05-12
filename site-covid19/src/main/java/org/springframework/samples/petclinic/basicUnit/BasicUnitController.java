package org.springframework.samples.petclinic.basicUnit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BasicUnitController {

	private static final String VIEWS_BASIC_UNIT_CREATE_OR_UPDATE_FORM = "basicUnits/createOrUpdateBasicUnitForm";
	
	@Autowired
	private final BasicUnitRepository basicUnitRepository;

	public BasicUnitController(BasicUnitRepository basicUnitRepository) {
		this.basicUnitRepository = basicUnitRepository;
	}

	// Lista Todas Unidades Cadastradas
	@GetMapping("/basicUnits")
	public String showBasicUnitsList(Model model) {
		List<BasicUnit> basicUnitsList = new ArrayList<BasicUnit>();
		basicUnitsList.addAll(this.basicUnitRepository.findAll());
		Collections.sort(basicUnitsList);
		model.addAttribute("basicUnits", basicUnitsList);
		return "basicUnits/basicUnitsList";
	}

	// Mostra detalhadamente os dados da unidade
	@GetMapping("/basicUnits/{basicUnitId}")
	public ModelAndView showBasicUnit(@PathVariable("basicUnitId") int basicUnitId) {
		ModelAndView mav = new ModelAndView("basicUnits/basicUnitDetails");
		BasicUnit basicUnit = this.basicUnitRepository.findById(basicUnitId);
		mav.addObject(basicUnit);
		return mav;
	}

	// Adiciona novas unidades basicas ao sistema
	@GetMapping("/basicUnits/new")
	public String initCreationForm(Map<String, Object> model) {
		BasicUnit basicUnit = new BasicUnit();
		model.put("basicUnit", basicUnit);
		return VIEWS_BASIC_UNIT_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/basicUnits/new")
	public String processCreationForm(BasicUnit basicUnit) {
		this.basicUnitRepository.save(basicUnit);
		return "redirect:/basicUnits";

	}

	//Edita as unidades basicas cadastradas
	@GetMapping("/basicUnits/{basicUnitId}/edit")
	public String initUpdateBasicUnitForm(@PathVariable("basicUnitId") int basicUnitId, Model model) {
		BasicUnit basicUnit = this.basicUnitRepository.findById(basicUnitId);  
		model.addAttribute(basicUnit);
		return VIEWS_BASIC_UNIT_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/basicUnits/{basicUnitId}/edit")
	public String processUpdateBasicUnitForm(@Valid BasicUnit basicUnit, @PathVariable("basicUnitId")  int basicUnitId, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_BASIC_UNIT_CREATE_OR_UPDATE_FORM;
		}else {
			BasicUnit ub = this.basicUnitRepository.findById(basicUnitId);
			ub.setName(basicUnit.getName());
			ub.setTelephone(basicUnit.getTelephone());
			ub.setZipCode(basicUnit.getZipCode());
			ub.setStreet(basicUnit.getStreet());
			ub.setNumber(basicUnit.getNumber());
			ub.setCity(basicUnit.getCity());
			ub.setState(basicUnit.getState());
			ub.setCountry(basicUnit.getCountry());
			ub.setOpeningTime(basicUnit.getOpeningTime());
			ub.setClosingTime(basicUnit.getClosingTime());
			this.basicUnitRepository.save(ub);
			return "redirect:/basicUnits";
		}
		
	}
	
	/*//remove as unidades basicas cadastradas
		@GetMapping("/basicUnits/{basicUnitId}/del")
		public String initDeleteBasicUnitForm(@PathVariable("basicUnitId") int basicUnitId, Model model) {
			BasicUnit basicUnit = this.basicUnitRepository.findById(basicUnitId);
			this.basicUnitRepository.delete(basicUnit);
			return "redirect:/basicUnits";
		}*/
}
