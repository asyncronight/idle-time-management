package me.momarija.bioui.controllers;

import me.momarija.bioui.domains.Chantier;
import me.momarija.bioui.domains.Engin;
import me.momarija.bioui.domains.Statistic;
import me.momarija.bioui.services.AdminService;
import me.momarija.bioui.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/user/")
public class UserController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "")
	public String chantierList(Model model){
		model.addAttribute("title", "La liste des chantiers");
		model.addAttribute("chantiers", adminService.getChantierList());
		return "user/chantierList";
	}

	@RequestMapping(value = "chantier/{id}", method = RequestMethod.GET)
	public String showEnginsChantier(@PathVariable int id,Model model) {
		model.addAttribute("title", "La liste des chantiers");
		model.addAttribute("engins", adminService.getEnginList(id));
		model.addAttribute("chantier",adminService.getChantier(id));
		Statistic statistic = new Statistic();
		statistic.setDateTo(new Date());
		model.addAttribute("statistic",statistic);
		return "user/enginsList";
	}

	@RequestMapping(value = "chantier/{id}", method = RequestMethod.POST)
	public String getChantierStatistics(Model model, @Valid Statistic statistic,@PathVariable int id, BindingResult bindingResult){
		if (bindingResult.hasErrors()){
			model.addAttribute("title", "Erreur");
			return "user/enginsList";
		}
		model.addAttribute("title", "La liste des chantiers");
		model.addAttribute("engins", adminService.getEnginList(id));
		model.addAttribute("chantier",adminService.getChantier(id));
		model.addAttribute("statistics",userService.getChantierStatistics(id,statistic.getDateFrom(),statistic.getDateTo()));
		return "user/enginsList";
	}

	@RequestMapping(value = "chantier/{idC}/engin/{id}", method = RequestMethod.GET)
	public String showEnginChantier(@PathVariable int id,@PathVariable int idC, Model model) {
		Engin engin = adminService.getEngin(id);
		model.addAttribute("title","Engin n° "+engin.getId());
		model.addAttribute("engin",engin);
		model.addAttribute("id",idC);
		Statistic statistic = new Statistic();
		statistic.setDateTo(new Date());
		model.addAttribute("statistic",statistic);
		return  "user/engin";
	}

	@RequestMapping(value = "chantier/{idC}/engin/{id}", method = RequestMethod.POST)
	public String getEnginStatistics(@PathVariable int id,@PathVariable int idC, Model model, @Valid Statistic statistic,BindingResult bindingResult){
		if (bindingResult.hasErrors()){
			model.addAttribute("title", "Erreur");
			return "user/engin";
		}
		Engin engin = adminService.getEngin(id);
		model.addAttribute("title","Engin n° "+engin.getId());
		model.addAttribute("engin",engin);
		model.addAttribute("id",idC);
		model.addAttribute("statistics",userService.getEnginStatistics(id,statistic.getDateFrom(),statistic.getDateTo()));

		return  "user/engin";
	}
}
