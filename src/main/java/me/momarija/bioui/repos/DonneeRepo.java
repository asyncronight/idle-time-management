package me.momarija.bioui.repos;

import me.momarija.bioui.domains.Donnee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DonneeRepo extends JpaRepository<Donnee, Long> {

	@Query("from Donnee where enginId=?1 and date between ?2 and ?3")
	List<Donnee> findBetween(int id, Date from, Date to);

	@Query("from Donnee where enginId in ?1 and date between ?2 and ?3 order by enginId,date")
	List<Donnee> findByEnginIds(List<Integer> ids, Date from, Date to);

	void deleteByEnginId(int enginId);
}
