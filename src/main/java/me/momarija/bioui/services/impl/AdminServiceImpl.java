package me.momarija.bioui.services.impl;

import me.momarija.bioui.domains.Chantier;
import me.momarija.bioui.domains.Engin;
import me.momarija.bioui.repos.ChantierRepo;
import me.momarija.bioui.repos.EnginRepo;
import me.momarija.bioui.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl implements AdminService {

	@Autowired
	private ChantierRepo chantierRepo;

	@Autowired
	private EnginRepo enginRepo;

	@Override
	public List<Chantier> getChantierList() {
		List<Chantier> list= chantierRepo.findAll();
		if (list == null)
			list = new ArrayList<Chantier>();
		return list;
	}

	@Override
	public Chantier addChantier(Chantier chantier) {
		return chantierRepo.save(chantier);
	}

	@Override
	public void deleteChantier(int idC, boolean deleteEngins, int idTo) {
		if (deleteEngins) {
			try {
				chantierRepo.delete(idC);
				return;
			}catch (Exception ex){
				throw new RuntimeException("Chantier introuvable, suppression échouée.");
			}
		}else {
			Chantier chantier = chantierRepo.findOne(idC);
			if (chantier == null)
				throw new RuntimeException("Chantier introuvable, suppression échouée.");
			Chantier chantierTo = chantierRepo.findOne(idTo);
			if (chantierTo == null)
				throw new RuntimeException("Impossible de transferer les engins, chantier introuvable.");
			chantier.getEngins().forEach(engin -> {
				engin.setChantier(chantierTo);
				enginRepo.save(engin);
			});
			chantier.setEngins(null);
			chantierRepo.delete(chantier);
		}
	}

	@Override
	public Chantier getChantier(int chantierId) {
		Chantier chantier = chantierRepo.findOne(chantierId);
		if (chantier == null)
			throw new RuntimeException("Chantier introuvable, récuperation des informations échouée.");
//		if (chantier.getEngins() == null)
//			chantier.setEngins(new ArrayList<Engin>());
		return chantier;
	}

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
}
