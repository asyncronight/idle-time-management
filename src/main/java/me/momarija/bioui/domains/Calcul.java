package me.momarija.bioui.domains;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Calcul {

    @Id
    @GeneratedValue
    private Long id;

    private Date date;

    private int enginId;

    private int tempsR;

    private int tempsP;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getEnginId() {
        return enginId;
    }

    public void setEnginId(int enginId) {
        this.enginId = enginId;
    }

    public int getTempsR() {
        return tempsR;
    }

    public void setTempsR(int tempsR) {
        this.tempsR = tempsR;
    }

    public int getTempsP() {
        return tempsP;
    }

    public void setTempsP(int tempsP) {
        this.tempsP = tempsP;
    }
}
