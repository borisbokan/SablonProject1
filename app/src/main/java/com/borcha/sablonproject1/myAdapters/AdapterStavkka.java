package com.borcha.sablonproject1.myAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.borcha.sablonproject1.R;
import com.borcha.sablonproject1.myDB.dbmodel.Stavkka;
import java.util.List;


/**
 * Created by androiddevelopment on 13.6.17..
 */

public class AdapterStavkka extends ArrayAdapter<Stavkka> {

    LayoutInflater lyInflater;
    Context cont;
    TextView txvNaziv,txvGodinaRodjenja;

    public AdapterStavkka(Context context, List<Stavkka> _stavka) {
        super(context, R.layout.lista_stavkka,_stavka);
        cont=context;
    }


    @NonNull
    @Override
    public View getView(int position, View vi,  ViewGroup parent) {
        lyInflater=(LayoutInflater)cont.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        vi=lyInflater.inflate(R.layout.lista_stavkka,null);
        Stavkka Stavkka=getItem(position);


        txvNaziv=(TextView)vi.findViewById(R.id.txvNazivStavkka_lista);
        txvGodinaRodjenja=(TextView)vi.findViewById(R.id.txtOpisStavkka_lista);

        txvNaziv.setText(Stavkka.getNaziv());
        txvGodinaRodjenja.setText(Stavkka.getOpis().toString());


        return vi;
    }
}
