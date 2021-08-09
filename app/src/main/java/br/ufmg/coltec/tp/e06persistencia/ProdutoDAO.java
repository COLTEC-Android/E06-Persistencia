package br.ufmg.coltec.tp.e06persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO extends SQLiteOpenHelper {

    private static String DB_NAME = "ProdutoDAO.sqlite";
    private static final int DB_VERSION = 1;
    private static final String SCRIPT_CREATE =
            "CREATE TABLE produtos (" +
                    "_id integer primary key autoincrement," +
                    "produto text," +
                    "preco double" +
                    ")";

    public ProdutoDAO(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SCRIPT_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS produtos");
        onCreate(sqLiteDatabase);
    }

    void insert(Produto p) {
        SQLiteDatabase db = getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put("produto", p.getProduto());
            values.put("preco", p.getPreco());
            db.insert("produtos", null, values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    List<Produto> getAll() {
        List<Produto> produtos = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        try {
            Cursor c = db.query("produtos", null, null, null, null, null, null);

            if(c.moveToFirst()) {
                do {
                    String produto = c.getString(c.getColumnIndex("produto"));
                    double preco = c.getDouble(c.getColumnIndex("preco"));

                    Produto p = new Produto();
                    p.setProduto(produto);
                    p.setPreco(preco);

                    produtos.add(p);
                } while(c.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        return produtos;
    }
}
