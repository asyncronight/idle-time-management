package me.momarija.bioui.controllers.user;

import me.momarija.bioui.domains.Statistic;
import me.momarija.bioui.service.admin.AdminChantierService;
import me.momarija.bioui.service.admin.AdminEnginService;
import me.momarija.bioui.service.user.UserEnginService;
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
public class UserEnginController {

	@Autowired
	private AdminEnginService adminEnginService;

	@Autowired
	private AdminChantierService adminChantierService;

	@Autowired
	private UserEnginService userService;

	@RequestMapping(value = {"chantier/{id}"}, method = RequestMethod.GET)
	public String enginsList(Model model, @PathVariable int id) {
		model.addAttribute("title", "Liste des engins");
		model.addAttribute("statistic", new Statistic());
		Statistic statistic = Statistic.getStatistic(Statistic.WEEK);
		model.addAttribute("enginsRendement", userService.getEnginsRendement(id, statistic));
		model.addAttribute("chantier", adminChantierService.getChantier(id));
		model.addAttribute("par", true);
		model.addAttribute("choisir", 1);
		return "user/engins";
	}

	@RequestMapping(value = {"chantier/{id}"}, method = RequestMethod.POST)
	public String enginsList(Model model, @PathVariable int id, @Valid Statistic statistic, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("title", "Erreur");
			return "user/chantiers";
		}
		model.addAttribute("choisir", 4);
		model.addAttribute("title", "Liste des engins");
		model.addAttribute("chantier", adminChantierService.getChantier(id));
		statistic.addHour();
		model.addAttribute("enginsRendement", userService.getEnginsRendement(id, statistic));
		return "user/engins";
	}

	@RequestMapping(value = {"chantier/{id}/twoWeeks"}, method = RequestMethod.GET)
	public String enginsListTwoWeeks(Model model, @PathVariable int id) {
		model.addAttribute("title", "Liste des engins");
		model.addAttribute("statistic", new Statistic());
		Statistic statistic = Statistic.getStatistic(Statistic.TWOWEEKS);
		model.addAttribute("enginsRendement", userService.getEnginsRendement(id, statistic));
		model.addAttribute("chantier", adminChantierService.getChantier(id));
		model.addAttribute("choisir", 2);
		return "user/engins";
	}

	@RequestMapping(value = {"chantier/{id}/month"}, method = RequestMethod.GET)
	public String enginsListMonth(Model model, @PathVariable int id) {
		model.addAttribute("title", "Liste des engins");
		model.addAttribute("statistic", new Statistic());
		Statistic statistic = Statistic.getStatistic(Statistic.MONTH);
		model.addAttribute("enginsRendement", userService.getEnginsRendement(id, statistic));
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

	@RequestMapping(value = {"chantier/{id}/engin/{idE}"}, method = RequestMethod.GET)
	public String enginStatisticWeek(Model model, @PathVariable int id, @PathVariable int idE) {
		model.addAttribute("title", "Statistique de l'engin " + id);
		model.addAttribute("statistic", new Statistic());
		model.addAttribute("par", true);
		model.addAttribute("choisir", 1);
		Statistic statistic = Statistic.getStatistic(Statistic.WEEK);
		model.addAttribute("enginStatistics", userService.getEnginStatistic(idE, statistic));
		model.addAttribute("engin", adminEnginService.getEngin(idE));
		return "user/enginStatistics";
	}

	@RequestMapping(value = {"chantier/{id}/engin/{idE}"}, method = RequestMethod.POST)
	public String enginStatistic(Model model, @PathVariable int id, @PathVariable int idE, @Valid Statistic statistic, BindingResult result) {
		model.addAttribute("engin", adminEnginService.getEngin(idE));
		if (result.hasErrors()) {
			model.addAttribute("title", "Erreur");
			return "user/enginStatistics";
		}
		model.addAttribute("choisir", 4);
		model.addAttribute("title", "Statistique de l'engin");
		statistic.addHour();
		model.addAttribute("enginStatistics", userService.getEnginStatistic(idE, statistic));
		return "user/enginStatistics";
	}

	@RequestMapping(value = {"chantier/{id}/engin/{idE}/twoWeeks"}, method = RequestMethod.GET)
	public String enginStatisticTwoWeek(Model model, @PathVariable int id, @PathVariable int idE) {
		model.addAttribute("title", "Statistique de l'engin " + id);
		model.addAttribute("statistic", new Statistic());
		Statistic statistic = Statistic.getStatistic(Statistic.TWOWEEKS);
		model.addAttribute("enginStatistics", userService.getEnginStatistic(idE, statistic));
		model.addAttribute("engin", adminEnginService.getEngin(idE));
		model.addAttribute("choisir", 2);
		return "user/enginStatistics";
	}

	@RequestMapping(value = {"chantier/{id}/engin/{idE}/month"}, method = RequestMethod.GET)
	public String enginStatisticMonth(Model model, @PathVariable int id, @PathVariable int idE) {
		model.addAttribute("title", "Statistique de l'engin " + id);
		model.addAttribute("statistic", new Statistic());
		Statistic statistic = Statistic.getStatistic(Statistic.MONTH);
		model.addAttribute("enginStatistics", userService.getEnginStatistic(idE, statistic));
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
	public String statisticsByDate(@PathVariable int id, @PathVariable int idC, @PathVariable String d, @PathVariable String m, @PathVariable String y, Model model) {
		String date = m + "/" + d + "/" + y;
		model.addAttribute("enginStatisticDay", userService.getEnginStatisticsDay(id, date));
		model.addAttribute("date", d + "/" + m + "/" + y);
		model.addAttribute("engin", adminEnginService.getEngin(id));
		return "user/enginStatisticsDay";
	}
}
