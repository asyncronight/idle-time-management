package me.momarija.bioui.repos;

import me.momarija.bioui.domains.Donnee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonneeRepo extends JpaRepository<Donnee, Integer> {

	List<Donnee> findByEnginId(int id);
}
