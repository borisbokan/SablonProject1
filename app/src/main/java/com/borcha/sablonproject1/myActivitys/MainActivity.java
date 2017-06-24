package com.borcha.sablonproject1.myActivitys;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.borcha.sablonproject1.R;

import com.borcha.sablonproject1.myAdapters.AdapterStavkka;
import com.borcha.sablonproject1.myDB.MySqLGlumci.MySqlStavkka;
import com.borcha.sablonproject1.myDB.dbmodel.Kateggorija;
import com.borcha.sablonproject1.myDB.dbmodel.Stavkka;
import com.borcha.sablonproject1.pomocne.DialogInfo;

import java.util.List;
import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity {


    ListView lvStavke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setujTemuIzSettings();

        setContentView(R.layout.main_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intUnosIspIntent=new Intent(getBaseContext(),UnosIspravkaStavkka.class);

                intUnosIspIntent.putExtra("tip_ope",UnosIspravkaStavkka.TIPOPERACIJE_NOVO);
                startActivity(intUnosIspIntent);

            }
        });





        lvStavke=(ListView)findViewById(R.id.lsLista);

        puniDefaultnePodatke();
        puniListu();




    }

    private void setujTemuIzSettings() {
        SharedPreferences setings = getPreferences(MODE_PRIVATE);

        int odabrana=Integer.valueOf(setings.getString("listaTema","0"));
        Log.i("tema",String.valueOf(odabrana));

        switch(odabrana){
            case 0:
               setTheme(R.style.AppTheme);
                break;
            case 1:

                break;

            case 2:
                setTheme(R.style.MojaTemaCrvena);
                break;

            case 3:
                setTheme(R.style.MojaTemaZelena);
                break;
        }


    }

    private void puniDefaultnePodatke() {
        MySqlStavkka dbstavke=new MySqlStavkka(this);

        if(dbstavke.getBrojStavkka()<1){
            Stavkka stav1=new Stavkka("Stavka 1","Opis stavke 1");
            stav1.setKateggorija(new Kateggorija("Kategorija 1"));
            stav1.setKateggorija(new Kateggorija("Kategorija 2"));
            stav1.setKateggorija(new Kateggorija("Kategorija 3"));
            dbstavke.snimiNoviStavkka(stav1);
        }

    }

    private void puniListu() {

        MySqlStavkka dbstavke=new MySqlStavkka(this);

        List<Stavkka> lista=dbstavke.getSviStavkkaovi();
        AdapterStavkka adStavke=new AdapterStavkka(this,lista);
        lvStavke.setAdapter(adStavke);

        if( lvStavke.getCount()<0){
            setContentView(R.layout.empty_layout);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_settings:
                Intent settings=new Intent(this,SettingsActivity.class);
                startActivity(settings);
                return super.onOptionsItemSelected(item);

            case R.id.action_oaplikaciji:
                   DialogInfo.newInstance(this,"O aplikciji","Zavrsna aplikacija \n Boris Bokan AAD 2017-07");

                return super.onOptionsItemSelected(item);
        }


        return super.onOptionsItemSelected(item);
    }
}
