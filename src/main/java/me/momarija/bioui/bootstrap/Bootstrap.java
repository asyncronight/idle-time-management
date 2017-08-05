package me.momarija.bioui.bootstrap;

import com.github.javafaker.Faker;
import me.momarija.bioui.domains.Chantier;
import me.momarija.bioui.domains.Donnee;
import me.momarija.bioui.domains.Engin;
import me.momarija.bioui.repos.ChantierRepo;
import me.momarija.bioui.repos.DonneeRepo;
import me.momarija.bioui.repos.EnginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private Faker faker;

    @Autowired
    private ChantierRepo chantierRepo;

    @Autowired
    private EnginRepo enginRepo;

    @Autowired
	private DonneeRepo donneeRepo;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    	faker = new Faker();
        loadChantier();
        loadEngins();
        loadDonnee();
        System.out.println("########## Finishing data loading #######");
    }

    private void loadChantier(){
        for (int i =0; i< 1; i++){
            Chantier chantier = new Chantier();
            chantier.setNom("Chantier "+(i+1));
            chantier.setAdresse(faker.address().toString());
            chantierRepo.save(chantier);
        }
    }

    private void loadEngins(){
        for (int i=0; i<1; i++){
            Engin engin = new Engin();
            engin.setSeuilP(1.9f);
            engin.setSeuilR(0.7f);
            engin.setPhoto("photo1.jpg");
            engin.setInterval(6);
            engin.setTemps(10);
            engin.setChantier(chantierRepo.findOne(1));
            enginRepo.save(engin);
        }
    }

    //e : engin number
    // i : donnee number
    private void loadDonnee(){
        Random random = new Random();
		int min = 19;
		for (int e = 0; e<1;e++){
            for (int i = 0; i<100 ;i++){
                Donnee donnee = new Donnee();
                donnee.setEnginId(e+1);
                donnee.setDate("24.04.2017");
                String sec = i%6==0 ?"00" : (i*10)%60+"";
                if(i%6==0)
                	min++;
                donnee.setHeure("14:"+min+":"+sec);
                if(i<30)
                    donnee.setX(random.nextFloat()*(2.5f-1.9f)+1.9f);
                else if (i<35)
                    donnee.setX(random.nextFloat()*(1.9f-0.7f)+0.7f);
                else if (i<55)
                    donnee.setX(random.nextFloat()*(2.5f-1.9f)+1.9f);
                else if (i<75)
                    donnee.setX(random.nextFloat()*0.7f);
                else if (i<85)
                    donnee.setX(random.nextFloat()*(1.9f-0.7f)+0.7f);
                else if (i<95)
                    donnee.setX(random.nextFloat()*0.7f);
                else if (i<98)
                    donnee.setX(random.nextFloat()*(1.9f-0.7f)+0.7f);
                else
                    donnee.setX(random.nextFloat()*0.7f);
                donneeRepo.save(donnee);
            }
        }
    }
}
