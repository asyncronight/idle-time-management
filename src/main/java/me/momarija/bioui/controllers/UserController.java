package me.momarija.bioui.controllers;

import me.momarija.bioui.domains.Chantier;
import me.momarija.bioui.domains.Statistic;
import me.momarija.bioui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/user/")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = {""}, method = RequestMethod.GET)
	public String chantiersList(Model model){
		model.addAttribute("title", "Liste des chantiers");
		model.addAttribute("statistic",new Statistic());
		return "user/chantiers";
	}

	@RequestMapping(value = {""},method = RequestMethod.POST)
	public String chantiersRendementList(Model model,@Valid Statistic statistic){
		model.addAttribute("title", "Liste des chantiers");
		model.addAttribute("statistic",statistic);
		List<Map<String,Object>> list = userService.getChantiersRendement(statistic);
		model.addAttribute("chantiersRendement",list);
		return "user/chantiers";
	}

	@RequestMapping(value = {"chantier/{id}"}, method = RequestMethod.GET)
	public String enginsList(Model model, @PathVariable int id){
		model.addAttribute("title", "Liste des engins");
		model.addAttribute("statistic",new Statistic());

		return "user/engins";
	}
	@RequestMapping(value = {"chantier/{id}"},method = RequestMethod.POST)
	public String enginsList(Model model, @PathVariable int id,@Valid Statistic statistic){
		model.addAttribute("title", "Liste des chantiers");
		model.addAttribute("statistic",statistic);
		model.addAttribute("chantierStatistics", userService.getChantierStatistics(id,statistic));
		model.addAttribute("enginsRendement", userService.getEnginsRendement(id,statistic));

		return "user/chantiers";
	}
}
