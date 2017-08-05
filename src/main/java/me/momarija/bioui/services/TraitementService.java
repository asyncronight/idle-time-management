package me.momarija.bioui.services;

import me.momarija.bioui.domains.Donnee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TraitementService {

        public Map<String,Integer> MyFunction(List<Donnee> l , int temps , int interval , float seuilP, float seuilR){

            System.out.println("Ready");

            int a=0,r=0,p=0;
            int k=1,i,somme=0;
            List<Integer> lista = new ArrayList<>();
            float xo_1;
            float xo;

            for (i=0 ; i<l.size()-1 ; i++){

                xo =l.get(i).getX() ;
                xo_1 = l.get(i+1).getX() ;


                if(xo <= seuilP && xo >= seuilR){
                    if(xo_1 <= seuilP && xo_1 >= seuilR){
                        k=k+1;
                    }
                    else {
                        lista.add(k);
                        k=1;
                    }
                    r=r+1;
                }else {

                    if(xo < seuilR ) {
                        a +=1;
                    }
                    else if(xo > seuilP){
                        p+=1;
                    }
                }

            }
            if(l.get(i).getX() > seuilP ) p++;

            if(l.get(i).getX() <= seuilP && l.get(i).getX()  > seuilR) r++;

            if(l.get(i).getX() < seuilR) a++;

            for(int j=0;j< lista.size() ; j++){
                if(lista.get(j)<=interval) somme +=lista.get(j);
            }
            p = (p+somme)*temps;
            r = (r-somme)*temps;
            a = a*temps;


            Map<String,Integer> map = new HashMap<>();
            map.put("production",p);
            map.put("ralenti",r);
            map.put("arret",a);

            return map;

        }
        //******************************************************************************
        // convert seconde to date
        //******************************************************************************
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
