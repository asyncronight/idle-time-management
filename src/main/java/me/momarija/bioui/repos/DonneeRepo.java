package me.momarija.bioui.repos;

import me.momarija.bioui.domains.Donnee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DonneeRepo extends JpaRepository<Donnee, Integer> {

	List<Donnee> findByEnginId(int id);

	@Query("from Donnee where enginId=?1 and date between ?2 and ?3")
	List<Donnee> findBetween(int id, Date from, Date to);
}
