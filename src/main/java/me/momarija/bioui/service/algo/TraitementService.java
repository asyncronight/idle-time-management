package me.momarija.bioui.service.algo;

import me.momarija.bioui.domains.Calcul;
import me.momarija.bioui.domains.Donnee;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TraitementService {

    public Map<String, Integer> calcule(List<Donnee> l, int temps, float seuilP, float seuilR) {

        System.out.println("Executing algo for " + l.size() + " unit of data.");

        //Check if there is no data
        //return 0 for all results
        if (l.isEmpty()) {
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("production", 0);
            map.put("ralenti", 0);
            map.put("arret", 0);
            return map;
        }

        int a = 0, r = 0, p = 0;
        int i = 0;
        float xo;

        for (i = 0; i < l.size() - 1; i++) {

            xo = l.get(i).getX();

            if (xo <= seuilP && xo >= seuilR) {

                r = r + 1;
            } else if (xo > seuilP) {
                p += 1;
            }

        }

        if (l.get(i).getX() > seuilP) p++;

        if (l.get(i).getX() <= seuilP && l.get(i).getX() >= seuilR) r++;

        p = p * temps;
        r = r * temps;
        Map<String, Integer> map = new HashMap<>();
        map.put("production", p);
        map.put("ralenti", r);

        return map;

    }

    public Map<String, Integer> calcul(List<Calcul> l) {
        Map<String, Integer> map = new HashMap<>();
        int production = 0, ralenti = 0;
        for (Calcul c : l) {
            production += c.getTempsP();
            ralenti += c.getTempsR();
        }
        map.put("production", production);
        map.put("ralenti", ralenti);
        return map;
    }


}
