package me.momarija.bioui.services;

import me.momarija.bioui.domains.Donnee;
import me.momarija.bioui.domains.Engin;

public interface GerantService {

	Engin updateEngin(Engin engin);

	Donnee addDonne(float d1, float d2, int id);
}
