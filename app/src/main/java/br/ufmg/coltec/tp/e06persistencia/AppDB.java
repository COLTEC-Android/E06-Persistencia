package br.ufmg.coltec.tp.e06persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppDB extends SQLiteOpenHelper {

    private static String DB_NAME = "bancoAPP";
    private static final int DB_VERSION = 3;

    public AppDB(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ProdutoDAO.CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
