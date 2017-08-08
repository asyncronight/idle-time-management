package me.momarija.bioui.controllers;

import me.momarija.bioui.domains.Donnee;
import me.momarija.bioui.repos.DonneeRepo;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
public class IndexController {

	@Autowired
	private DonneeRepo donneeRepo;

	/**
	 * Returne les donnees + ou - une minute dés maintenant
	 *
	 */
	@RequestMapping(value = "/")
	@ResponseBody
	public List<Donnee> index(){
		Date date = new Date();
		return donneeRepo.findBetween(1, DateUtils.setMinutes(date,
				date.getMinutes()-2),DateUtils.setMinutes(date,date.getMinutes()+2));
	}
}
