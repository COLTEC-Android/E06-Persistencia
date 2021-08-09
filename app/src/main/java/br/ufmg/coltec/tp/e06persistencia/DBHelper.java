package br.ufmg.coltec.tp.e06persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String SCHEMA_NAME = "db.sqlite";
    private static final int SCHEMA_VERSION = 1;
    private static final String CREATE_TABLE_PRODUCTS = "CREATE TABLE "+ ProdutoDAO.TABLE_NAME + " (" +
                                                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                    ProdutoDAO.PRODUCT_NAME_COLUMN + " VARCHAR(100), " +
                                                    ProdutoDAO.PRODUCT_VALUE_COLUMN + " VARCHAR(20)" +
                                                    ")";

    public DBHelper(Context context) {
        super(context, SCHEMA_NAME, null, SCHEMA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_PRODUCTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
