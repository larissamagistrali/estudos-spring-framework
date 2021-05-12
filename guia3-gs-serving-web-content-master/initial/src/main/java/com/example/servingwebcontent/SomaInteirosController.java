package com.example.servingwebcontent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SomaInteirosController {
	@GetMapping("/soma")
	public String soma(@RequestParam int a, @RequestParam int b,Model model) {
		model.addAttribute("param_a",a);
		model.addAttribute("param_b",b);
		model.addAttribute("param_soma",a+b);
		return "soma" ;
	}

}