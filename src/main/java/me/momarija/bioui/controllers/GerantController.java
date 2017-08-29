package me.momarija.bioui.controllers;

import me.momarija.bioui.domains.Chantier;
import me.momarija.bioui.service.AdminChantierService;
import me.momarija.bioui.service.GerantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GerantController {

	@Autowired
	private GerantService gerantService;

	@Autowired
	private AdminChantierService chantierService;

	@RequestMapping(value = "/gerant/")
	public String chantierList(Model model) {
		model.addAttribute("title", "La liste des chantiers");
		model.addAttribute("chantiers", chantierService.getChantierList());
		return "gerant/chantierList";
	}

	@RequestMapping(value = "/gerant/chantier/{idC}")
	public String enginList(Model model, @PathVariable int idC) {
		Chantier chantier = chantierService.getChantier(idC);
		model.addAttribute("title", "Chantier : " + chantier.getNom());
		model.addAttribute("chantier", chantier);
		model.addAttribute("chantiers", chantierService.getChantierList());
		return "gerant/enginList";
	}

	@RequestMapping(value = "/gerant/chantier/{idC}/engin/{idE}/trans", method = RequestMethod.POST)
	public String transfersEngin(@PathVariable int idE, @PathVariable int idC, @RequestParam int chantierId) {
		gerantService.transferEngin(idE, chantierId);
		return "redirect:/gerant/chantier/" + idC;
	}

	@RequestMapping(value = "/donnee")
	@ResponseBody
	public String saveDonnee(@RequestParam float d1, @RequestParam float d2, @RequestParam int id) {
		gerantService.addData(d1, d2, id);
		return "ok";
	}
}
