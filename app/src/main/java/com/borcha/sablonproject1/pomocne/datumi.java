package com.borcha.sablonproject1.pomocne;

import android.util.Log;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

/**
 * Klasa koja pomaze u radu sa datumima i svim proracunima u okviru datuma.
 *
 * @author borcha
 */
public class datumi {

    public static final long milisekPoDanu = 24 * 60 * 60 * 1000;
    public int GODINA;
    public int MESEC;
    public int DAN;
    public int SAT;
    public int MINUT;
    public int SEKUND;
    public long vremeUMiliSek;
    public int brojDanaUMesecu;
    public int brNedeljaUmesecu;
    public Date datumIzMilisek;
    public Date datum;
    Calendar kalendar;
    private String format = "dd-MM-yyyy";

    public datumi() {
        kalendar = new GregorianCalendar();
    }

    public datumi(int god, int mes, int dan) {

        this.GODINA = god;
        this.MESEC = mes-1;
        this.DAN = dan;
        kalendar = new GregorianCalendar();
        kalendar.set(this.GODINA, this.MESEC, this.DAN);

        datumUMilisek();
        brDanaUMesecu();
        brNedeljaUmesecu();
    }

    public datumi(int god, int mes, int dan, int sat, int min, int sek) {

        this.GODINA = god;
        this.MESEC = mes-1;
        this.DAN = dan;
        this.SAT = sat;
        this.MINUT = min;
        this.SEKUND = sek;

        kalendar = new GregorianCalendar();
        kalendar.set(this.GODINA, this.MESEC, this.DAN, this.SAT, this.MINUT,
                this.SEKUND);

        datumUMilisek();
        brDanaUMesecu();
        brNedeljaUmesecu();

    }

    private static long izracunajRazlikuDana(Date datumPre, Date datumPosle) {
        return (datumPosle.getTime() - datumPre.getTime())
                / (24 * 60 * 60 * 1000);
    }

    private static long danaIzmedju(Date startDatum, Date krajDatum) {
        return 0;
    }

    /**
     * Metoda vraca naziv oznake dan-mesec-godina.<br>
     * pr. datum 12-5-2013<br>
     * <i>Rez</i><br>
     * [0][0]-dan<br>
     * [0][1]-mesec<br>
     * [0][2]-godina <br>
     *
     * @param _datum
     * @return Array tj [0][0],[0][1],[0][2]
     */
    public static String[] oznakeDatuma(String _datum) {
        StringTokenizer datTok = new StringTokenizer(_datum, "-");
        int dan = 0;
        int mesec = 0;
        int godina = 0;

        while (datTok.hasMoreTokens()) {
            dan = Integer.valueOf(datTok.nextToken());
            mesec = Integer.valueOf(datTok.nextToken());
            godina = Integer.valueOf(datTok.nextToken());
        }
        String[] rez = new String[3];
        rez[0] = String.valueOf(dan);
        rez[1] = String.valueOf(mesec);
        rez[2] = String.valueOf(godina);
        return rez;

    }

    // proveri da li je datum u opsegu izmedju startnog i krajnjeg tj zavrsnog
    public static boolean datumUOpsegu(long _startni, long _zavrsni,
                                       long _trenutni) {
        return _trenutni > _startni && _trenutni < _zavrsni;


    }

    // Metoda vraca naziv dana za trazeni datum.
    public static String nazivDanaUNedelji(String _datum) {
        StringTokenizer datTok = new StringTokenizer(_datum, "-");
        int dan = 0;
        int mesec = 0;
        int godina = 0;

        while (datTok.hasMoreTokens()) {
            dan = Integer.valueOf(datTok.nextToken());
            mesec = Integer.valueOf(datTok.nextToken());
            godina = Integer.valueOf(datTok.nextToken());
        }

        GregorianCalendar kalGre = new GregorianCalendar();
        kalGre.set(Calendar.YEAR, godina);
        kalGre.set(Calendar.MONTH, mesec - 1);
        kalGre.set(Calendar.DAY_OF_MONTH, dan);

        int foDa = kalGre.get(Calendar.DAY_OF_WEEK);
        String rez = vratiDan(foDa);

        return rez;
    }

    public static String vratiDan(int foDa) {
        String rez = "";
        switch (foDa) {
            case 1:
                rez = "Nedelja";
                break;
            case 2:
                rez = "Ponedeljak";
                break;
            case 3:
                rez = "Utorak";
                break;
            case 4:
                rez = "Sreda";
                break;
            case 5:
                rez = "Cetvrtak";
                break;
            case 6:
                rez = "Petak";
                break;
            case 7:
                rez = "Subota";
                break;

        }
        return rez;
    }

    // Metode za obradu podataka.
    private void datumUMilisek() {
        this.vremeUMiliSek = kalendar.getTimeInMillis();

    }

    /**
     * Vraca vrednost datuma iz tipa datuma milisecond </br> <b>Format u obliku:
     * </b> &nbsp; dan-mesec-godina (dd-MM-yyyy).
     *
     * @param vrednost je tipa <i>long</i> (u bazi se cuva podatak ovog tipa)
     * @return String
     */
    @SuppressWarnings("ConstantConditions")
    public String datumIZMilisek(long vrednost) {

        SimpleDateFormat datform = null;
        try {
            datform = new SimpleDateFormat(format);
        } catch (NullPointerException e) {
            // TODO Auto-generated catch block
            Log.e("Greska 1", e.toString());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            Log.e("Greska 2", e.toString());
            e.printStackTrace();
        }

        return datform.format(new Date(vrednost));

    }

    /**
     * @param _datum1
     * @param _datum2
     * @return
     */
    public long brojDana(String _datum1, String _datum2) {

        Date datum1 = null;
        Date datum2 = null;

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        long rez = 0;
        long d1;
        long d2;
        try {
            d1 = df.parse(_datum1).getTime();
            d2 = df.parse(_datum2).getTime();
            rez = (d2 - d1) / (24 * 60 * 60 * 1000);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return rez;

    }

    private void brDanaUMesecu() {

        this.brojDanaUMesecu = kalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    private void brNedeljaUmesecu() {
        this.brNedeljaUmesecu = kalendar.getActualMaximum(Calendar.DAY_OF_WEEK);
    }

    // GET AND SET
    // -------------------------------------------------------------------------------
    public int getBrojDanaUMesecu() {
        return brojDanaUMesecu;
    }

    /**
     * Vraca vreme u milisek (moja metoda).
     *
     * @return
     */
    public long getVremeUMiliSek() {
        return vremeUMiliSek;
    }

    public int getBrojNedeljaUMesecu() {
        return brNedeljaUmesecu;
    }

    // -------------------set -------------------
    public void setGODINA(int gODINA) {
        GODINA = gODINA;
    }

    public void setMESEC(int mESEC) {
        MESEC = mESEC;
    }

    public void setDAN(int dAN) {
        DAN = dAN;
    }

    //

    public void setSAT(int sAT) {
        SAT = sAT;
    }

    public void setMINUT(int mINUT) {
        MINUT = mINUT;
    }

    public void setSEKUND(int sEKUND) {
        SEKUND = sEKUND;
    }

    /**
     * Mala klasa za vracanje datuma u milisekundama.<br>
     * Moze se primeniti sa matematickim operacima (-,+,*) u cilju dobijanja
     * razlike od <br>
     * jednog datuma do drugog. Vratiti u datum . <b>Primer </b><br>
     * <i><code>uMiliSekunde(2011,3,12)<code></i>
     *
     * @param god
     * @param mes
     * @param dan
     * @return Long
     */
    public long uMiliSekunde(int god, int mes, int dan) {
        datumi ums = new datumi(god, mes, dan);
        return ums.getVremeUMiliSek();
    }


    public static long getDanasnjiDatum_long(){

        int dan,mes,god;
        Calendar cal=Calendar.getInstance();
       /* dan=cal.get(Calendar.DAY_OF_MONTH);
        mes=cal.get(Calendar.MONTH)+1;
        god=cal.get(Calendar.YEAR);*/
        long miSek=cal.getTimeInMillis();

        return miSek;

    }

    public static long povecajZaBrojDana(long _datumKojiPovec,long _brojDanaZaUvecati){

        long rez= ((1000*60*60*24) * _brojDanaZaUvecati) +_datumKojiPovec;

        return rez;
    }

    public static long getBrojDana_OdDoDatum(long _od,long _do){

         long raz=_do - _od;
         long rez=raz/(1000*60*60*24);

        return rez;
    }

    public int getBrojMeseci_OdDoDatum(long _od,long _do){

        long raz=_od - _do;
        int rez=(int)raz / (12 * 24 * 60 * 60 * 1000);

        return rez;
    }

    public int getBrojDana_ODDanas(long _naznaceniDatum){

        long raz=_naznaceniDatum-getDanasnjiDatum_long();
        int rez=(int)raz / (24 * 60 * 60 * 1000);
        return  rez+1;
    }


    public int getDANIzMilisekunde(long _datumLong){
        int rez=0;
        String datum=datumIZMilisek(_datumLong);
        mojtoken tok=new mojtoken(datum,"-",3);
        rez=Integer.valueOf(tok.rezultat()[0]);

        return rez;
    }

    public int getMESECIzMilisekunde(long _datumLong){
        int rez=0;
        String datum=datumIZMilisek(_datumLong);
        mojtoken tok=new mojtoken(datum,"-",3);
        rez=Integer.valueOf(tok.rezultat()[1]);

        return rez;
    }

    public int getGODINAIzMilisekunde(long _datumLong){
        int rez=0;
        String datum=datumIZMilisek(_datumLong);
        mojtoken tok=new mojtoken(datum,"-",3);
        rez=Integer.valueOf(tok.rezultat()[2]);

        return rez;
    }


    public long datumIzString(String _datum) {
        long sredjenDatum=0L;
        int dan,mesec,godina;

        mojtoken token=new mojtoken(_datum,"-",3);
        dan=Integer.valueOf(token.rezultat()[0]);
        mesec=Integer.valueOf(token.rezultat()[1]);
        godina=Integer.valueOf(token.rezultat()[2]);

        datumi dat=new datumi();
        sredjenDatum= dat.uMiliSekunde(godina,mesec,dan);

        return sredjenDatum;


    }
}
