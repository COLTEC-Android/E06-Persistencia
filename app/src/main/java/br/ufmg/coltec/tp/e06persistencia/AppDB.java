package br.ufmg.coltec.tp.e06persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a2016951820 on 14/06/18.
 */
public class AppDB extends SQLiteOpenHelper {

    private static String DB_NOME = "Produtos";
    private static final int DB_VERSAO = 1;
    private static final String SCRIPT_CREATE = "CREATE TABLE Produtos (nome TEXT, preco REAL)";

    public AppDB(Context context){
        super(context, DB_NOME, null, DB_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SCRIPT_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(Produto p){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues cv = new ContentValues();
            cv.put("nome", p.getNome());
            cv.put("preco", p.getPreco());
            db.insert("Produtos", null, cv);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }
    }
    public List<Produto> getAllUsers() {
        List<Produto> produtos = new ArrayList<>();   // lista que será retornada como resposta
        SQLiteDatabase db = getReadableDatabase();

        try {
            // faz a leitura dos dados do banco
            Cursor c = db.query("Produtos", null, null, null, null, null, null);

            if(c.moveToFirst()) {
                do {
                    String nome = c.getString(c.getColumnIndex("nome"));
                    Double preco = c.getDouble(c.getColumnIndex("preco"));

                    Produto p = new Produto(nome, preco);

                    // adiciona user na lista que será retornada
                    produtos.add(p);
                } while(c.moveToNext());
            }
        } catch (Exception e) {
            // trata exceção
        } finally {
            db.close();
        }

        return produtos;
    }
}
