package me.momarija.bioui.services.impl;

import me.momarija.bioui.domains.Donnee;
import me.momarija.bioui.domains.Engin;
import me.momarija.bioui.repos.DonneeRepo;
import me.momarija.bioui.repos.EnginRepo;
import me.momarija.bioui.services.GerantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GerantServiceImpl implements GerantService {

	@Autowired
	private EnginRepo enginRepo;

	@Autowired
	private DonneeRepo donneeRepo;

	@Override
	public Engin updateEngin(Engin engin) {
		return enginRepo.save(engin);
	}

	@Override
	public Donnee addDonne(float d1, float d2, int id) {
		Donnee donnee = new Donnee();
		donnee.setX(d1);
		donnee.setX2(d2);
		donnee.setEnginId(id);
		donnee.setDate(new Date());
		return donneeRepo.save(donnee);
	}
}
