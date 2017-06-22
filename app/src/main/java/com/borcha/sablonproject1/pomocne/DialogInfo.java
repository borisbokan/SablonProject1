package com.borcha.sablonproject1.pomocne;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by borcha on 16.06.17..
 */

public class DialogInfo extends AlertDialog.Builder{

    public DialogInfo(Context context) {
        super(context);

    }

    public static DialogInfo newInstance(Context context,String _naslov,String _tekst) {
           DialogInfo dialog=new DialogInfo(context);
           dialog.setTitle(_naslov);
           dialog.setMessage(_tekst);

            dialog.setPositiveButton("Zatvori", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });


            dialog.show();

           return dialog;

    }

}
