package me.momarija.bioui.services.impl;

import me.momarija.bioui.domains.Donnee;
import me.momarija.bioui.domains.Engin;
import me.momarija.bioui.repos.ChantierRepo;
import me.momarija.bioui.repos.DonneeRepo;
import me.momarija.bioui.repos.EnginRepo;
import me.momarija.bioui.services.UserService;
import me.momarija.bioui.services.algo.TraitementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ChantierRepo chantierRepo;

	@Autowired
	private EnginRepo enginRepo;

	@Autowired
	private DonneeRepo donneeRepo;

	@Autowired
	private TraitementService traitementService;

	@Override
	public Map<String, Integer> getEnginStatistics(int enginId, Date from, Date to) {
		Engin engin = enginRepo.findOne(enginId);
		return doWork(engin, from, to);
	}

	@Override
	public List<Map<String, Integer>> getChantierStatistics(int chantierId, Date from, Date to) {
		List<Engin> list = chantierRepo.findOne(chantierId).getEngins();
		List<Map<String, Integer>> mapList = new ArrayList<Map<String, Integer>>();
		for(Engin engin : list){
			mapList.add(doWork(engin, from, to));
		}
		return mapList;
	}

	private Map<String, Integer> doWork(Engin engin,Date from, Date to){
		List<Donnee> donneeList = donneeRepo.findBetween(engin.getId(), from, to);
		return traitementService.calcule(donneeList, engin.getTemps(), engin.getIntervale(), engin.getSeuilP(), engin.getSeuilR());
	}
}
