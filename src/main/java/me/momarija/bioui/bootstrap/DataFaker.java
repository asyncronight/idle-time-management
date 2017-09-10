package me.momarija.bioui.bootstrap;

import me.momarija.bioui.domains.Donnee;
import me.momarija.bioui.repos.DonneeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Component
public class DataFaker {

	private DonneeRepo donneeRepo;

	private Random random;

	@Autowired
	public DataFaker(DonneeRepo donneeRepo) {
		this.donneeRepo = donneeRepo;
		this.random = new Random();
	}

	/**
	 * Chaque 10s on vas ajouter une nouvelle donnée
	 * pour les engins du chantiers 1 et 2
	 */
	@Scheduled(fixedRate = 10000)
	public void sendDonnee() {
		for (int engin = 1; engin < 11; engin++) {
			if (engin == 4)
				continue;
			Donnee donnee = new Donnee();
			donnee.setEnginId(engin);
			donnee.setDate(new Date());
			donnee.setX(random.nextFloat() * 2.5f);
			donnee.setX2(random.nextFloat() * 2.5f);
			donneeRepo.save(donnee);
		}
	}
}
