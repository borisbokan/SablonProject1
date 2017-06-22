package com.borcha.sablonproject1.myDB.MySqLGlumci;

import android.content.Context;

import com.borcha.sablonproject1.myDB.MyDbHelp;
import com.borcha.sablonproject1.myDB.dbmodel.Kateggorija;
import com.borcha.sablonproject1.myDB.dbmodel.Stavkka;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by borcha on 02.06.17..
 */

public class MySqlStavkka extends MyDbHelp {


    private Context cont;
    private Stavkka stavkka;

    public Kateggorija getKateggorija() {
        return kateggorija;
    }

    public void setKateggorija(Kateggorija kateggorija) {
        this.kateggorija = kateggorija;
    }

    private Kateggorija kateggorija;
    private int id=0;



    /**
     * Konstruktor za unos. Nap. Ukoliko je sa Id-om ima moguce dodatne operacije kao sto su: <br> Update ili Delete.
     * @param _cont

     */
    public MySqlStavkka(Context _cont){
        super(_cont);
        this.cont=_cont;

    }

    /**
     * Konstruktor sa Id-om je ukoliko saljemo u cilju update ili brisanja podatka.
     * @param _cont
     * @param _Stavkka
     */
    public MySqlStavkka(Context _cont, Stavkka _Stavkka) {
        super(_cont);
        this.cont = _cont;
        this.stavkka=_Stavkka;

    }


    //*************************operaciej nad bazom *****************************************************

    /**
     * Update jela
     */
    public void prepraviStavkka() {

       int rez= 0;

        try {
            rez = getDaoStavkka().update(stavkka);
            //PrepraviKategoriju.OnPrepraviKategoriju(rez);
        } catch (SQLException e) {
            e.printStackTrace();
        }




    }

    /**
     * Brisanje jela
     */
    public void obrisiStavkka()  {
        int rez= 0;
            try{
                rez=getDaoStavkka().delete(this.stavkka);
                //ObrisiKategoriju.OnObrisiKategoriju(rez);


            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

    /**
     * Unos novog Stavkkaa
     * @param _Stavkka
     */
    public void snimiNoviStavkka(Stavkka _Stavkka) {

        if(!_Stavkka.equals(null)) {
            //TODO. Uraditi Sql upit za delete
            int rez = 0;
            try {
                rez = getDaoStavkka().create(_Stavkka);


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    //Vraca listu svih objekata Jelo
    public List<Stavkka> getSviStavkkaovi() {
        List<Stavkka> lista=new ArrayList<>();
        try {
            lista=getDaoStavkka().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    //Trazi vrednost jela po ID zapisu
    public Stavkka getStavkkaPoId(int _id) {
        Stavkka Stavkka=null;
        try {
            Stavkka= getDaoStavkka().queryForId(_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Stavkka;
    }



    //Trazi vrednost jela po ID zapisu
    public List<Stavkka> getStavkkaPoKateggorija(Kateggorija _kateggorija) {
        List<Stavkka> Stavkkaovi=null;
        try {

            QueryBuilder<Stavkka,Integer> query=getDaoStavkka().queryBuilder();
            Where<Stavkka,Integer> where=query.where().eq(Stavkka.tStavkka_kateggorija,_kateggorija);
            Stavkkaovi= where.query();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Stavkkaovi;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Stavkka getStavkka() {
        return this.stavkka;
    }

    public void setStavkka(Stavkka Stavkka) {
        this.stavkka = Stavkka;
    }


    public int getBrojStavkka(){
        int br=0;
        try {
            br=getDaoStavkka().queryForAll().size();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return br;
    }




}
