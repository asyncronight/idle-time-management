package me.momarija.bioui.algo;

import me.momarija.bioui.repos.DonneeRepo;
import me.momarija.bioui.services.algo.TraitementService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TraitementServiceTest {

	@Autowired
	private DonneeRepo donneeRepo;

	@Test
	@Ignore
	public void test(){
		TraitementService serviceTest = new TraitementService();
		Map<String,Integer> map = serviceTest.calcule(donneeRepo.findAll(),10,6,1.9f,0.7f);
		assertEquals(map.size() , 3);
		assertEquals( map.get("production"),new Integer(580));
		assertEquals( map.get("ralenti"),new Integer(100));
		assertEquals( map.get("arret"),new Integer(320));
	}
}
