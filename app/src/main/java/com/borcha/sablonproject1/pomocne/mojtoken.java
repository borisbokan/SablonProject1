package com.borcha.sablonproject1.pomocne;

import java.util.StringTokenizer;

/**
 * Created by borcha on 19.1.2016..
 */
public class mojtoken {

   int brDelj=0;String textZaDelj;
   String karakter;
    String[] rezultat;


    public mojtoken(String _vrednostZaDeljenje,String _karakZaDeljenje,int _naKolikoTrebaPodeliti){
        brDelj=_naKolikoTrebaPodeliti;
        textZaDelj=_vrednostZaDeljenje;
        karakter=_karakZaDeljenje;

        rezultat=new String[_naKolikoTrebaPodeliti];
        if(_naKolikoTrebaPodeliti==0){
            brDelj=2;
        }


   }

    public String[] rezultat(){
        int i=0;
        StringTokenizer st=new StringTokenizer(textZaDelj,karakter);
        String[] tokeni=new String[brDelj];

        while(st.hasMoreTokens()){
            tokeni[i]=st.nextToken();
            i++;

        }
        return  tokeni;
    }

}
