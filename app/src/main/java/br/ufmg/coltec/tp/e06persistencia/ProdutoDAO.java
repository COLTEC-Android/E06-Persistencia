package br.ufmg.coltec.tp.e06persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import br.ufmg.coltec.tp.e06persistencia.Produto;

/**
 * Created by a2016951790 on 02/08/18.
 */

public class ProdutoDAO extends SQLiteOpenHelper{

    // nome do banco
    private static String DB_NAME = "AppDB.sqlite";

    // versão do banco
    private static final int DB_VERSION = 1;

    // script para criação do banco
    private static final String SCRIPT_CREATE = "CREATE TABLE GERAL(produto varchar, valor double)";

    public ProdutoDAO(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SCRIPT_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }



    public void insertProduct(Produto p) {
        // 1. Obtenção do SQLiteDatabase
        SQLiteDatabase db = getWritableDatabase();

        try {
            // 2. Execução da ação
            ContentValues values = new ContentValues();
            values.put("produto", p.getNome());
            values.put("valor", p.getValor());
            db.insert("GERAL", null, values);
        } catch(Exception e) {
            //trata exceção
        } finally {
            // 3. Fechamento da conexão
            db.close();
        }
    }

}
