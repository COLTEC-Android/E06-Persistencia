package br.ufmg.coltec.tp.e06persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class appDB extends SQLiteOpenHelper {

    private static String DB_NAME = "AppDB.sqlite";
    private static final int DB_VERSION = 1;
    public static final String TABLE = "Produtos";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PRICE = "price";

    public appDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE + "("
                + ID + " integer primary key autoincrement,"
                + NAME + " text,"
                + PRICE + " text"
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}
