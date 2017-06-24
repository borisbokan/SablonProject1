package com.borcha.sablonproject1.myDB.dbmodel;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;


/**
 * Created by borcha on 20.05.17..
 */
@DatabaseTable(tableName = Kateggorija.tKateggorija)
public class Kateggorija {

    public static final String tKateggorija="tKateggorija";
    public static final String tKateggorija_id="id";
    public static final String tKateggorija_naziv="naziv";



    @DatabaseField(columnName = tKateggorija_id,generatedId = true)
    private int id;
    @DatabaseField(columnName = tKateggorija_naziv)
    private String naziv;

    @ForeignCollectionField(foreignFieldName = "kateggorija",eager = true)
    ForeignCollection<Stavkka> stavkke;

    public Kateggorija() { }
    public static String gettKateggorija() {
        return tKateggorija;
    }

    public static String gettKateggorija_id() {
        return tKateggorija_id;
    }

    public static String gettKateggorija_naziv() {
        return tKateggorija_naziv;
    }

    public void setStavkke(ForeignCollection<Stavkka> stavkke) {
        this.stavkke = stavkke;
    }



    public Kateggorija(String _naziv) {
        this.naziv = _naziv;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }



    public ForeignCollection<Stavkka> getStavkke() {
        return this.stavkke;
    }




    public String toString() {
        return  id + "-" + naziv;
    }

}
