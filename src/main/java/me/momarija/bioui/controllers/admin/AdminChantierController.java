package me.momarija.bioui.controllers.admin;

import me.momarija.bioui.domains.Chantier;
import me.momarija.bioui.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admin")
public class AdminChantierController {

	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "")
	public String chantierLis(Model model){
		model.addAttribute("title", "Liste des chantier");
		model.addAttribute("chantiers", adminService.getChantierList());
		return "admin/chantierList";
	}

	@RequestMapping(value = "chantier/add", method = RequestMethod.GET)
	public String addChantierGet(Model model) {
		model.addAttribute("title", "Ajouter un chantier");
		model.addAttribute("chantier", new Chantier());
		return "admin/chantierForm";
	}

	@RequestMapping(value = "chantier/add", method = RequestMethod.POST)
	public String addChantierPost(Model model, @Valid Chantier chantier, BindingResult bindingResult){
		if (bindingResult.hasErrors()){
			model.addAttribute("title", "Erreur");
			return "admin/chantierForm";
		}
		chantier = adminService.addChantier(chantier);
		return "redirect:/admin/chantier/"+chantier.getId();
	}

	@RequestMapping(value = "chantier/{idC}/delete")
	public String deleteChantier(@PathVariable int idC){
		adminService.deleteChantier(idC);
		return "redirect:/admin/";
	}
}
