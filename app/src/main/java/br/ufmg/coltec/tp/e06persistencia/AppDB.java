package br.ufmg.coltec.tp.e06persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by a2016951561 on 21/06/18.
 */

public class AppDB extends SQLiteOpenHelper {

    private static final String NOME_TABELA = "tabela";
    private static final String COLUNA1 = "produto";
    private static final String COLUNA2 = "preco";

    private static final String name = "AppDB.sqlite";
    private static final int version = 1;
    private static final String SCRIPT_CREATE =
            "CREATE TABLE " + NOME_TABELA + " (" +
                    COLUNA1 + " TEXT," +
                    COLUNA2 + " TEXT)";

    public AppDB(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SCRIPT_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void adicionarProduto(Produto p){ // throws Exception
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put(COLUNA1, p.getNome());
        dados.put(COLUNA2, p.getPreco());
        db.insert(NOME_TABELA,null,dados);
        db.close();
    }

    public List<Produto> getDados(){
        List<Produto> produtos = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(NOME_TABELA,null,null,null,null,null,null);
        int colunaNomeProduto = c.getColumnIndex(COLUNA1);
        int colunaPreco = c.getColumnIndex(COLUNA2);

        if(c.moveToFirst()) {
            do produtos.add(new Produto(c.getString(colunaNomeProduto),c.getString(colunaPreco)));
            while(c.moveToNext());
        }
        db.close();
        return produtos;
    }
}
