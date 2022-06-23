package com.example.e06_persistencia;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private AppDB database;

    public ProdutoDAO(AppDB db){
        this.database = db;
    }

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS Produtos (\n" +
            "Id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "Nome TEXT NOT NULL,\n" +
            "Preco REAL NOT NULL);"
            ;


    public void insert(Produto p){
        SQLiteDatabase db = database.getWritableDatabase();

        try{
            ContentValues values = new ContentValues();
            values.put("Nome", p.getNome());
            values.put("Preco", p.getPreco());
            db.insert("Produtos", null, values);

        }catch (Exception e){
            Log.e("ProdutoDAO", e.getMessage());
        }finally {
            db.close();
        }
    }

    @SuppressLint("Range")
    public List<Produto> getAll(){

        List<Produto> produtos = new ArrayList<>();
        SQLiteDatabase db = database.getReadableDatabase();

        try{
            Cursor c = db.query("Produtos", null, null, null, null, null, null);

            if(c.moveToFirst()) {
                do {

                    String nome = c.getString(c.getColumnIndex("Nome"));
                    double preco = c.getDouble(c.getColumnIndex("Preco"));


                    Produto produto = new Produto(nome, preco);

                    produtos.add(produto);

                } while(c.moveToNext());
            }
        }catch (Exception e){
            Log.e("ProdutoDAO", e.getMessage());
        }finally {
            db.close();
        }

        return produtos;
    }
}
