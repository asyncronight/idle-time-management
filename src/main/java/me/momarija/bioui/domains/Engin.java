package me.momarija.bioui.domains;

import javax.persistence.*;
import java.util.List;

@Entity
public class Engin {

    @Id
    @GeneratedValue
    private int id;

    private float seuilP;

    private float seuilR;

    private String photo;

    private int interval;

    private int temps;

    @ManyToOne
    @JoinColumn(name = "chantier_id")
    private Chantier chantier;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getSeuilP() {
        return seuilP;
    }

    public void setSeuilP(float seuilP) {
        this.seuilP = seuilP;
    }

    public float getSeuilR() {
        return seuilR;
    }

    public void setSeuilR(float seuilR) {
        this.seuilR = seuilR;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getTemps() {
        return temps;
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }

    public Chantier getChantier() {
        return chantier;
    }

    public void setChantier(Chantier chantier) {
        this.chantier = chantier;
    }
}
