package me.momarija.bioui.services.impl;

import me.momarija.bioui.domains.Engin;
import me.momarija.bioui.repos.EnginRepo;
import me.momarija.bioui.services.GerantService;
import org.springframework.beans.factory.annotation.Autowired;

public class GerantServiceImpl implements GerantService {

	@Autowired
	private EnginRepo enginRepo;

	@Override
	public Engin updateEngin(Engin engin) {
		return enginRepo.save(engin);
	}
}
