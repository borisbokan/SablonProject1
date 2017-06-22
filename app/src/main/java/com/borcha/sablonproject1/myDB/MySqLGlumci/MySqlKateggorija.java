package com.borcha.sablonproject1.myDB.MySqLGlumci;

import android.app.NotificationManager;
import android.content.Context;
import android.widget.Toast;

import com.borcha.sablonproject1.myActivitys.SettingsActivity;
import com.borcha.sablonproject1.myDB.MyDbHelp;
import com.borcha.sablonproject1.myDB.dbmodel.Kateggorija;
import com.borcha.sablonproject1.myDB.dbmodel.Stavkka;
import com.borcha.sablonproject1.pomocne.infoPoruka;
import com.borcha.sablonproject1.pomocne.myNotification;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.List;


/**
 * Created by borcha on 02.06.17..
 */

public class MySqlKateggorija extends MyDbHelp {

    private boolean toasUklj;
    private boolean notifUkl;
    private Context cont;
    private Kateggorija kateggorija;
    private int id=0;
    NotificationManager mNotificationManager;

    /**
     * Konstruktor za unos. Nap. Ukoliko je sa Id-om ima moguce dodatne operacije kao sto su: <br> Update ili Delete.
     * @param _cont
     */
    public MySqlKateggorija(Context _cont) {
        super(_cont);
        this.cont=_cont;

        SettingsActivity podesavanja=new SettingsActivity();
        notifUkl= podesavanja.jelNotifikacionaPorukaUkljucena();
        toasUklj=podesavanja.jelToastPorukaUkljucena();

         mNotificationManager = (NotificationManager)cont.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    /**
     * Konstruktor sa Id-om je ukoliko saljemo u cilju update ili brisanja podatka.
     * @param _cont
     * @param _id
     */
    public MySqlKateggorija(Context _cont, int _id) {
        super(_cont);
        this.cont=_cont;
        this.id=_id;

        SettingsActivity podesavanja=new SettingsActivity();
        notifUkl= podesavanja.jelNotifikacionaPorukaUkljucena();
        toasUklj=podesavanja.jelToastPorukaUkljucena();

        mNotificationManager = (NotificationManager)cont.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public MySqlKateggorija(Context _cont, Kateggorija _kateggorija) {
        super(_cont);
        this.cont=_cont;
        this.kateggorija=_kateggorija;

        SettingsActivity podesavanja=new SettingsActivity();
        notifUkl= podesavanja.jelNotifikacionaPorukaUkljucena();
        toasUklj=podesavanja.jelToastPorukaUkljucena();

        mNotificationManager = (NotificationManager)cont.getSystemService(Context.NOTIFICATION_SERVICE);
    }



    //*************************operaciej nad bazom *****************************************************


    public void updateKateggorija() {

        int rez=0;

        if(getId()!=0){

            try {
                rez=getDaoKateggorija().updateId(kateggorija,getId());

                if(rez==1){
                   uslovnaNotifikacija("Ispravka kateggorija","Ispravka kateggorija " + kateggorija.getNaziv() + " uspesna");
                    uslovnaToastPoruka("Ispravka kateggorija","Ispravka kateggorija" + kateggorija.getNaziv().toString()  + " uspesna");
                }else{
                    uslovnaNotifikacija("Ispravka kateggorija","Ispravka kateggorija " + kateggorija.getNaziv()  + " neuspesna. Greska!");
                    uslovnaToastPoruka("Ispravka kateggorija","Ispravka kateggorija " + kateggorija.getNaziv()  + " neuspesna. Greska!");
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }


        }else{
            infoPoruka.newInstance(cont,"Poruka o gresci","Ne postoji ID zapisa!. Ne mozete prepraviti podatak za Kateggorija");

        }

    }

    private void uslovnaToastPoruka(String naslov, String poruka) {
        if(toasUklj){
            Toast.makeText(cont,naslov + " - " + poruka,Toast.LENGTH_LONG).show();
        }
    }

    private void uslovnaNotifikacija(String naslov, String poruka) {

        if(notifUkl){

            myNotification notifikacija=new myNotification(cont,naslov,poruka);
            mNotificationManager.notify(13,notifikacija.build());
        }
    }

    /**
     * Brisanje jela
     */
    public void obrisiKateggorija() {
        int rez= 0;

        try {
                rez = getDaoKateggorija().delete(this.kateggorija);
            if(rez==1){
                uslovnaNotifikacija("Brisanje glumca","Brisanje glumca " +  this.kateggorija.getNaziv() + " uspesna");
                uslovnaToastPoruka("Brisanje glumca","Brisanje glumca " + this.kateggorija.getNaziv()  + " uspesna");
            }else{
                uslovnaNotifikacija("Brisanje glumca","Brisanje glumca " + this.kateggorija.getNaziv()  + " neuspesna. Greska!");
                uslovnaToastPoruka("Brisanje glumca","Brisanje glumca " + this.kateggorija.getNaziv()  + " neuspesna. Greska!");
            }


                //ObrisiKateggorija.OnObrisiKateggorija(rez);
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    /**
     * Unos novog jela
     * @param _kateggorija
     */
    public void snimiNovoKateggorija(Kateggorija _kateggorija) {

        if(!_kateggorija.equals(null)){
            //TODO. Uraditi Sql upit za delete
            int rez= 0;
            try {
                rez = getDaoKateggorija().create(_kateggorija);

                if(rez==1){
                    uslovnaNotifikacija("Unos kateggorija","Unos kateggorija " + kateggorija.getNaziv()  + " uspesna");
                    uslovnaToastPoruka("Unos kateggorija","Unos kateggorija " + kateggorija.getNaziv()  + " uspesna");
                }else{
                    uslovnaNotifikacija("Unos kateggorija","Unos kateggorija " + kateggorija.getNaziv()  + " neuspesna. Greska!");
                    uslovnaToastPoruka("Unos kateggorija","Unos kateggorija " + kateggorija.getNaziv() + " neuspesna. Greska!");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }


        }else{
            infoPoruka.newInstance(cont,"Poruka o gresci","Objekat Kateggorija ima  null vrednsot");
        }



    }


    //Vraca listu svih objekata Kateggorija
    public List<Kateggorija> getSviGlumci()  {
        List<Kateggorija> glumci=null;
        try {
            glumci= getDaoKateggorija().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return glumci;
    }

    //Trazi vrednost jela po ID zapisu
    public Kateggorija getKateggorijaPoId(int _id)  {

        try {
            return getDaoKateggorija().queryForId(_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  Vraca listu jela po kategoriji
     */

    public List<Kateggorija> getKateggorijaiPioStavkka(Stavkka _stavkka)  {
        List<Kateggorija> glumci=null;
        try {
            QueryBuilder upit = getDaoKateggorija().queryBuilder();
            Where<Kateggorija,Integer> where=upit.where().idEq(getDaoStavkka(),_stavkka);
            glumci=where.query();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return glumci;
    }

    public void setKateggorija(Kateggorija Kateggorija) {
        this.kateggorija = Kateggorija;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrojKateggorijaa() {
        int br = 0;
        try {
            br = getDaoKateggorija().queryForAll().size();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return br;
    }


}
