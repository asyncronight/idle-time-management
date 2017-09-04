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
		model.addAttribute("chantiersRendementWeek",userService.getChantiersRendementWeek());
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
		model.addAttribute("enginsRendementWeek",userService.getEnginsRendementWeek(id));
		return "user/engins";
	}

	@RequestMapping(value = {"chantier/{id}"},method = RequestMethod.POST)
	public String enginsList(Model model, @PathVariable int id,@Valid Statistic statistic){
		model.addAttribute("title", "Liste des engins");
		model.addAttribute("statistic",statistic);
		model.addAttribute("enginsRendementWeek", userService.getEnginsRendement(id,statistic));
		return "user/engins";
	}

	@RequestMapping(value = {"chantier/{id}/statistic"}, method = RequestMethod.GET)
	public String chantierStatisticWeek(Model model, @PathVariable int id){
		model.addAttribute("title", "Statistique du chantier");
		model.addAttribute("statistic",new Statistic());
		model.addAttribute("par","de la semaine");
		model.addAttribute("chantierStatisticsWeek",userService.getChantierStatisticsWeek(id));
		return "user/chantierStatistics";
	}

	@RequestMapping(value = {"chantier/{id}/statistic"},method = RequestMethod.POST)
	public String chantierStatistic(Model model, @PathVariable int id,@Valid Statistic statistic){
		model.addAttribute("title", "Statistique du chantier");
		model.addAttribute("statistic",statistic);
		model.addAttribute("chantierStatisticsWeek", userService.getChantierStatistics(id,statistic));
		return "user/chantierStatistics";
	}
	@RequestMapping(value = {"chantier/{id}/engin/{idE}"}, method = RequestMethod.GET)
	public String enginStatisticWeek(Model model, @PathVariable int id,@PathVariable int idE){
		model.addAttribute("title", "Statistique de l'engin");
		model.addAttribute("statistic",new Statistic());
		model.addAttribute("par","de la semaine");
		model.addAttribute("enginStatisticsWeek",userService.getEnginStatisticsWeek(idE));
		return "user/enginStatistics";
	}

	@RequestMapping(value = {"chantier/{id}/engin/{idE}"},method = RequestMethod.POST)
	public String enginStatistic(Model model, @PathVariable int id,@PathVariable int idE,@Valid Statistic statistic){
		model.addAttribute("title", "Statistique de l'engin");
		model.addAttribute("statistic",statistic);
		model.addAttribute("enginStatistics", userService.getEnginStatistic(idE,statistic));
		return "user/enginStatistics";
	}

}
