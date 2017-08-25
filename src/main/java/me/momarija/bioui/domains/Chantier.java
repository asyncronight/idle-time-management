package me.momarija.bioui.domains;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Chantier {

    @Id
    @GeneratedValue
    private int id;

    @NotEmpty
    @Size(min = 3,max = 20)
    private String nom;

    @NotEmpty
    @Size(min = 10, max = 100)
    private String adresse;

    @OneToMany(mappedBy = "chantier", cascade = CascadeType.ALL)
    private List<Engin> engins;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<Engin> getEngins() {
        return engins;
    }

    public void setEngins(List<Engin> engins) {
        this.engins = engins;
    }
}
