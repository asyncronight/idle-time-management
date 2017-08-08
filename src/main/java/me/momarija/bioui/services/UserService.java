package me.momarija.bioui.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserService {

	Map<String, Integer> getEnginStatistics(int enginId, Date from, Date to);

	List<Map<String, Integer>> getChantierStatistics(int chantierId, Date from, Date to);
}
