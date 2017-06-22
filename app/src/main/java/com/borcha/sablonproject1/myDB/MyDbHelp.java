package com.borcha.sablonproject1.myDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.borcha.sablonproject1.myDB.dbmodel.Kateggorija;
import com.borcha.sablonproject1.myDB.dbmodel.Stavkka;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;


/**
 * Created by borcha on 02.06.17..
 */

public class MyDbHelp extends OrmLiteSqliteOpenHelper {

    private static final String DBNAME="dbsablon.db";
    private static final int DB_VER=1;

    private Dao<Kateggorija, Integer> daoKateggorija=null;
    private Dao<Stavkka, Integer> daoStavkka = null;

    public MyDbHelp(Context context) {
           super(context, DBNAME, null,DB_VER);
    }



    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource conn) {

        Log.i(MyDbHelp.class.getName(), "onCreate");
        try {
            TableUtils.createTable(conn, Kateggorija.class);
            TableUtils.createTable(conn, Stavkka.class);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource conn, int oldVersion, int newVersion) {

        Log.i(MyDbHelp.class.getName(), "onUpdate");
        try {
            TableUtils.dropTable(conn,Kateggorija.class,true);
            TableUtils.dropTable(conn,Stavkka.class,true);

            onCreate(database,conn);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public Dao<Kateggorija,Integer> getDaoKateggorija() throws SQLException {

        if(daoKateggorija==null) {
            daoKateggorija = getDao(Kateggorija.class);
        }

        return daoKateggorija;
    }



    public Dao<Stavkka,Integer> getDaoStavkka() throws SQLException {

        if(daoStavkka==null){
            daoStavkka=getDao(Stavkka.class);
        }

        return daoStavkka;
    }

    @Override
    public void close() {
        Log.i("baza","Zatvorena");
        daoStavkka=null;
        daoKateggorija=null;

        super.close();
    }


}
