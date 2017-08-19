package me.momarija.bioui.controllers;

import me.momarija.bioui.domains.Chantier;
import me.momarija.bioui.domains.Engin;
import me.momarija.bioui.services.AdminService;
import me.momarija.bioui.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private StorageService storageService;

	@RequestMapping(value = "")
	public String chantierList(Model model){
		model.addAttribute("title", "La liste des chantiers");
		model.addAttribute("chantiers", adminService.getChantierList());
		return "admin/chantierList";
	}

	@RequestMapping(value = "chantier/add", method = RequestMethod.GET)
	public String addChantierGet(Model model){
		model.addAttribute("title" , "Ajouter un chantier");
		model.addAttribute("chantier", new Chantier());
		return "admin/chantierForm";
	}

	@RequestMapping(value = "chantier/add", method = RequestMethod.POST)
	public String addChantierPost(Model model, @Valid Chantier chantier, BindingResult bindingResult){
		if (bindingResult.hasErrors()){
			model.addAttribute("title" , "Erreur");
			return "admin/chantierForm";
		}
		adminService.addChantier(chantier);
		return "redirect:/admin/";
	}

	@RequestMapping(value = "chantier/{id}/delete")
	public String deleteChantier(@PathVariable int id) {
		adminService.deleteChantier(id);
		return "redirect:/admin/";
	}

	@RequestMapping(value = "chantier/{id}")
	public String ennginList(@PathVariable int id, Model model){
		Chantier chantier = adminService.getChantier(id);
		model.addAttribute("title", "Chantier : "+ chantier.getNom());
		model.addAttribute("chantier", chantier);
		return "admin/enginList";
	}

	@RequestMapping(value = "chantier/{id}/add", method = RequestMethod.GET)
	public String addEnginGet(@PathVariable int id, Model model){
		model.addAttribute("title", "ajouter un engin");
		model.addAttribute("engin", new Engin());
		return "admin/enginForm";
	}

	@RequestMapping(value = "chantier/{idC}/add", method = RequestMethod.POST)
	public String addEnginPost(@PathVariable int idC, Model model, @RequestParam("file") MultipartFile file, @Valid Engin engin, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			model.addAttribute("title", "Erreur");
			return "admin/enginForm";
		}
		if (!file.isEmpty()){
			engin.setPhoto(storageService.store(file));
		}else
			engin.setPhoto("photo1.jpg");
		engin.setChantier(adminService.getChantier(idC));
		adminService.addEngin(engin);
		return "redirect:/admin/chantier/"+idC;
	}

	@RequestMapping(value = "chantier/{idC}/engin/{id}/delete")
	public String deleteEngin(@PathVariable int id, @PathVariable int idC){
		adminService.deleteEngin(id);
		return "redirect:/admin/chantier/"+idC;
	}
}
