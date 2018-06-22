package br.ufmg.coltec.tp.e06persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Guilherme Assis on 21/06/18.
 */

public class AppDB extends SQLiteOpenHelper {
    // nome do banco
    private static String DB_NAME = "AppDB.sqlite";
    // versão do banco
    private static final int DB_VERSION = 1;
    public static final String TABLE = "Produtos";
    public static final String ID = "id";
    public static final String NOME = "name";
    public static final String PRECO = "preco";

    public AppDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE + "("
                + ID + " integer primary key autoincrement,"
                + NOME + " text,"
                + PRECO + " text"
                +")";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}