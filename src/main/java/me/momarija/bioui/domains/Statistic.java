package me.momarija.bioui.domains;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import java.util.Date;

public class Statistic {
    @DateTimeFormat(pattern="dd-MMM-YYYY HH:mm:ss")
    private Date dateFrom;
    @DateTimeFormat (pattern="dd-MMM-YYYY  HH:mm:ss")
    private Date dateTo;


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
}
