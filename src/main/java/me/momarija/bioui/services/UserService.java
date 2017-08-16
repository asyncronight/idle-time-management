package me.momarija.bioui.services;

import me.momarija.bioui.domains.Chantier;
import me.momarija.bioui.domains.Engin;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserService {

	Map<String, Integer> getEnginStatistics(int enginId, Date from, Date to);

	Map<String, Integer> getChantierStatistics(int chantierId, Date from, Date to);

}
