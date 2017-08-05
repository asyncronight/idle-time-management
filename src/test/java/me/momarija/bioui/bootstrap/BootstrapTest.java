package me.momarija.bioui.bootstrap;

import me.momarija.bioui.domains.User;
import me.momarija.bioui.repos.ChantierRepo;
import me.momarija.bioui.repos.DonneeRepo;
import me.momarija.bioui.repos.EnginRepo;
import me.momarija.bioui.repos.UserRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootstrapTest {

	@Autowired
	private ChantierRepo chantierRepo;

	@Autowired
	private EnginRepo enginRepo;

	@Autowired
	private DonneeRepo donneeRepo;

	@Autowired
	private UserRepo userRepo;

	@Test
	public void dataInsertTest(){
		assertEquals(2,chantierRepo.findAll().size());
		assertEquals(10,enginRepo.findAll().size());
		assertEquals(1000,donneeRepo.findAll().size());
		assertEquals(3,userRepo.findAll().size());

		assertEquals("Admin",userRepo.findOne(1).getRoles().get(0).getRole());
	}
}
