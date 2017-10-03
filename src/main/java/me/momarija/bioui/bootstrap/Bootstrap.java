package me.momarija.bioui.bootstrap;

import com.github.javafaker.Faker;
import me.momarija.bioui.domains.*;
import me.momarija.bioui.repos.*;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
	private UserRepo userRepo;

    @Autowired
	private RoleRepo roleRepo;

    @Autowired
    private CalculRepo calculRepo;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    	if(userRepo.findAll().isEmpty()) {
			faker = new Faker();
			loadChantier();
			loadEngins();
			loadDonnee();
			loadUsers();
            loadCalcul();
            System.out.println("########## Finishing data loading #######");
		}
    }

    private void loadChantier(){
        for (int i =0; i< 2; i++){
            Chantier chantier = new Chantier();
            chantier.setNom("Chantier "+(i+1));
            chantier.setAdresse(faker.address().fullAddress());
            chantierRepo.save(chantier);
        }
    }

    private void loadEngins(){
    	Random random = new Random();
        for (int i=0; i<10; i++){
            Engin engin = new Engin();
            engin.setMarque(faker.company().name());
            engin.setChauffeur(faker.name().fullName());
            engin.setAnnee("2017");
            engin.setSeuilP(1.9f+(random.nextFloat()*0.6f-0.3f));
            engin.setSeuilR(0.7f+(random.nextFloat()*0.8f-0.4f));
            engin.setPhoto("photo1.jpg");
            engin.setTemps(10);
            engin.setNbHeureRentabilite(11);
            engin.setChantier(chantierRepo.findOne(i%2+1));
            enginRepo.save(engin);
        }
    }

    //e : engin number
    // i : donnee number
    private void loadDonnee(){
    	Date date;
        Random random = new Random();
		for (int e = 0; e<10;e++){
			date = new Date();
            for (int i = 0; i<100 ;i++){
                Donnee donnee = new Donnee();
                donnee.setEnginId(e+1);
                donnee.setDate(date = DateUtils.addSeconds(date, 10));

                if(i<30+random.nextInt(10)){
                	donnee.setX(random.nextFloat()*(2.5f-1.9f)+1.9f);
                	donnee.setX2(random.nextFloat()*(2.5f-1.9f)+1.9f);
				}
                else if (i<35+random.nextInt(10)){
                    donnee.setX(random.nextFloat()*(1.9f-0.7f)+0.7f);
                    donnee.setX2(random.nextFloat()*(1.9f-0.7f)+0.7f);
				}
                else if (i<55+random.nextInt(10)){
                    donnee.setX(random.nextFloat()*(2.5f-1.9f)+1.9f);
                    donnee.setX2(random.nextFloat()*(2.5f-1.9f)+1.9f);
				}
                else if (i<75+random.nextInt(10)){
                    donnee.setX(random.nextFloat()*0.7f);
                    donnee.setX2(random.nextFloat()*0.7f);
				}
                else if (i<85+random.nextInt(10)){
                    donnee.setX(random.nextFloat()*(1.9f-0.7f)+0.7f);
                    donnee.setX2(random.nextFloat()*(1.9f-0.7f)+0.7f);
				}
                else if (i<95+random.nextInt(5)){
                    donnee.setX(random.nextFloat()*0.7f);
                    donnee.setX2(random.nextFloat()*0.7f);
				}
                else if (i<98+random.nextInt(3)){
                    donnee.setX(random.nextFloat()*(1.9f-0.7f)+0.7f);
                    donnee.setX2(random.nextFloat()*(1.9f-0.7f)+0.7f);
				}
                else{
                    donnee.setX(random.nextFloat()*0.7f);
				}
                donneeRepo.save(donnee);
            }
        }
    }

    private void loadUsers(){
		Role role1 = new Role();
		role1.setRole("Admin");
		roleRepo.save(role1);
		Role role2 = new Role();
		role2.setRole("Gerant");
		roleRepo.save(role2);
		Role role3 = new Role();
		role3.setRole("User");
		roleRepo.save(role3);

		User user1 = new User();
		user1.setUsername("user1");
		user1.setPassword("user1");
		user1.setRoles(new ArrayList<Role>() {
			{
				add(role1);
			}
		});
		userRepo.save(user1);
		User user2 = new User();
		user2.setUsername("user2");
		user2.setPassword("user2");
		user2.setRoles(new ArrayList<Role>() {
			{
				add(role2);
			}
		});
		userRepo.save(user2);
		User user3 = new User();
		user3.setUsername("user3");
		user3.setPassword("user3");
		user3.setRoles(new ArrayList<Role>() {
			{
				add(role3);
			}
		});
		userRepo.save(user3);
    }

    private void loadCalcul() {

        Random random = new Random();
        Date date;
        for (int e = 0; e < 10; e++) {
            date = new Date();
            for (int i = 0; i < 10; i++) {
                Calcul calcul = new Calcul();
                calcul.setEnginId(e + 1);
                calcul.setDate(date);
                calcul.setTempsP(1000);
                calcul.setTempsR(500);
                calculRepo.save(calcul);
            }
        }

    }
}
