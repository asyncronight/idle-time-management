package me.momarija.bioui.service;

import me.momarija.bioui.domains.Engin;

public interface AdminEnginService {

	Engin addEngin(Engin engin, int chantierId);

	void deleteEngin(int enginId, boolean deleteData);

	Engin getEngin(int enginId);

	Engin updateEngin(Engin engin);
}
