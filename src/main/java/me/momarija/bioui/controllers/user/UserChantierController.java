package me.momarija.bioui.controllers.user;

import me.momarija.bioui.domains.Statistic;
import me.momarija.bioui.service.admin.AdminChantierService;
import me.momarija.bioui.service.user.UserChantierService;
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
public class UserChantierController {

	@Autowired
	private UserChantierService userService;

	@Autowired
	private AdminChantierService adminChantierService;

	@RequestMapping(value = {""}, method = RequestMethod.GET)
	public String chantiersList(Model model){
		model.addAttribute("title", "Liste des chantiers");
		Statistic statistic = Statistic.getStatistic(Statistic.WEEK);
		model.addAttribute("chantiersRendement", userService.getChantiersRendement(statistic));
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
		statistic.addHour();
		model.addAttribute("chantiersRendement", userService.getChantiersRendement(statistic));
		return "user/chantiers";
	}

	@RequestMapping(value = "twoWeeks", method = RequestMethod.GET)
	public String chantierTwoWeeks(Model model) {
		model.addAttribute("choisir", 2);
		model.addAttribute("title", "Liste des chantiers");
		Statistic statistic = Statistic.getStatistic(Statistic.TWOWEEKS);
		model.addAttribute("chantiersRendement", userService.getChantiersRendement(statistic));
		model.addAttribute("statistic", new Statistic());
		return "user/chantiers";
	}

	@RequestMapping(value = "month", method = RequestMethod.GET)
	public String chantierMonth(Model model) {
		model.addAttribute("choisir", 3);
		model.addAttribute("title", "Liste des chantiers");
		Statistic statistic = Statistic.getStatistic(Statistic.MONTH);
		model.addAttribute("chantiersRendement", userService.getChantiersRendement(statistic));
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

	@RequestMapping(value = {"chantier/{id}/statistic"}, method = RequestMethod.GET)
	public String chantierStatisticWeek(Model model, @PathVariable int id){
		model.addAttribute("title", "Statistique du chantier");
		model.addAttribute("statistic",new Statistic());
		model.addAttribute("semaine", true);
		Statistic statistic = Statistic.getStatistic(Statistic.WEEK);
		model.addAttribute("chantierStatistics", userService.getChantierStatistics(id, statistic));
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
		statistic.addHour();
		model.addAttribute("chantierStatistics", userService.getChantierStatistics(id, statistic));
		return "user/chantierStatistics";
	}

	@RequestMapping(value = {"chantier/{id}/statistic/twoWeeks"}, method = RequestMethod.GET)
	public String chantierStatisticTwoWeek(Model model, @PathVariable int id) {
		model.addAttribute("title", "Statistique du chantier");
		model.addAttribute("statistic", new Statistic());
		model.addAttribute("twoWeks", true);
		model.addAttribute("choisir", 2);
		Statistic statistic = Statistic.getStatistic(Statistic.TWOWEEKS);
		model.addAttribute("chantierStatistics", userService.getChantierStatistics(id, statistic));
		model.addAttribute("chantier", adminChantierService.getChantier(id));
		return "user/chantierStatistics";
	}

	@RequestMapping(value = {"chantier/{id}/statistic/month"}, method = RequestMethod.GET)
	public String chantierStatisticMonth(Model model, @PathVariable int id) {
		model.addAttribute("title", "Statistique du chantier");
		model.addAttribute("statistic", new Statistic());
		model.addAttribute("month", true);
		model.addAttribute("choisir", 3);
		Statistic statistic = Statistic.getStatistic(Statistic.MONTH);
		model.addAttribute("chantierStatistics", userService.getChantierStatistics(id, statistic));
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

	@RequestMapping(value = "chantier/{idC}/{d}/{m}/{y}", method = RequestMethod.GET)
	public String statsByDate(@PathVariable int idC, @PathVariable String d, @PathVariable String m, @PathVariable String y, Model model) {
		String date = m + "/" + d + "/" + y;
		model.addAttribute("chantierStatisticDay", userService.getChantierStatisticsDay(idC, date));
		model.addAttribute("date", d+ "/" + m + "/" +y);
		model.addAttribute("chantier", adminChantierService.getChantier(idC));
		return "user/chantierStatisticsDay";
	}
}
