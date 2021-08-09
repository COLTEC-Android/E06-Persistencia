package br.ufmg.coltec.tp.e06persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    public static final String TABLE_NAME = "products";
    public static final String PRODUCT_NAME_COLUMN = "name";
    public static final String PRODUCT_VALUE_COLUMN = "value";

    private final SQLiteOpenHelper helper;

    public ProdutoDAO(SQLiteOpenHelper helper){
        this.helper = helper;
    }

    public void insert(Produto p) {
        SQLiteDatabase db = this.helper.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(PRODUCT_NAME_COLUMN, p.getName());
            values.put(PRODUCT_VALUE_COLUMN, p.getValue());
            long id = db.insert(TABLE_NAME, null, values);
            Log.d("DEBUG>>>", "Produto inserido com ID: " + id);
        } catch (Exception e) {
            Log.d("ERRO>>>", "Erro ao inserir um produto: " + e.getMessage());
        } finally {
            db.close();
        }
    }

    List<Produto> getAll() {
        SQLiteDatabase db = this.helper.getReadableDatabase();
        List<Produto> products = new ArrayList<>();
        try {
            Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null,null);
            if(cursor.moveToFirst()) {
                do{
                    String name = cursor.getString(cursor.getColumnIndex(PRODUCT_NAME_COLUMN));
                    String value = cursor.getString(cursor.getColumnIndex(PRODUCT_VALUE_COLUMN));
                    products.add(new Produto(name, value));
                }while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            Log.d("ERRO>>>", "Erro ao recuperar produtos: " + e.getMessage());
        } finally {
            db.close();
        }
        return products;
    }
}
