package com.borcha.sablonproject1.myActivitys;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;

import com.borcha.sablonproject1.R;


/**
 * Created by androiddevelopment on 16.5.17..
 */

public class SettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {


    boolean toastPoruka=true;
    boolean notifikacionaPoruka=true;
    private SharedPreferences podesavnja;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.podesavanja);

         podesavnja=getPreferences(MODE_PRIVATE);
         toastPoruka=podesavnja.getBoolean("sw_notifikacija_ukljucena",true);
         notifikacionaPoruka=podesavnja.getBoolean("chb_toast_ukljucen",true);


         podesavnja.registerOnSharedPreferenceChangeListener(this);



    }





    public void setNotifikacijaPoruka(boolean ukljuceno) {
        SharedPreferences podes=getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor prepravi=podes.edit();
        prepravi.putBoolean("sw_notifikacija_ukljucena",ukljuceno);
        prepravi.commit();

    }
    public boolean jelNotifikacionaPorukaUkljucena() {

        return notifikacionaPoruka;
    }

    public void setToastPoruka(boolean ukljuceno) {
        SharedPreferences podes=getPreferences(MODE_ENABLE_WRITE_AHEAD_LOGGING);
        SharedPreferences.Editor prepravi=podes.edit();
        prepravi.putBoolean("chb_toast_ukljucen",ukljuceno);
        prepravi.commit();

    }
    public boolean jelToastPorukaUkljucena() {
        return toastPoruka;
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        if(key.equals("listaTema")){
            int vred=Integer.valueOf(sharedPreferences.getString("listTema","0"));
            Log.i("tema",String.valueOf(vred));
            setujTemuIzSettings(vred);
        }


    }


    private void setujTemuIzSettings(int _tema) {

        switch(_tema){
            case 0:
                setTheme(R.style.AppTheme);
                break;
            case 1:
                setTheme(R.style.MojaTemaPlava);
                break;

            case 2:
                setTheme(R.style.MojaTemaCrvena);
                break;

            case 3:
                setTheme(R.style.MojaTemaZelena);
                break;
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        podesavnja.unregisterOnSharedPreferenceChangeListener(this);
    }
}
