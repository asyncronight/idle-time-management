package me.momarija.bioui.controllers;

import me.momarija.bioui.domains.Statistic;
import me.momarija.bioui.service.AdminChantierService;
import me.momarija.bioui.service.AdminEnginService;
import me.momarija.bioui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/user/")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AdminEnginService adminEnginService;

	@Autowired
	private AdminChantierService adminChantierService;

	@RequestMapping(value = {""}, method = RequestMethod.GET)
	public String chantiersList(Model model){
		model.addAttribute("title", "Liste des chantiers");
		model.addAttribute("chantiersRendement",userService.getChantiersRendementWeek());
		model.addAttribute("statistic",new Statistic());
		model.addAttribute("par", true);
		model.addAttribute("choisir", 1);



		return "user/chantiers";
	}

	@RequestMapping(value = {""},method = RequestMethod.POST)
	public String chantiersRendementList(Model model, @Valid Statistic statistic, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("title", "Erreur");

			return "user/chantiers";
		}

		model.addAttribute("choisir", 4);
		model.addAttribute("title", "Liste des chantiers");
		model.addAttribute("chantiersRendement", userService.getChantiersRendement(statistic));
		return "user/chantiers";
	}

	@RequestMapping(value = "twoWeeks", method = RequestMethod.GET)
	public String chantierTwoWeeks(Model model) {
		model.addAttribute("choisir", 2);
		model.addAttribute("title", "Liste des chantiers");
		model.addAttribute("chantiersRendement", userService.getChantiersRendementTwoWeek());
		model.addAttribute("statistic", new Statistic());
		return "user/chantiers";
	}

	@RequestMapping(value = "month", method = RequestMethod.GET)
	public String chantierMonth(Model model) {
		model.addAttribute("choisir", 3);
		model.addAttribute("title", "Liste des chantiers");
		model.addAttribute("chantiersRendement", userService.getChantiersRendementMonth());
		model.addAttribute("statistic", new Statistic());
		return "user/chantiers";
	}

	@RequestMapping(value = "twoWeeks", method = RequestMethod.POST)
	public String chantierTwoWeeksPost() {
		return "forward:/user/";
	}

	@RequestMapping(value = "month", method = RequestMethod.POST)
	public String chantierMonthPost() {
		return "forward:/user/";
	}

	@RequestMapping(value = {"chantier/{id}"}, method = RequestMethod.GET)
	public String enginsList(Model model, @PathVariable int id){
		model.addAttribute("title", "Liste des engins");
		model.addAttribute("statistic",new Statistic());
		model.addAttribute("enginsRendement",userService.getEnginsRendementWeek(id));
		model.addAttribute("chantier", adminChantierService.getChantier(id));
		model.addAttribute("par", true);
		model.addAttribute("choisir", 1);

		return "user/engins";
	}

	@RequestMapping(value = {"chantier/{id}"},method = RequestMethod.POST)
	public String enginsList(Model model, @PathVariable int id, @Valid Statistic statistic, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("title", "Erreur");
			return "user/chantiers";
		}
		model.addAttribute("choisir", 4);

		model.addAttribute("title", "Liste des engins");
		model.addAttribute("chantier", adminChantierService.getChantier(id));
		model.addAttribute("enginsRendement", userService.getEnginsRendement(id, statistic));
		return "user/engins";
	}

	@RequestMapping(value = {"chantier/{id}/twoWeeks"}, method = RequestMethod.GET)
	public String enginsListTwoWeeks(Model model, @PathVariable int id) {
		model.addAttribute("title", "Liste des engins");
		model.addAttribute("statistic", new Statistic());
		model.addAttribute("enginsRendement", userService.getEnginsRendementTwoWeek(id));
		model.addAttribute("chantier", adminChantierService.getChantier(id));
		model.addAttribute("choisir", 2);

		return "user/engins";
	}

	@RequestMapping(value = {"chantier/{id}/month"}, method = RequestMethod.GET)
	public String enginsListMonth(Model model, @PathVariable int id) {
		model.addAttribute("title", "Liste des engins");
		model.addAttribute("statistic", new Statistic());
		model.addAttribute("enginsRendement", userService.getEnginsRendementMonth(id));
		model.addAttribute("chantier", adminChantierService.getChantier(id));
		model.addAttribute("choisir", 3);

		return "user/engins";
	}

	@RequestMapping(value = {"chantier/{id}/twoWeeks"}, method = RequestMethod.POST)
	public String enginsListTwoWeeksPost(@PathVariable int id) {
		return "forward:/user/chantier/" + id;
	}

	@RequestMapping(value = {"chantier/{id}/month"}, method = RequestMethod.POST)
	public String enginsListMonthPost(@PathVariable int id) {
		return "forward:/user/chantier/" + id;
	}

	@RequestMapping(value = {"chantier/{id}/statistic"}, method = RequestMethod.GET)
	public String chantierStatisticWeek(Model model, @PathVariable int id){
		model.addAttribute("title", "Statistique du chantier");
		model.addAttribute("statistic",new Statistic());
		model.addAttribute("semaine", true);
		model.addAttribute("chantierStatistics", userService.getChantierStatisticsWeek(id));
		model.addAttribute("chantier", adminChantierService.getChantier(id));
		model.addAttribute("choisir", 1);

		return "user/chantierStatistics";
	}

	@RequestMapping(value = {"chantier/{id}/statistic"},method = RequestMethod.POST)
	public String chantierStatistic(Model model, @PathVariable int id, @Valid Statistic statistic, BindingResult result) {
		model.addAttribute("chantier", adminChantierService.getChantier(id));
		if (result.hasErrors()) {
			model.addAttribute("title", "Erreur");
			return "user/chantierStatistics";
		}
		model.addAttribute("title", "Statistique du chantier");
		model.addAttribute("choisir", 4);

		model.addAttribute("chantierStatistics", userService.getChantierStatistics(id, statistic));
		return "user/chantierStatistics";
	}

	@RequestMapping(value = {"chantier/{id}/statistic/twoWeeks"}, method = RequestMethod.GET)
	public String chantierStatisticTwoWeek(Model model, @PathVariable int id) {
		model.addAttribute("title", "Statistique du chantier");
		model.addAttribute("statistic", new Statistic());
		model.addAttribute("twoWeks", true);
		model.addAttribute("choisir", 2);

		model.addAttribute("chantierStatistics", userService.getChantierStatisticsTwoWeek(id));
		model.addAttribute("chantier", adminChantierService.getChantier(id));
		return "user/chantierStatistics";
	}

	@RequestMapping(value = {"chantier/{id}/statistic/month"}, method = RequestMethod.GET)
	public String chantierStatisticMonth(Model model, @PathVariable int id) {
		model.addAttribute("title", "Statistique du chantier");
		model.addAttribute("statistic", new Statistic());
		model.addAttribute("month", true);
		model.addAttribute("choisir", 3);

		model.addAttribute("chantierStatistics", userService.getChantierStatisticsMonth(id));
		model.addAttribute("chantier", adminChantierService.getChantier(id));
		return "user/chantierStatistics";
	}

	@RequestMapping(value = {"chantier/{id}/statistic/twoWeeks"}, method = RequestMethod.POST)
	public String chantierStatisticTwoWeekPost(@PathVariable int id) {
		return "forward:/user/chantier/" + id + "/statistic";
	}

	@RequestMapping(value = {"chantier/{id}/statistic/month"}, method = RequestMethod.POST)
	public String chantierStatisticMonthPost(@PathVariable int id) {
		return "forward:/user/chantier/" + id + "/statistic";
	}

	@RequestMapping(value = {"chantier/{id}/engin/{idE}"}, method = RequestMethod.GET)
	public String enginStatisticWeek(Model model, @PathVariable int id,@PathVariable int idE){
		model.addAttribute("title", "Statistique de l'engin " + id);
		model.addAttribute("statistic",new Statistic());
		model.addAttribute("par", true);
		model.addAttribute("choisir", 1);

		model.addAttribute("enginStatistics", userService.getEnginStatisticsWeek(idE));
		model.addAttribute("engin", adminEnginService.getEngin(idE));
		return "user/enginStatistics";
	}

	@RequestMapping(value = {"chantier/{id}/engin/{idE}"},method = RequestMethod.POST)
	public String enginStatistic(Model model, @PathVariable int id, @PathVariable int idE, @Valid Statistic statistic, BindingResult result) {
		model.addAttribute("engin", adminEnginService.getEngin(idE));
		if (result.hasErrors()) {
			model.addAttribute("title", "Erreur");
			return "user/enginStatistics";
		}
		model.addAttribute("choisir", 4);

		model.addAttribute("title", "Statistique de l'engin");
		model.addAttribute("enginStatistics", userService.getEnginStatistic(idE,statistic));
		return "user/enginStatistics";
	}

	@RequestMapping(value = {"chantier/{id}/engin/{idE}/twoWeeks"}, method = RequestMethod.GET)
	public String enginStatisticTwoWeek(Model model, @PathVariable int id, @PathVariable int idE) {
		model.addAttribute("title", "Statistique de l'engin " + id);
		model.addAttribute("statistic", new Statistic());
		model.addAttribute("enginStatistics", userService.getEnginStatisticsTwoWeek(idE));
		model.addAttribute("engin", adminEnginService.getEngin(idE));
		model.addAttribute("choisir", 2);

		return "user/enginStatistics";
	}

	@RequestMapping(value = {"chantier/{id}/engin/{idE}/month"}, method = RequestMethod.GET)
	public String enginStatisticMonth(Model model, @PathVariable int id, @PathVariable int idE) {
		model.addAttribute("title", "Statistique de l'engin " + id);
		model.addAttribute("statistic", new Statistic());
		model.addAttribute("enginStatistics", userService.getEnginStatisticsMonth(idE));
		model.addAttribute("engin", adminEnginService.getEngin(idE));
		model.addAttribute("choisir", 3);


		return "user/enginStatistics";
	}

	@RequestMapping(value = {"chantier/{id}/engin/{idE}/twoWeeks"}, method = RequestMethod.POST)
	public String enginStatisticTwoWeekPost(@PathVariable int id, @PathVariable int idE) {
		return "forward:/user/chantier/" + id + "/engin/" + idE;
	}

	@RequestMapping(value = {"chantier/{id}/engin/{idE}/month"}, method = RequestMethod.POST)
	public String enginStatisticMonthPost(@PathVariable int id, @PathVariable int idE) {
		return "forward:/user/chantier/" + id + "/engin/" + idE;
	}

	@RequestMapping(value = "chantier/{idC}/engin/{id}/{d}/{m}/{y}", method = RequestMethod.GET)
	public String statisticsByDate(@PathVariable int id,@PathVariable int idC, @PathVariable String d, @PathVariable String m, @PathVariable String y, Model model){
		String date = m+"/"+d+"/"+y;
		model.addAttribute("enginStatisticDay", userService.getEnginStatisticsDay(id,date));
		model.addAttribute("date", d+ "/" + m + "/" +y);
		model.addAttribute("engin", adminEnginService.getEngin(id));
		return "user/enginStatisticsDay";
	}

	@RequestMapping(value = "chantier/{idC}/{d}/{m}/{y}", method = RequestMethod.GET)
	public String statsByDate(@PathVariable int idC, @PathVariable String d, @PathVariable String m, @PathVariable String y, Model model) {
		String date = m + "/" + d + "/" + y;
		model.addAttribute("chantierStatisticDay", userService.getChantierStatisticsDay(idC, date));
		model.addAttribute("date", d+ "/" + m + "/" +y);
		model.addAttribute("chantier", adminChantierService.getChantier(idC));
		return "user/chantierStatisticsDay";
	}
}
