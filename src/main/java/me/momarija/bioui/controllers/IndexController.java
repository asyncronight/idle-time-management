package me.momarija.bioui.controllers;

import me.momarija.bioui.services.GerantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

	@Autowired
	private GerantService gerantService;

	@RequestMapping(value = {"/", "/login" })
	public String index(Model model){
		model.addAttribute("title", "Bioui Analyzer");
		return "index";
	}

	@RequestMapping(value = "/donnee")
	@ResponseBody
	public String saveDonnee(@RequestParam float d1, @RequestParam float d2, @RequestParam int id){
		gerantService.addDonne(d1, d2, id);
		return "ok";
	}
}
