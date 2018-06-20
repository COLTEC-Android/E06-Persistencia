package br.ufmg.coltec.tp.e06persistencia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a2016951715 on 20/06/18.
 */

public class AppDB {

    // nome do banco
    private static final String DB_NAME = "Produtos.db";
    // versão do banco
    private static final int DB_VERSION = 1;
    // script para criação do banco
    private static final String CREATE_TABLE = "CREATE TABLE Produtos(ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT NOT NULL,  INTEGER NOT NULL";

    public AppDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // executado no momento em que o banco é criado pela primeira vez
        db.execSQL(CREATE_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }



}


