package com.borcha.sablonproject1.myActivitys;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.borcha.sablonproject1.R;


/**
 * Created by androiddevelopment on 16.5.17..
 */

public class SettingsActivity extends PreferenceActivity {


    boolean toastPoruka=true;
    boolean notifikacionaPoruka=true;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.podesavanja);

         SharedPreferences podesavnja=getPreferences(MODE_PRIVATE);
         toastPoruka=podesavnja.getBoolean("sw_notifikacija_ukljucena",true);
         notifikacionaPoruka=podesavnja.getBoolean("chb_toast_ukljucen",true);
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


}
