package me.momarija.bioui.service.impl;

import me.momarija.bioui.domains.Chantier;
import me.momarija.bioui.domains.Donnee;
import me.momarija.bioui.domains.Engin;
import me.momarija.bioui.domains.Statistic;
import me.momarija.bioui.repos.ChantierRepo;
import me.momarija.bioui.repos.DonneeRepo;
import me.momarija.bioui.repos.EnginRepo;
import me.momarija.bioui.service.UserService;
import me.momarija.bioui.service.algo.DateUtility;
import me.momarija.bioui.service.algo.TraitementService;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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



	public List<Engin> getEnginList(int chantierId) {
		return chantierRepo.findOne(chantierId).getEngins();
	}

	private Map<String, Integer> doWork(Engin engin,Date from, Date to){
		List<Donnee> donneeList = donneeRepo.findBetween(engin.getId(), from, to);
		return traitementService.calcule(donneeList, engin.getTemps(), engin.getSeuilP(), engin.getSeuilR());
	}

	@Override
	public List<Map<String, Object>> getChantiersRendement(Statistic statistic) {
		List<Map<String,Object>> list = new ArrayList<>();

		Map<String,Object> mapChantierRendement = new HashMap<>();

		for (Chantier c:chantierRepo.findAll()) {
			mapChantierRendement.put("chantier",c);
			mapChantierRendement.put("rendement",getChantierRendement(c.getId(),statistic));
			list.add(mapChantierRendement);
			mapChantierRendement=new HashMap<>();
		}

		return list;
	}

	@Override
	public List<Map<String, String>> getChantierStatistics(int chantierId, Statistic statistic) {








		List<Map<String,String>> list = new ArrayList<>();
		Map<String,String> map = new HashMap<>();int i=0;
		int p=0,r=0,a=0;float rendement=0.2f;
		List<Map<String,Object>> l = new ArrayList<>();

		for (Engin e:getEnginList(chantierId)) {

			l = getEnginStatistique(e.getId(),statistic);
			p+=(Integer)l.get(i).get("production");
			r+=(Integer)l.get(i).get("ralenti");
			a+=(Integer)l.get(i).get("arret");
			rendement+=(Double)l.get(i).get("rendement");

		}
		map.put("production",dateUtility.convertToDate(p));
		map.put("ralenti",dateUtility.convertToDate(r));
		map.put("arret",dateUtility.convertToDate(a));
		map.put("date",l.get(i).get("dayFrom")+"");
		map.put("pause",statistic.getNbHourRepos()+ "h");
		map.put("rendement","");
		return list;
	}

	@Override
	public List<Map<String, Object>> getEnginsRendement(int chantierId, Statistic statistic) {
		List<Map<String,Object>> list = new ArrayList<>();
		Map<String,Object> mapEnginRendement = new HashMap<>();

		for (Engin e:getEnginList(chantierId)) {
			mapEnginRendement.put("engin",e);
			mapEnginRendement.put("rendement",getChantierRendement(e.getId(),statistic));
			list.add(mapEnginRendement);
			mapEnginRendement=new HashMap<>();
		}
		return list;
	}

	@Override
	public List<Map<String, String>> getEnginStatistics(int enginId, Statistic statistic) {
		List<Map<String,String>> list = new ArrayList<>();
		Map<String,String> map = new HashMap<>();
		List<Map<String,Object>> l = getEnginStatistique(enginId,statistic);

		map.put("production",dateUtility.convertToDate((Integer)(l.get(0).get("production") )));
		map.put("ralenti",dateUtility.convertToDate((Integer)(l.get(0).get("ralenti") )));
		map.put("arret",dateUtility.convertToDate((Integer)(l.get(0).get("arret") )));
		map.put("date",l.get(0).get("date").toString());
		map.put("pause",l.get(0).get("pause").toString()+" h");
		map.put("rendement",String.format("%.2f", l.get(0).get("rendement"))+" %");

		list.add(map);
		return list;
	}
	public List<Map<String, Object>> getEnginStatistique(int enginId, Statistic statistic) {
		List<Map<String,Object>> list = new ArrayList<>();
		int nbJours = statistic.calculNbJours();
		System.out.println(nbJours);
		int i;Date d1,d2,d3 ;
		long y;
		Engin engin = enginRepo.findOne(enginId);


		SimpleDateFormat stf = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat stimef = new SimpleDateFormat("HH:mm");

		String dayFrom =stf.format(statistic.getDayFrom());
		String hourFrom = stimef.format(statistic.getHourFrom());
		String hourTo = stimef.format(statistic.getHourTo());

		Map<String ,Integer> map;
		Map<String ,Object> map2 ;
		double p_percent;long z;

		for(i=0;i<=nbJours;i++){

			System.out.println("From : "+dayFrom+" "+hourFrom);

			d1= new Date(dayFrom+" "+hourFrom);
			d3= new Date(dayFrom+" "+hourTo);
			y = d1.getTime()+24*3600*1000;
			d2 = new Date(y);
			z = d3.getTime()-d1.getTime()-statistic.getNbHourRepos()*3600*1000;
			map2 = new HashMap<>();
			map = doWork(engin,d1,d3);
			int arret = (int)z/1000 - map.get("production") - map.get("ralenti");

			if(arret < 0){
				map.put("ralenti",map.get("ralenti")+arret);
				arret = 0 ;
			}
			if(map.get("ralenti")<0) map.put("ralenti",0);

			p_percent =(double) (map.get("production") * 100 / (z/1000) ) ;


			map2.put("production",map.get("production"));
			map2.put("ralenti",map.get("ralenti"));
			map2.put("arret", arret);
			map2.put("date", dayFrom);
			map2.put("pause",statistic.getNbHourRepos());
			map2.put("rendement",p_percent);
			list.add(map2);

			dayFrom = stf.format(d2);

		}
		return list;
	}



	public float getChantierRendement(int chantierId, Statistic statistic){
		float rendement = 0.2f;
		List<Engin> listEngins = getEnginList(chantierId);
		for (Engin e:listEngins) {
			rendement +=getEnginRendement(e.getId(),statistic);
		}
		rendement = rendement/listEngins.size();
		return rendement;
	}


	public float getEnginRendement(int enginId,Statistic statistic){
		float rendement = 0.2f;
		int i;Date d1,d2,d3 ;
		long y,z;
		int nbJours = statistic.calculNbJours();
		SimpleDateFormat stf = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat stimef = new SimpleDateFormat("HH:mm");
		Engin engin = enginRepo.findOne(enginId);
		String dayFrom =stf.format(statistic.getDayFrom());
		String hourFrom = stimef.format(statistic.getHourFrom());
		String hourTo = stimef.format(statistic.getHourTo());
		Map<String ,Integer> map ;

		for(i=0;i<=nbJours;i++){
			d1= new Date(dayFrom+" "+hourFrom);
			d3= new Date(dayFrom+" "+hourTo);
			y = d1.getTime()+24*3600*1000;
			d2 = new Date(y);

			//getting map from Algorithme (TraitementService)

			map = doWork(engin,d1,d3);
			z = d3.getTime()-d1.getTime()-statistic.getNbHourRepos()*3600*1000;
			int arret = (int)z/1000 - map.get("production") - map.get("ralenti");

			if(arret < 0){
				map.put("ralenti",map.get("ralenti")+arret);
				arret = 0 ;
			}
			if(map.get("ralenti")<0) map.put("ralenti",0);
			rendement =(float) (map.get("production") * 100 / (z/1000) ) ;
		}
		return rendement;
	}

}
