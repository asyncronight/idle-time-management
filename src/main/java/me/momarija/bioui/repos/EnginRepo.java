package me.momarija.bioui.repos;

import me.momarija.bioui.domains.Engin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnginRepo extends JpaRepository<Engin, Integer> {
}
