package me.momarija.bioui.services.algo;

import me.momarija.bioui.domains.Donnee;
import me.momarija.bioui.repos.DonneeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class DonneeFaker {

	private DonneeRepo donneeRepo;

	private Random random;

	@Autowired
	public DonneeFaker(DonneeRepo donneeRepo){
		this.donneeRepo=donneeRepo;
		random = new Random();
	}

	/**
	 * Chaque 10s on vas ajouter une nouvelle donnée
	 * Seulement des donnees pour l'engin 1
	 *
	 */
	@Scheduled(fixedRate = 10000)
	@Transactional
	public void sendDonnee(){
		System.out.println();
		Donnee donnee= new Donnee();
		donnee.setEnginId(1);
		donnee.setDate(new Date());
		donnee.setX(random.nextFloat()*(2.5f-1.9f)+1.9f);
		donnee.setX2(random.nextFloat()*(2.5f-1.9f)+1.9f);
		donneeRepo.save(donnee);
		System.out.println("############ New record - id : "+donnee.getId()+" ##########");
	}
}
