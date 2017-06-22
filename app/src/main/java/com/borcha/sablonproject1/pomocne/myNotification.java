package com.borcha.sablonproject1.pomocne;

import android.content.Context;
import android.support.v7.app.NotificationCompat;


/**
 * Created by borcha on 28.05.17..
 */

public class myNotification extends  NotificationCompat.Builder{

    Context cont;

    public myNotification(Context cont, String _titl, String _text) {
        super(cont);
        this.cont= cont;
        setContentTitle(_titl);
        setContentText(_text);

    }


 }
