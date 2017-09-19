package me.momarija.bioui.service.user;

import me.momarija.bioui.domains.Statistic;

import java.util.List;
import java.util.Map;

public interface UserEnginService {

	List<Map<String, Object>> getEnginsRendement(int chantierId, Statistic statistic);

	List<Map<String, String>> getEnginStatistic(int enginId, Statistic statistic);

	List<Map<String, String>> getEnginStatisticsDay(int enginId, String day);

}
