package com.borcha.sablonproject1.myActivitys;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.widget.Toast;

import com.borcha.sablonproject1.R;


/**
 * Created by androiddevelopment on 16.5.17..
 */

public class SettingsActivity extends PreferenceActivity  {


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

        ListPreference lista=(ListPreference)findPreference("listaTema");
        lista.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                 int vred=Integer.valueOf(preference.getSharedPreferences().getString("listaTema","0"));
                setujTemuIzSettings(vred);
                return false;
            }
        });

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


    private void setujTemuIzSettings(int _tema) {

        switch(_tema){
            case 0:
                getApplication().setTheme(R.style.AppTheme);
                break;
            case 1:
                getApplication().setTheme(R.style.MojaTemaPlava);
                break;

            case 2:
                getApplication().setTheme(R.style.MojaTemaCrvena);
                break;

            case 3:
                getApplication().setTheme(R.style.MojaTemaZelena);
                break;
        }


    }

}
