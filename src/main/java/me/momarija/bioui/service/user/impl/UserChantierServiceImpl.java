package me.momarija.bioui.service.user.impl;

import me.momarija.bioui.domains.Statistic;
import me.momarija.bioui.repos.ChantierRepo;
import me.momarija.bioui.repos.DonneeRepo;
import me.momarija.bioui.service.algo.TraitementService;
import me.momarija.bioui.service.user.UserChantierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserChantierServiceImpl implements UserChantierService {

	@Autowired
	private ChantierRepo chantierRepo;

	@Autowired
	private DonneeRepo donneeRepo;

	@Autowired
	private TraitementService traitementService;

	@Override
	public List<Map<String, Object>> getChantiersRendement(Statistic statistic) {
		return null;
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
