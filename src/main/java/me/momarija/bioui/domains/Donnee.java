package me.momarija.bioui.domains;

import javax.persistence.*;

@Entity
public class Donnee {

    @Id
    @GeneratedValue
    private int id;

    private String date;

    private String heure;

    private float x;

    private int enginId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public int getEnginId() {
        return enginId;
    }

    public void setEnginId(int enginId) {
        this.enginId = enginId;
    }
}
