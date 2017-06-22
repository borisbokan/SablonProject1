package com.borcha.sablonproject1.myActivitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.borcha.sablonproject1.R;
import com.borcha.sablonproject1.myDB.MySqLGlumci.MySqlStavkka;
import com.borcha.sablonproject1.myDB.dbmodel.Kateggorija;
import com.borcha.sablonproject1.myDB.dbmodel.Stavkka;

/**
 * Created by androiddevelopment on 13.6.17..
 */

public class UnosIspravkaKateggorija extends AppCompatActivity implements View.OnClickListener {

    public static final int TIPOPERACIJE_NOVO=0;//Ukoliko dolazimo sa novim ulazom
    public static final int TIPOPERACIJE_ISPRAVI=1;//Ukoliko dolazimo da ispravimo podatak

    EditText etxtNaziv,etxtOpis;

    Button btnSnimi,btnOdustajem;

    private int tip_ope=0;
    private int idStavkka;
    private Stavkka stavkka;
    private Kateggorija kateggorija;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unos_ispravka_kateggorija);

        tip_ope=getIntent().getIntExtra("operacija",0);
        idStavkka=getIntent().getIntExtra("id_Stavkka",0);

        etxtNaziv=(EditText)findViewById(R.id.etxtNaziv_unosStavkka);
        etxtOpis=(EditText)findViewById(R.id.etxtOpis_unosStavkka);

        btnSnimi=(Button)findViewById(R.id.btnSnimi_unosispravka);
        btnOdustajem=(Button)findViewById(R.id.btnOdustajem_unosispravka);

        if(tip_ope==TIPOPERACIJE_ISPRAVI && idStavkka!=0){


            getStavkkaPoId(idStavkka);
            pripremiZaIspravku();
        }


        btnSnimi.setOnClickListener(this);
        btnOdustajem.setOnClickListener(this);

    }


    private void pripremiZaIspravku() {

        etxtNaziv.setText(this.stavkka.getNaziv());
        etxtOpis.setText(this.stavkka.getOpis());


    }

    private void getStavkkaPoId(int _id){

        MySqlStavkka dbstavkka=new MySqlStavkka(this);
        this.stavkka=dbstavkka.getStavkkaPoId(_id);

    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnSnimi_unosispravka:

                if(tip_ope==TIPOPERACIJE_NOVO){
                    snimi();

                }else{
                    ispravi();
                }
                break;


            case R.id.btnOdustajem_unosispravka:

                 finish();

                break;

            default:

                break;


        }
    }


    private void getStavkka(int _id){

        MySqlStavkka dbstavkka=new MySqlStavkka(this);
        this.stavkka=dbstavkka.getStavkkaPoId(_id);

    }

    private void ispravi() {
         getStavkka(idStavkka);

         MySqlStavkka dbstavkka=new MySqlStavkka(this);
         dbstavkka.setKateggorija(this.kateggorija);
         dbstavkka.prepraviStavkka();


    }

    private void snimi() {
         final Stavkka novStavkka=new Stavkka();
        novStavkka.setNaziv(this.etxtNaziv.getText().toString());
        novStavkka.setOpis(this.etxtOpis.getText().toString());

        MySqlStavkka dbstavkka=new MySqlStavkka(this);
        dbstavkka.setStavkka(novStavkka);

        dbstavkka.snimiNoviStavkka(novStavkka);

    }
}
