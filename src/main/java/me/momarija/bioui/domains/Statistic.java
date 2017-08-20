package me.momarija.bioui.domains;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import java.util.Date;

public class Statistic {

    @DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
    private Date dateFrom;

    @DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
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
