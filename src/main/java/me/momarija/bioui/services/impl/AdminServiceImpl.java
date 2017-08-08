package me.momarija.bioui.services.impl;

import me.momarija.bioui.domains.Chantier;
import me.momarija.bioui.domains.Engin;
import me.momarija.bioui.repos.ChantierRepo;
import me.momarija.bioui.repos.EnginRepo;
import me.momarija.bioui.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private EnginRepo enginRepo;

	@Autowired
	private ChantierRepo chantierRepo;

	@Override
	public Engin addEngin(Engin engin) {
		return enginRepo.save(engin);
	}

	@Override
	public void deleteEngin(int id) {
		enginRepo.delete(id);
	}

	@Override
	public Chantier addChantier(Chantier chantier) {
		return chantierRepo.save(chantier);
	}

	@Override
	public void deleteChantier(int id) {
		chantierRepo.delete(id);
	}

	@Override
	public List<Chantier> getChantierList() {
		return chantierRepo.findAll();
	}

	@Override
	public List<Engin> getEnginList(int chantierId) {
		return chantierRepo.findOne(chantierId).getEngins();
	}
}
