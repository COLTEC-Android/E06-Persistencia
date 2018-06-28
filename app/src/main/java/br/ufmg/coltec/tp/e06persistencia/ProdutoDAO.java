package br.ufmg.coltec.tp.e06persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mtgo0 on 28/06/2018.
 */

public class ProdutoDAO extends SQLiteOpenHelper {

    // nome do banco
    private static String DB_NAME = "AppDB.sqlite";

    // versão do banco
    private static final int DB_VERSION = 1;

    // script para criação do banco
    private static final String SCRIPT_CREATE = "CREATE TABLE IF NOT EXISTS produtos (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, preco REAL);";


    public ProdutoDAO(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SCRIPT_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert(Produtos p){

        SQLiteDatabase db = getWritableDatabase();

        try {
            // 2. Execução da ação
            ContentValues values = new ContentValues();
            values.put("nome", p.getNome());
            values.put("preco", p.getPreco());

            db.insert("produtos", null, values);
        } catch(Exception e) {
            //trata exceção
            throw e;
        } finally {
            // 3. Fechamento da conexão
            db.close();
        }
    }

    public List<Produtos> getAll(){

        List<Produtos> produtos = new ArrayList<>();   // lista que será retornada como resposta
        SQLiteDatabase db = getReadableDatabase();

        try {
            // faz a leitura dos dados do banco
            Cursor c = db.query("produtos", null, null, null, null, null, null);

            if(c.moveToFirst()) {
                do {
                    // recupera os campos
                    String name = c.getString(c.getColumnIndex("nome"));
                    String preco = c.getString(c.getColumnIndex("preco"));

                    // cria objeto user a partir dos dados retornados do banco
                    Produtos p = new Produtos();
                    p.setNome(name);
                    p.setPreco(Float.parseFloat(preco));

                    // adiciona user na lista que será retornada
                    produtos.add(p);
                } while(c.moveToNext());
            }
        } catch (Exception e) {
            // trata exceção
            throw e;
        } finally {
            db.close();
        }

        return produtos;
    }
}
