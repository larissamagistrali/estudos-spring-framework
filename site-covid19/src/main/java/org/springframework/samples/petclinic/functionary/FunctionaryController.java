package org.springframework.samples.petclinic.functionary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FunctionaryController {

	private static final String VIEWS_FUNCTIONARY_CREATE_OR_UPDATE_FORM = "functionaries/createOrUpdateFunctionaryForm";

	private final FunctionaryRepository functionaryRepository;

	public FunctionaryController(FunctionaryRepository functionaryRepository) {
		this.functionaryRepository = functionaryRepository;
	}

	// Lista Todos os funcionarios
	@GetMapping("/functionaries")
	public String showFunctionariesList(Model model) {
		List<Functionary> functionariesList = new ArrayList<Functionary>();
		functionariesList.addAll(this.functionaryRepository.findAll());
		Collections.sort(functionariesList);
		model.addAttribute("functionaries", functionariesList);
		return "/functionaries/functionariesList";
	}

	// Mostra detalhadamente os dados do funcionario
	@GetMapping("/functionaries/{functionaryId}")
	public ModelAndView showFunctionary(@PathVariable("functionaryId") int functionaryId) {
		ModelAndView mav = new ModelAndView("functionaries/functionaryDetails");
		Functionary functionary = this.functionaryRepository.findById(functionaryId);
		mav.addObject("functionaries", functionary);
		return mav;
	}

	// Adiciona novos funcionarios ao sistema
	@GetMapping("/functionaries/new")
	public String initCreationForm(Map<String, Object> model) {
		Functionary functionary = new Functionary();
		model.put("functionary", functionary);
		return VIEWS_FUNCTIONARY_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/functionaries/new")
	public String processCreationForm(Functionary functionary) {
		this.functionaryRepository.save(functionary);
		return "redirect:/functionaries";

	}

	// Edita os dados do funcionario cadastrado
	@GetMapping("/functionaries/{functionaryId}/edit")
	public String initUpdateFunctionaryForm(@PathVariable("functionaryId") int functionaryId, Model model) {
		Functionary functionary = this.functionaryRepository.findById(functionaryId);
		model.addAttribute(functionary);
		return VIEWS_FUNCTIONARY_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/functionaries/{functionaryId}/edit")
	public String processUpdateFunctionaryForm(@Valid Functionary functionary,
			@PathVariable("functionaryId") int functionaryId) {
		Functionary funcAux = this.functionaryRepository.findById(functionaryId);
		funcAux.setName(functionary.getName());
		funcAux.setTelephone(functionary.getTelephone());
		funcAux.setFunction(functionary.getFunction());
		funcAux.setCpf(functionary.getCpf());
		funcAux.setDateBirth(functionary.getDateBirth());
		this.functionaryRepository.save(funcAux);
		return "redirect:/functionaries";

	}

	// remove funcionario cadastrado
	@GetMapping("/functionaries/{idFunctionary}/del")
	public String initDeleteUnidadeBasicaForm(@PathVariable("idFunctionary") int idFunctionary, Model model) {
			Functionary funcAux = this.functionaryRepository.findById(idFunctionary);
			this.functionaryRepository.delete(funcAux);
			return "redirect:/functionaries";
	}

}
