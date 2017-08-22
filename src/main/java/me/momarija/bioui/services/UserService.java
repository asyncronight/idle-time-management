package me.momarija.bioui.services;

import me.momarija.bioui.domains.Chantier;
import me.momarija.bioui.domains.Engin;
import me.momarija.bioui.domains.Statistic;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserService {

	Map<String, String> getEnginStatistics(int enginId, Date from, Date to);

	Map<String, String> getEnginStatisticsWeek(int enginId);

	Map<String, String> getChantierStatistics(int chantierId, Date from, Date to);

	Map<String, String> getChantierStatisticsWeek(int chantierId);

	List<Map<String, String>> getEnginStatistic(int enginId, Statistic statistic);

}
