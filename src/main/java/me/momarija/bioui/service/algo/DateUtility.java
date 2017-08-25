package me.momarija.bioui.service.algo;


import java.util.Date;

public class DateUtility {

	public String convertToDate(int time){
		int nbJ=0;
		int nbH=0;
		int nbM=0;
		while(time-3600*24 >= 0){
			nbJ++;
			time-=3600*24;
		}
		while(time-3600 >= 0){
			nbH++;
			time-=3600;
		}
		while(time-60 >= 0){
			nbM++;
			time-=60;
		}
		return ((nbJ==0)? "":nbJ+" jours ") +((nbH==0)? "": nbH + " h " )+ ((nbM==0)? "":nbM +" min " )+ time +" s";
	}



	public long convertToTime(Date date1,Date date2){
		//returning the difference between two dates
		long d1 = date1.getTime();
		long d2 = date2.getTime();
        return d2 - d1 ;
	}
}
