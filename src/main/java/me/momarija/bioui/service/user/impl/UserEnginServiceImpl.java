package me.momarija.bioui.service.user.impl;

import me.momarija.bioui.domains.Statistic;
import me.momarija.bioui.repos.DonneeRepo;
import me.momarija.bioui.repos.EnginRepo;
import me.momarija.bioui.service.algo.TraitementService;
import me.momarija.bioui.service.user.UserEnginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserEnginServiceImpl implements UserEnginService {

	@Autowired
	private EnginRepo enginRepo;

	@Autowired
	private DonneeRepo donneeRepo;

	@Autowired
	private TraitementService traitementService;

	@Override
	public List<Map<String, Object>> getEnginsRendement(int chantierId, Statistic statistic) {
		return null;
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
