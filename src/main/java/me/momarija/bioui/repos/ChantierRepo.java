package me.momarija.bioui.repos;

import me.momarija.bioui.domains.Chantier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChantierRepo extends JpaRepository<Chantier, Integer> {
}
