package com.borcha.sablonproject1.myActivitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.borcha.sablonproject1.R;
import com.borcha.sablonproject1.myDB.MySqLGlumci.MySqlKateggorija;
import com.borcha.sablonproject1.myDB.MySqLGlumci.MySqlStavkka;
import com.borcha.sablonproject1.myDB.dbmodel.Kateggorija;
import com.borcha.sablonproject1.myDB.dbmodel.Stavkka;

import java.util.List;

/**
 * Created by borcha on 15.06.17..
 */

public class DetaljiStavkka extends AppCompatActivity {


    private int idGlumac=0;
    TextView txvNaziv,txvOpis;
    ListView lsvKategorije;
    Kateggorija kateggorija;
    private Stavkka stavkka;
    private int idStavkka;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalji);
        idStavkka=getIntent().getIntExtra("id_stavkka",0);
        getStavkkaPoId(idGlumac);
        //savedInstanceState.putInt("id_glumac",idGlumac);

        txvNaziv=(TextView)findViewById(R.id.txvNazivStvakka_detalji);
        txvOpis=(TextView)findViewById(R.id.txvDatumRodjenja_detalji);
        lsvKategorije=(ListView)findViewById(R.id.lsvKateggorija_detalji);

        setujPodatkeKateggorijaNaOsnovuID();


    }


    private void getStavkkaPoId(int _id){

        MySqlStavkka dbstavka=new MySqlStavkka(this);
        this.stavkka=dbstavka.getStavkkaPoId(_id);

    }

    private void setujPodatkeKateggorijaNaOsnovuID() {

        if(idGlumac>0){

            txvNaziv.setText(stavkka.getNaziv());
            txvOpis.setText(stavkka.getOpis().toString());

            ArrayAdapter<Kateggorija> adkateggorije=new ArrayAdapter<Kateggorija>(this,android.R.layout.simple_list_item_1,getListaKateggorija(this.stavkka));
            lsvKategorije.setAdapter(adkateggorije);
        }
    }

    private List<Kateggorija> getListaKateggorija(Stavkka _stavkka) {

        MySqlKateggorija dbkateggorija=new MySqlKateggorija(this);
        List<Kateggorija> lista=dbkateggorija.getKateggorijaiPioStavkka(_stavkka);

        return lista;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.menu_detalji,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.dodaj_kateggorija:

                Intent inUnoIspKateggorija=new Intent(this,UnosIspravkaKateggorija.class);
                inUnoIspKateggorija.putExtra("operacija", UnosIspravkaStavkka.TIPOPERACIJE_NOVO);
                inUnoIspKateggorija.putExtra("id_stavkka",this.stavkka.getId());
                startActivity(inUnoIspKateggorija);

                return super.onOptionsItemSelected(item);


            case R.id.ispravi_stavkka:

                Intent inIspravkaStavkka=new Intent(this,UnosIspravkaStavkka.class);
                inIspravkaStavkka.putExtra("operacija", UnosIspravkaKateggorija.TIPOPERACIJE_ISPRAVI);
                inIspravkaStavkka.putExtra("id_stavkka",idStavkka);
                startActivity(inIspravkaStavkka);

                return super.onOptionsItemSelected(item);


            case R.id.obrisi_stavkka:

               MySqlStavkka dbgstavkka=new MySqlStavkka(this,stavkka);
                dbgstavkka.obrisiStavkka();
                moveTaskToBack(true);

                return super.onOptionsItemSelected(item);


            default:

                return super.onOptionsItemSelected(item);
        }

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("id_stavkka",idStavkka);


    }

    @Override
    protected void onResume() {
        super.onResume();
        setujPodatkeKateggorijaNaOsnovuID();
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        idGlumac=savedInstanceState.getInt("id_stavkka",0);
    }
}
