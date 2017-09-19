package me.momarija.bioui.domains;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;
public class Statistic {

	public static int WEEK = 0;
	public static int TWOWEEKS = 1;
	public static int MONTH = 2;

    @DateTimeFormat(pattern="dd/MM/yyyy")
	@NotNull
	private Date dayFrom;

    @DateTimeFormat(pattern="dd/MM/yyyy")
	@NotNull
	private Date dayTo;

	public static Statistic getStatistic(int a) {
		Statistic statistic = new Statistic();
		Date date = new Date();
		statistic.setDayTo(date);
		if (a == 0)
			date.setTime(date.getTime() - 1000 * 518400);
		else if (a == 1)
			date.setTime(date.getTime() - 1000 * 1814400);
		else
			date.setTime(date.getTime() - 1000 * 1814400 - 1000 * 604800 - 1000 * 302400);
		statistic.setDayFrom(date);
		statistic.addHour();
		return statistic;
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

	public void addHour() {
		this.dayFrom.setHours(0);
		this.dayFrom.setMinutes(0);
		this.dayTo.setHours(23);
		this.dayTo.setMinutes(59);
	}
}
