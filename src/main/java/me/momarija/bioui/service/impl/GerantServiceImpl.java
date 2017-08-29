package me.momarija.bioui.service.impl;

import me.momarija.bioui.domains.Chantier;
import me.momarija.bioui.domains.Donnee;
import me.momarija.bioui.domains.Engin;
import me.momarija.bioui.repos.ChantierRepo;
import me.momarija.bioui.repos.DonneeRepo;
import me.momarija.bioui.repos.EnginRepo;
import me.momarija.bioui.service.GerantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(rollbackFor = Exception.class)
public class GerantServiceImpl implements GerantService {

	@Autowired
	private ChantierRepo chantierRepo;

	@Autowired
	private EnginRepo enginRepo;

	@Autowired
	private DonneeRepo donneeRepo;

	@Override
	public void transferEngin(int idEngin, int idChantier) {
		Chantier chantier = chantierRepo.findOne(idChantier);
		if (chantier == null)
			throw new RuntimeException("Transfers échouéee, chantier introuvable");
		Engin engin = enginRepo.findOne(idEngin);
		if (engin == null)
			throw new RuntimeException("Transfers échouéee, engin n'existe pas");
		engin.setChantier(chantier);
		enginRepo.save(engin);
	}

	@Override
	public void addData(float x1, float x2, int id) {
		if (!enginRepo.exists(id))
			throw new RuntimeException("Impossible d'ajouter la donnée, engin (id=" + id + ") n'éxiste pas.");
		Donnee donnee = new Donnee();
		donnee.setDate(new Date());
		donnee.setEnginId(id);
		donnee.setX(x1);
		donnee.setX2(x2);
		donneeRepo.save(donnee);
	}
}
