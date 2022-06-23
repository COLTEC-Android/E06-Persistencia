package com.example.e06_persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppDB extends SQLiteOpenHelper {
    private static String DB_NAME = "AppDB.sqlite2";
    private static ProdutoDAO produtoDAO;

    private static final int DB_VERSION = 1;

    public AppDB(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate (SQLiteDatabase db){
        db.execSQL(produtoDAO.CREATE_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
