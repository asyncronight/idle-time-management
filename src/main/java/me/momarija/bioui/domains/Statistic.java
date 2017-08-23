package me.momarija.bioui.domains;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
public class Statistic {


    @DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
    private Date dateFrom;


    @DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
    private Date dateTo;


    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date dayFrom;

    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date dayTo;

    @DateTimeFormat(pattern="HH:mm")
    private Date hourFrom;

    @DateTimeFormat(pattern="HH:mm")
    private Date hourTo;

    @DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
    private Date date;


    private int nbHourRepos;

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Date getHourFrom() {   return hourFrom;    }

    public void setHourFrom(Date hourFrom) {this.hourFrom = hourFrom; }

    public Date getHourTo() {  return hourTo;   }

    public void setHourTo(Date hourTo) {  this.hourTo = hourTo;  }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDayFrom() {
        return dayFrom;
    }

    public void setDayFrom(Date dayFrom) {
        this.dayFrom = dayFrom;
    }

    public Date getDayTo() { return dayTo;    }

    public void setDayTo(Date dayTo) { this.dayTo = dayTo;    }

    public int getNbHourRepos() {
        return nbHourRepos;
    }

    public void setNbHourRepos(int nbHourRepos) {
        this.nbHourRepos = nbHourRepos;
    }

    public int calculNbJours(){
        SimpleDateFormat stf = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat stimef = new SimpleDateFormat("HH:mm");

        String dayFrom =stf.format(getDayFrom());
        String hourFrom = stimef.format(getHourFrom());
        String dayTo =stf.format(getDayTo());
        String hourTo = stimef.format(getHourTo());

        Date d1 = new Date(dayFrom+" "+hourFrom);
        Date d2 = new Date(dayTo+" "+hourFrom);

        long ms = d2.getTime() - d1.getTime() ;

        int nbJours = (int)((ms /1000 )/3600)/24;
        return nbJours;
    }

}
