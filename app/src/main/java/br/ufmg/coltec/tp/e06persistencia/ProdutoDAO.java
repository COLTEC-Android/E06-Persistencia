package br.ufmg.coltec.tp.e06persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ProdutoDAO extends SQLiteOpenHelper {
    private static String DB_NAME = "Produtos.sqlite";
    private static final int DB_VERSION = 1;
    private static final String SCRIPT_CREATE ="CREATE TABLE IF NOT EXISTS produtos (ID INTEGER PRIMARY KEY,nome TEXT, categoria TEXT, preco REAL);";

    public ProdutoDAO(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    public void insert(Produto p){
        SQLiteDatabase db = getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put("nome", p.getNome());
            values.put("preco", p.getPreco());
            db.insert("produtos", null, values);
        } catch(Exception e) {
        } finally {
            db.close();
        }
    }
    public ArrayList<Produto> getAll() {
        ArrayList<Produto> produtos = new ArrayList<>();   // lista que será retornada como resposta
        SQLiteDatabase db = getReadableDatabase();

        try {
            Cursor c = db.query("produtos", null, null, null,
                    null, null, null);

            if(c.moveToFirst()) {
                do {
                    Integer ID = Integer.parseInt (c.getString(c.getColumnIndex("preco")));
                    String nome = c.getString(c.getColumnIndex("nome"));
                    Double preco = Double.parseDouble(c.getString(c.getColumnIndex("preco")));

                    //Produto produto = new Produto(ID, nome, preco);
                    produtos.add(new Produto(ID, nome, preco));
                } while(c.moveToNext());
            }
        } catch (Exception e) {
            // trata exceção
        } finally {
            db.close();
        }

        return produtos;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // executado no momento em que o banco é criado pela primeira vez
        db.execSQL(SCRIPT_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}