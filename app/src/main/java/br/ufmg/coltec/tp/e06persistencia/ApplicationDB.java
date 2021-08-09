package br.ufmg.coltec.tp.e06persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ApplicationDB extends SQLiteOpenHelper {

    private static final String SCHEMA_NAME = "db.sqlite";
    private static final int SCHEMA_VERSION = 1;
    private static final String PRODUCT_TABLE = "CREATE TABLE products (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name VARCHAR(100), " +
            "price double" +
            ")";

    public ApplicationDB(Context context){
        super(context, SCHEMA_NAME, null, SCHEMA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(PRODUCT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
