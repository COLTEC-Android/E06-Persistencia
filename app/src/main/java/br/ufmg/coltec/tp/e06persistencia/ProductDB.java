package br.ufmg.coltec.tp.e06persistencia;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ProductDB extends SQLiteOpenHelper {

    private static String PRODUCT_DB = "db.sqlite";
    private static final int DB_VERSION = 1;
    private static final String SQL_CREATE =
            "CREATE TABLE products (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "product TEXT, " +
                    "price REAL" +
                    ")";

    public ProductDB(Context context){
        super (context, PRODUCT_DB, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
            sqLiteDatabase.execSQL(SQL_CREATE);

        }catch (SQLException e){
            Log.e("DB_ERROR", e.getLocalizedMessage() );
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
