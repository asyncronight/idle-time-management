package me.momarija.bioui.services;

import me.momarija.bioui.domains.Engin;

public interface AdminEnginService {

	Engin addEngin(Engin engin, int chantierId);

	void deleteEngin(int enginId);
}
