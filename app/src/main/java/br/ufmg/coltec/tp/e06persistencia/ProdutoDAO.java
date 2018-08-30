package br.ufmg.coltec.tp.e06persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by a2016952894 on 14/06/18.
 */

public class ProdutoDAO extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DATABASE_NAME = "ProdutoDAO.sqlite";

    private static final String SCRIPT_CREATE = "CREATE TABLE " +
            "IF NOT EXISTS produtos (" +
            "produto_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "preco INTEGER NOT NULL, " +
            "nome TEXT" +
            ");";

    public ProdutoDAO(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SCRIPT_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertUser(Produto produto) {
        // 1. Obtenção do SQLiteDatabase
        SQLiteDatabase db = getWritableDatabase();

        try {
            // 2. Execução da ação
            ContentValues values = new ContentValues();
            values.put("nome", produto.getNome());
            values.put("preco", produto.getPreco());
            db.insert("produtos", null, values);
        } catch(Exception e) {
            //trata exceção
        } finally {
            // 3. Fechamento da conexão
            db.close();
        }
    }

    public ArrayList<Produto> getAll() {
        ArrayList<Produto> produtos = new ArrayList<>();   // lista que será retornada como resposta
        SQLiteDatabase db = getReadableDatabase();

        try {
            // faz a leitura dos dados do banco
            Cursor c = db.query("produtos", null, null, null,
                    null, null, null);

            if(c.moveToFirst()) {
                do {
                    // recupera os campos
                    String nome = c.getString(c.getColumnIndex("nome"));
                    Integer preco = Integer.parseInt(
                            c.getString(c.getColumnIndex("preco")));
                    int id = Integer.parseInt(c.getString(c.getColumnIndex("produto_id")));

                    // cria objeto user a partir dos dados retornados do banco
                    Produto produto = new Produto(nome, preco);
                    produto.setId(id);
                    // adiciona user na lista que será retornada
                    produtos.add(produto);
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
