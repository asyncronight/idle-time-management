package me.momarija.bioui.services.algo;


public class DateUtility {

	public String convertToDate(int time){
		int nbH=0;
		int nbM=0;
		while(time-3600 >= 0){
			nbH++;
			time-=3600;
		}
		while(time-60 >= 0){
			nbM++;
			time-=60;
		}
		return nbH + " h" + nbM +" min" + time +" s";
	}
}
