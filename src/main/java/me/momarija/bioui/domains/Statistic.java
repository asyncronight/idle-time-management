package me.momarija.bioui.domains;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Statistic {

    @DateTimeFormat(pattern="dd/MM/yyyy")
	@NotNull
	private Date dayFrom;

    @DateTimeFormat(pattern="dd/MM/yyyy")
	@NotNull
	private Date dayTo;

    @DateTimeFormat(pattern="HH:mm")
	@NotNull
	private Date hourFrom;

    @DateTimeFormat(pattern="HH:mm")
	@NotNull
	private Date hourTo;

	@Min(0)
	private int nbHourRepos;

	public Statistic() {

	}

	public Date getDayFrom() {
		return dayFrom;
	}

	public void setDayFrom(Date dayFrom) {
		this.dayFrom = dayFrom;
	}

	public Date getDayTo() {
		return dayTo;
	}

	public void setDayTo(Date dayTo) {
		this.dayTo = dayTo;
	}

	public Date getHourFrom() {
		return hourFrom;
	}

	public void setHourFrom(Date hourFrom) {
		this.hourFrom = hourFrom;
	}

	public Date getHourTo() {
		return hourTo;
	}

	public void setHourTo(Date hourTo) {
		this.hourTo = hourTo;
	}

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
