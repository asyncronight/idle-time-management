package me.momarija.bioui.services.impl;

import me.momarija.bioui.domains.Donnee;
import me.momarija.bioui.domains.Engin;
import me.momarija.bioui.domains.Statistic;
import me.momarija.bioui.repos.ChantierRepo;
import me.momarija.bioui.repos.DonneeRepo;
import me.momarija.bioui.repos.EnginRepo;
import me.momarija.bioui.services.UserService;
import me.momarija.bioui.services.algo.DateUtility;
import me.momarija.bioui.services.algo.TraitementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ChantierRepo chantierRepo;

	@Autowired
	private EnginRepo enginRepo;

	@Autowired
	private DonneeRepo donneeRepo;

	@Autowired
	private TraitementService traitementService;

	private DateUtility dateUtility = new DateUtility();

	@Override
	public Map<String, String> getEnginStatistics(int enginId, Date from, Date to) {
		Engin engin = enginRepo.findOne(enginId);
		Map<String, Integer> map = doWork(engin, from, to);
		Map<String, String> map2 = new HashMap<>();
		double p = 0.0,r=0.0,a=0.0;

		long fullTime  = dateUtility.convertToTime(from,to);
		long arret  = fullTime/1000  - map.get("production") - map.get("ralenti");

		p =(double) map.get("production") * 100 / (fullTime/1000) ;
		r= (double) map.get("ralenti") * 100 / (fullTime/1000);
		a= (double) arret * 100 / (fullTime/1000) ;

		//because the time ( arret ) cannot be saved when the <engin> is not in travel


		map2.put("production", dateUtility.convertToDate(map.get("production")));
		map2.put("ralenti", dateUtility.convertToDate(map.get("ralenti")));
		map2.put("arret", dateUtility.convertToDate((int)arret));
		map2.put("productionPercent",p+"");
		map2.put("ralentiPercent",r+"");
		map2.put("arretPercent",a+"");
		map2.put("rendement",p+"");

		return map2;
	}

	@Override
	public Map<String, String> getChantierStatistics(int chantierId, Date from, Date to) {
		List<Engin> list = chantierRepo.findOne(chantierId).getEngins();
		int p=0,r=0,a=0;
		Map<String, Integer> mapEngin= new HashMap<>();
		Map<String, Integer> map;
        double p_percent = 0.0,r_percent=0.0,a_percent=0.0;
        int arret ;

        for(Engin engin:list){
			map = doWork(engin,from,to);
			p = p+ map.get("production");
			r = r+ map.get("ralenti");
		}

        long fullTime  = dateUtility.convertToTime(from,to);
        arret = (int)(fullTime/1000 - p - r);

		mapEngin.put("production",p);
		mapEngin.put("ralenti",r);
		mapEngin.put("arret",arret);

        p_percent =(double) mapEngin.get("production") * 100 / (fullTime/1000) ;
        r_percent= (double) mapEngin.get("ralenti") * 100 / (fullTime/1000);
        a_percent= (double) mapEngin.get("arret") * 100 / (fullTime/1000) ;


        Map<String, String> map2 = new HashMap<>();
		map2.put("production", dateUtility.convertToDate(mapEngin.get("production")));
		map2.put("ralenti", dateUtility.convertToDate(mapEngin.get("ralenti")));
		map2.put("arret", dateUtility.convertToDate(mapEngin.get("arret")));
        map2.put("productionPercent",p_percent+"");
        map2.put("ralentiPercent",r_percent+"");
        map2.put("arretPercent",a_percent+"");
        map2.put("rendement",p_percent+"");

        return map2;
	}

	private Map<String, Integer> doWork(Engin engin,Date from, Date to){
		List<Donnee> donneeList = donneeRepo.findBetween(engin.getId(), from, to);
		return traitementService.calcule(donneeList, engin.getTemps(), engin.getIntervale(), engin.getSeuilP(), engin.getSeuilR());
	}

	@Override
	public Map<String, String> getChantierStatisticsWeek(int chantierId) {
		Statistic s = new Statistic();
		s.setDateFrom(new Date(System.currentTimeMillis() - 604800*1000));
		s.setDateTo(new Date());
		return getChantierStatistics(chantierId,s.getDateFrom(),s.getDateTo());
	}

	@Override
	public Map<String, String> getEnginStatisticsWeek(int enginId) {
		Statistic s = new Statistic();
		s.setDateFrom(new Date(System.currentTimeMillis() - 604800*1000));
		s.setDateTo(new Date());
		return getEnginStatistics(enginId,s.getDateFrom(),s.getDateTo());
	}

}
