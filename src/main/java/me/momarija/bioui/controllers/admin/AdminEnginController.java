package me.momarija.bioui.controllers.admin;

import me.momarija.bioui.domains.Chantier;
import me.momarija.bioui.domains.Engin;
import me.momarija.bioui.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admin/chantier/{idC}")
public class AdminEnginController {

	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "")
	public String enginList(@PathVariable int idC, Model model){
		Chantier chantier = adminService.getChantier(idC);
		model.addAttribute("title", chantier.getNom());
		model.addAttribute("chantier", chantier);
		return "admin/enginList";
	}

	@RequestMapping(value = "/addEngin", method = RequestMethod.GET)
	public String addEnginGet(Model model){
		model.addAttribute("title", "Ajouter un engin");
		model.addAttribute("engin", new Engin());
		return "admin/enginForm";
	}

	@RequestMapping(value = "/addEngin", method = RequestMethod.POST)
	public String addEnginPost(@PathVariable int idC, Model model, @Valid Engin engin, BindingResult bindingResult,
								@RequestParam("file") MultipartFile file){
		if (file == null)
			bindingResult.addError(new ObjectError("file", "Ajouter une image"));
		if (bindingResult.hasErrors()){
			model.addAttribute("title", "Erreur");
			return "admin/enginForm";
		}
		//save file
		//set enngin's photo
		adminService.addEngin(engin, idC);
		return "redirect:/admin/chantier/"+idC;
	}
}
