package me.momarija.bioui.service.user;

import me.momarija.bioui.domains.Statistic;

import java.util.List;
import java.util.Map;

public interface UserChantierService {

	List<Map<String, Object>> getChantiersRendement(Statistic statistic);

	List<Map<String, String>> getChantierStatistics(int chantierId, Statistic statistic);

	List<Map<String, String>> getChantierStatisticsDay(int enginId, String day);
}
