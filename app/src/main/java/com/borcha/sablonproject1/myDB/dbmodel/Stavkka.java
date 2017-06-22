package com.borcha.sablonproject1.myDB.dbmodel;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by androiddevelopment on 13.6.17..
 */
@DatabaseTable(tableName= Stavkka.tStavkka)
public class Stavkka {

    public static final String tStavkka="stavka";
    public static final String tStavkka_id="id";
    public static final String tStavkka_naziv="naziv";
    public static final String tStavkka_opis="opis";
    public static final String tStavkka_kateggorija="kateggorija";


    @DatabaseField(columnName = tStavkka_id,generatedId = true)
    private int id;
    @DatabaseField(columnName = tStavkka_naziv)
    private String naziv;
    @DatabaseField(columnName = tStavkka_opis)
    private String opis;

    @DatabaseField(columnName = tStavkka_kateggorija,foreign = true,foreignAutoCreate = true,foreignAutoRefresh = true)
    private Kateggorija kateggorija;


    public Stavkka(){}

    public Stavkka(String _naziv, String _opis){
        this.naziv=_naziv;
        this.opis=_opis;


    }

    public Kateggorija getKateggorija() {
        return kateggorija;
    }

    public void setKateggorija(Kateggorija kateggorija) {
        this.kateggorija = kateggorija;
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

    public String getOpis() {
        return opis;
    }

    public void setOpis(String _opis) {
        this.opis = _opis;
    }



   public String toString(){
       return this.naziv;   }
}
