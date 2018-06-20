package br.ufmg.coltec.tp.e06persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;


public class ProdutoDAO extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DATABASE_NAME = "ProdutoDAO.sqlite";

    private static final String SCRIPT_CREATE = "CREATE TABLE" +
            "IF NOT EXISTS produtos (" +
            "produto_id INTEGER PRIMARY AUTOINCREMENT," +
            "preco INTEGER NOT NULL" +
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
        SQLiteDatabase db = getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put("nome", produto.getNome());
            values.put("preco", produto.getPreco());
            db.insert("users", null, values);
        }

        catch(Exception e) {
            //Exceção
        }

        finally {
            db.close();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Produto> getAll() {
        List<Produto> produtos = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        try {
            // Leitura dos dados do banco
            Cursor c = db.query("users", null, null, null, null, null, null);

            if(c.moveToFirst()) {
                do {
                    String nome = c.getString(c.getColumnIndex("nome"));
                    Integer preco = Integer.parseInt(
                            c.getString(c.getColumnIndex("preco")));
                    int id = Integer.parseInt(c.getString(c.getColumnIndex("id")));

                    Produto produto = new Produto();
                    produto.setNome(nome);
                    produto.setPreco(preco);
                    produto.setId(id);

                    produtos.add(produto);
                } while(c.moveToNext());
            }
        }

        catch (Exception e) {
            // Exceção
        }

        finally {
            db.close();
        }

        return produtos;
    }
}
