package me.momarija.bioui.domains;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Chantier {

    @Id
    @GeneratedValue
    private int id;

    @NotEmpty(message = "Le nom ne peut pas être vide")
    @Length(min = 3,max = 20,message = "Le nom entre 3 et 20 caractères")
    private String nom;

    @Length(max = 400, message = "L'adresse ne peut pas dépasser 400 caractères")
    private String adresse;

    @OneToMany(mappedBy = "chantier")
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
