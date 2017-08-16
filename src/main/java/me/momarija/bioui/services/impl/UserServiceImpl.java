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

import java.util.*;

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
	public Map<String, Integer> getChantierStatistics(int chantierId, Date from, Date to) {
		List<Engin> list = chantierRepo.findOne(chantierId).getEngins();
		int p=0,r=0,a=0;
		Map<String, Integer> mapEngin= new HashMap<>();
		Map<String, Integer> map;
		for(Engin engin:list){
			map = doWork(engin,from,to);
			p = p+ map.get("production");
			r = r+ map.get("ralenti");
			a = a+ map.get("arret");
		}
		mapEngin.put("production",p/list.size());
		mapEngin.put("ralenti",r/list.size());
		mapEngin.put("arret",a/list.size());
		return mapEngin;
	}

	private Map<String, Integer> doWork(Engin engin,Date from, Date to){
		List<Donnee> donneeList = donneeRepo.findBetween(engin.getId(), from, to);
		return traitementService.calcule(donneeList, engin.getTemps(), engin.getIntervale(), engin.getSeuilP(), engin.getSeuilR());
	}
}
