package me.momarija.bioui.service.admin;

import me.momarija.bioui.domains.Chantier;

import java.util.List;

public interface AdminChantierService {

	List<Chantier> getChantierList();

	Chantier addChantier(Chantier chantier);

	void deleteChantier(int idC, boolean deleteEngins, int idTo);

	Chantier getChantier(int chantierId);
}
