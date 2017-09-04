package me.momarija.bioui.service;

import me.momarija.bioui.domains.Chantier;
import me.momarija.bioui.domains.Engin;
import me.momarija.bioui.domains.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService{

    List<Map<String, Object>> getChantiersRendementWeek();

    List<Map<String,Object>> getChantiersRendement(Statistic statistic);

    List<Map<String,String>> getChantierStatistics(int chantierId,Statistic statistic);

    List<Map<String,String>> getChantierStatisticsWeek(int chantierId);

    List<Map<String,Object>> getEnginsRendement(int chantierId,Statistic statistic);

    List<Map<String, Object>> getEnginsRendementWeek(int chantierId);

    List<Map<String,String>> getEnginStatistic(int enginId,Statistic statistic);

    List<Map<String,String>> getEnginStatisticsWeek(int enginId);

}
