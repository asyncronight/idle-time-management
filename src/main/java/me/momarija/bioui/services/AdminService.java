package me.momarija.bioui.services;

import me.momarija.bioui.domains.Chantier;
import me.momarija.bioui.domains.Engin;

import java.util.List;

public interface AdminService {

	Engin addEngin(Engin engin);

	void deleteEngin(int id);

	Chantier addChantier(Chantier chantier);

	void deleteChantier(int id);

	List<Chantier> getChantierList();

	List<Engin> getEnginList(int chantierId);
}
