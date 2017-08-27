package me.momarija.bioui.services;

import me.momarija.bioui.domains.Chantier;
import me.momarija.bioui.domains.Engin;

import java.util.List;

public interface AdminService {

	List<Chantier> getChantierList();

	Chantier addChantier(Chantier chantier);

	void deleteChantier(int idC, boolean deleteEngins, int idTo);

	Chantier getChantier(int chantierId);

	Engin addEngin(Engin engin, int chantierId);
}
