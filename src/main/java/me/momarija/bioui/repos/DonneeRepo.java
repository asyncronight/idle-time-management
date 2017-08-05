package me.momarija.bioui.repos;

import me.momarija.bioui.domains.Donnee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonneeRepo extends JpaRepository<Donnee, Integer> {
}
