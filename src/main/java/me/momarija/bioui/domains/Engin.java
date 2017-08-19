package me.momarija.bioui.domains;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Engin {

    @Id
    @GeneratedValue
    private int id;

    @NotEmpty
    @Size(min = 3, max = 40)
    private String marque;

    @NotEmpty
    @Size(min = 3, max = 30)
    private String chauffeur;

    @NotEmpty
    @Pattern(regexp = "[12][0-9]{3}")
    private String annee;

    private float seuilP;

    private float seuilR;

    private String photo;

    private int intervale;

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

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(String chauffeur) {
        this.chauffeur = chauffeur;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
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

    public int getIntervale() {
        return intervale;
    }

    public void setIntervale(int intervale) {
        this.intervale = intervale;
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
