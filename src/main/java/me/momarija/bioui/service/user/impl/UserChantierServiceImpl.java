package me.momarija.bioui.service.user.impl;

import me.momarija.bioui.domains.Chantier;
import me.momarija.bioui.domains.Statistic;
import me.momarija.bioui.repos.ChantierRepo;
import me.momarija.bioui.repos.DonneeRepo;
import me.momarija.bioui.service.algo.TraitementService;
import me.momarija.bioui.service.user.UserChantierService;
import me.momarija.bioui.service.user.UserEnginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserChantierServiceImpl implements UserChantierService {

	@Autowired
	private UserEnginService userEnginService;

	@Autowired
	private ChantierRepo chantierRepo;

	@Autowired
	private DonneeRepo donneeRepo;

	@Autowired
	private TraitementService traitementService;

	@Override
	public List<Map<String, Object>> getChantiersRendement(Statistic statistic) {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> list, lista = new ArrayList<>();
		for (Chantier c : chantierRepo.findAll()) {
			float rendement = 0.02f;
			list = userEnginService.getEnginsRendement(c.getId(), statistic);
			for (Map<String, Object> map2 : list) {
				rendement += Float.parseFloat(map2.get("rendement").toString());
			}
			rendement /= list.size();
			map.put("chantier", c);
			map.put("rendement", String.format("%.2f", rendement));
			lista.add(map);
		}
		return lista;
	}

	@Override
	public List<Map<String, String>> getChantierStatistics(int chantierId, Statistic statistic) {
		return null;
	}

	@Override
	public List<Map<String, String>> getChantierStatisticsDay(int enginId, String day) {
		return null;
	}
}
