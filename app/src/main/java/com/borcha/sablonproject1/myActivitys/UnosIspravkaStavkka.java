package com.borcha.sablonproject1.myActivitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.borcha.sablonproject1.R;
import com.borcha.sablonproject1.myDB.MySqLGlumci.MySqlStavkka;
import com.borcha.sablonproject1.myDB.dbmodel.Kateggorija;
import com.borcha.sablonproject1.myDB.dbmodel.Stavkka;

/**
 * Created by borcha on 15.06.17..
 */

public class UnosIspravkaStavkka extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    public static final int TIPOPERACIJE_NOVO = 0;
    public static final int TIPOPERACIJE_ISPRAVI = 1;

    EditText etxtNaziv,etxtOpis;
    Button btnOdustajem,btnSnimi;
    Spinner spKateggorija;
    Stavkka stavkka;
    Kateggorija kateggorija;
    private int tipOpe;

    private int selPosZanra;
    private int idStavkka;
    private int idStavvka;

    private int idKateggorija;
    private String selKategorija;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.unos_ispravka_stavkka);

        tipOpe=getIntent().getIntExtra("tip_ope",0);
        idStavvka=getIntent().getIntExtra("id_stavkka",0);
        idKateggorija=getIntent().getIntExtra("id_kateggorija",0);



        if(tipOpe==TIPOPERACIJE_ISPRAVI && idStavkka!=0){

            getStavkkaPoId(idStavkka);
            pripremiZaIspravku();
        }

        etxtNaziv=(EditText)findViewById(R.id.etxtNaziv_unosStavkka);
        etxtOpis=(EditText)findViewById(R.id.etxtOpis_unosStavkka);

        spKateggorija=(Spinner)findViewById(R.id.spKateggorija_unosStavkka);

        ArrayAdapter adZanr=new ArrayAdapter(this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.kateggorija));
        spKateggorija.setAdapter(adZanr);

        spKateggorija.setOnItemSelectedListener(this);


        btnOdustajem=(Button)findViewById(R.id.btnOdustajem_unosStavkka);
        btnSnimi=(Button)findViewById(R.id.btnSnimi_unosStavkka);

        btnSnimi.setOnClickListener(this);
        btnOdustajem.setOnClickListener(this);

    }

    private void pripremiZaIspravku() {

          etxtNaziv.setText(this.stavkka.getNaziv());
          etxtOpis.setText(this.stavkka.getOpis());
          spKateggorija.setSelection(selktovanaPoziPoSadrzaju(this.stavkka.getKateggorija().toString()));

    }

    private void getStavkkaPoId(int _idStavkka) {

        MySqlStavkka dbstavkka=new MySqlStavkka(this);
        this.stavkka= dbstavkka.getStavkkaPoId(_idStavkka);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnOdustajem_unosStavkka:
                moveTaskToBack(true);
                finish();
                break;


            case R.id.btnSnimi_unosStavkka:

                if(tipOpe==TIPOPERACIJE_NOVO){
                    snimi();
                }else{
                    ispravi();
                }

                break;


            default:

                break;
        }
    }

    private void ispravi() {
        MySqlStavkka dbstavvka=new MySqlStavkka(this);
        dbstavvka.setStavkka(this.stavkka);
        dbstavvka.prepraviStavkka();
    }

    private void snimi() {

        Stavkka novaStavkka=new Stavkka();
        novaStavkka.setNaziv(etxtNaziv.getText().toString());
        novaStavkka.setOpis(etxtOpis.getText().toString());
        novaStavkka.setKateggorija(this.kateggorija);
        MySqlStavkka dbfilm=new MySqlStavkka(this);
        dbfilm.snimiNoviStavkka(novaStavkka);

    }

    private int selktovanaPoziPoSadrzaju(String _sadrzaj){
        String[] zanrovi=getResources().getStringArray(R.array.kateggorija);
        int pozicija;
        int brojac=0;
        for (String stavka : zanrovi) {
               if(_sadrzaj.contentEquals(stavka)){
                    pozicija=brojac;
               }
               brojac++;
        }

        return  brojac;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selKategorija=(String)parent.getItemAtPosition(position);
        selPosZanra=position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
         spKateggorija.setSelection(0);
    }
}
