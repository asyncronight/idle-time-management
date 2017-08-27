package me.momarija.bioui.services.impl;

import me.momarija.bioui.domains.Chantier;
import me.momarija.bioui.domains.Engin;
import me.momarija.bioui.repos.ChantierRepo;
import me.momarija.bioui.repos.EnginRepo;
import me.momarija.bioui.services.AdminEnginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class AdminEnginServiceImpl implements AdminEnginService {

	@Autowired
	private ChantierRepo chantierRepo;

	@Autowired
	private EnginRepo enginRepo;

	@Override
	public Engin addEngin(Engin engin, int chantierId) {
		Chantier chantier = chantierRepo.findOne(chantierId);
		if (chantier == null){
			//delete engin's saved photo
			throw new RuntimeException("Chantier introuvable, impossible d'ajouter l'engin.");
		}
		engin.setChantier(chantier);
		return enginRepo.save(engin);
	}

	@Override
	public void deleteEngin(int enginId) {
		try {
			enginRepo.delete(enginId);
		}catch (Exception ex){
			throw new RuntimeException("Suppression échouée, engins introuvable");
		}
	}
}
