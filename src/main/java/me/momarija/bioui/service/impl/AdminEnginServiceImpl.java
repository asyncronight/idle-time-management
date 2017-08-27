package me.momarija.bioui.service.impl;

import me.momarija.bioui.domains.Chantier;
import me.momarija.bioui.domains.Engin;
import me.momarija.bioui.repos.ChantierRepo;
import me.momarija.bioui.repos.DonneeRepo;
import me.momarija.bioui.repos.EnginRepo;
import me.momarija.bioui.service.AdminEnginService;
import me.momarija.bioui.service.StorageService;
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

	@Autowired
	private StorageService storageService;

	@Autowired
	private DonneeRepo donneeRepo;

	@Override
	public Engin addEngin(Engin engin, int chantierId) {
		Chantier chantier = chantierRepo.findOne(chantierId);
		if (chantier == null){
			throw new RuntimeException("Chantier introuvable, impossible d'ajouter l'engin.");
		}
		engin.setChantier(chantier);
		return enginRepo.save(engin);
	}

	@Override
	public void deleteEngin(int enginId, boolean deleteData) {
		Engin engin = enginRepo.findOne(enginId);
		if (engin == null)
			throw new RuntimeException("Suppression échouée, engins introuvable");
		enginRepo.delete(engin);
		storageService.deleteFile(engin.getPhoto());
		if (deleteData)
			donneeRepo.deleteByEnginId(enginId);
	}
}
