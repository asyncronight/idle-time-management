package me.momarija.bioui.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class IndexController {

	@RequestMapping(value = "/login")
	public String index(Model model, Principal principal) {
		if (principal != null)
			return "redirect:/";
		model.addAttribute("title", "Bioui Analyzer");
		return "login";
	}

	@RequestMapping(value = "/")
	public String home(Model model) {
		model.addAttribute("title", "Bioui Analyzer | Acceuil");
		return "home";
	}
}
