package me.momarija.bioui.controllers;

import me.momarija.bioui.domains.Donnee;
import me.momarija.bioui.repos.DonneeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class IndexController {

	@Autowired
	private DonneeRepo donneeRepo;

	@RequestMapping(value = "/")
	@ResponseBody
	public List<Donnee> index(){
		return donneeRepo.findAll();
	}
}
