package me.momarija.bioui.service.user.impl;

import me.momarija.bioui.domains.Donnee;
import me.momarija.bioui.domains.Engin;
import me.momarija.bioui.domains.Statistic;
import me.momarija.bioui.repos.CalculRepo;
import me.momarija.bioui.repos.ChantierRepo;
import me.momarija.bioui.repos.DonneeRepo;
import me.momarija.bioui.repos.EnginRepo;
import me.momarija.bioui.service.algo.DateUtility;
import me.momarija.bioui.service.algo.TraitementService;
import me.momarija.bioui.service.user.UserEnginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserEnginServiceImpl implements UserEnginService {

	@Autowired
	private EnginRepo enginRepo;

	@Autowired
	private ChantierRepo chantierRepo;

	@Autowired
	private DateUtility dateUtility;

	@Autowired
	private DonneeRepo donneeRepo;

	@Autowired
	private CalculRepo calculRepo;

	@Autowired
	private TraitementService traitementService;

	@Override
	public List<Map<String, Object>> getEnginsRendement(int chantierId, Statistic statistic) {
		Map<String, Integer> map;
		Map<String, Object> mapList = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		List<Integer> listIdEngins = new ArrayList<>();
		chantierRepo.findOne(chantierId).getEngins().forEach(engin -> listIdEngins.add(engin.getId()));
		List<Donnee> listDonnee = calculRepo.findByEnginIds(listIdEngins, statistic.getDayFrom(), statistic.getDayTo());
		for (Engin engin : chantierRepo.findOne(chantierId).getEngins()) {
			map = traitementService.calcule(listDonnee.stream().filter(donnee -> donnee.getEnginId() == engin.getId()).collect(Collectors.toList()), engin.getTemps(), engin.getSeuilP(), engin.getSeuilR());
			float rendement = map.get("production") / (statistic.calculNbJour() * engin.getNbHeureRentabilite() * 36);
			mapList.put("engin", engin);
			mapList.put("rendement", rendement);
			list.add(mapList);
			mapList = new HashMap<>();
		}
		return list;
	}

	@Override
	public List<Map<String, String>> getEnginStatistic(int enginId, Statistic statistic) {
		return null;
	}

	@Override
	public List<Map<String, String>> getEnginStatisticsDay(int enginId, String day) {
		return null;
	}
}
