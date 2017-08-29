package me.momarija.bioui.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping(value = {"/", "/login" })
	public String index(Model model){
		model.addAttribute("title", "Bioui Analyzer");
		return "index";
	}

	@RequestMapping(value = "/home")
	public String home(Model model) {
		model.addAttribute("title", "Bioui Analyzer | Acceuil");
		return "home";
	}
}
