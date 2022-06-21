package com.example.e06_persistencia;

import static android.widget.Toast.*;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private Database database;

    ProdutoDAO(Database db){
        this.database = db;
    }
    public static final String CREATE_MSG = "CREATE TABLE IF NOT EXISTS produtos(\n" +
            "\tid INTEGER PRIMARY KEY AUTOINCREMENT, \t\n" +
            "\tnome TEXT NOT NULL, \n" +
            "\tpreco REAL NOT NULL \n" +
            ");";

    public void adicionarProduto(Produto p, Context context){

        SQLiteDatabase db = database.getWritableDatabase();

        try{
            ContentValues values = new ContentValues();
            values.put("nome", p.getNome());
            values.put("preco", p.getPreco());
            db.insert("produtos", null, values);
            Log.d("ProdutosADD", p.toString());
            Toast.makeText(context, "Produto adicionado com sucesso", LENGTH_SHORT).show();
        } catch(Exception e){
            Log.e("ProdutoDAO", e.getMessage());
        }finally{
            db.close();
        }
    }

    @SuppressLint("Range")
    public ArrayList<Produto> getProdutos(){
        Log.d("Produtos", "Entrouuu");
        ArrayList<Produto> produtos = new ArrayList<>();
        SQLiteDatabase db = database.getReadableDatabase();
            try {
                Cursor c = db.query("produtos", null, null, null, null, null, null);
                if(c.moveToFirst()){
                    Log.d("Produtos", "if");
                    do{
                        Produto p = new Produto(c.getString(c.getColumnIndex("nome")),
                                                c.getDouble(c.getColumnIndex("preco")));
                        Log.d("Produtos", p.toString());
                        produtos.add(p);
                    }while(c.moveToNext());
                }
            }catch (Exception e){
                Log.e("ProdutoDAO", e.getMessage());
            }finally {
                db.close();
            }
        return produtos;
    }
}
