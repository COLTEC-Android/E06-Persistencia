package br.ufmg.coltec.tp.e06persistencia;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

public class ProdutoDAO {


    public static final String CREATE_SCRIPT = "CREATE TABLE IF NOT EXISTS produtos (\n" +
            "\tid INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\tname TEXT NOT NULL\n" + "\tvalor FLOAT NOT NULL\n"+
            ");";

    public static final String TABLE_NAME = "produtos";
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";
    public static final String VALOR_COLUMN = "valor";

    private AppDB appDB;

    public ProdutoDAO(AppDB appDB){
        this.appDB = appDB;
    }

    //faz a insecao do produto
    void insert(Produto p){

        SQLiteDatabase writeDb = this.appDB.getWritableDatabase();
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put(NAME_COLUMN, p.getName());
            contentValues.put(VALOR_COLUMN,p.getValor());
            writeDb.insert(TABLE_NAME,null,contentValues);

        }catch (Exception e){
            Log.e("Gravar dados","Erro ao gravar produto"+ e.toString());
        }finally {
            writeDb.close();
        }

    }
    //retorna os produtos cadastrados
    public List<Produto> getAll(){

        List<Produto> produtos = new ArrayList<>();
        SQLiteDatabase readDB = this.appDB.getReadableDatabase();

        try {
            Cursor res = readDB.query(TABLE_NAME,null,null,null,null,null, null);
            if (res.moveToFirst()) {
                do {
                    int id = res.getInt(res.getColumnIndex(ID_COLUMN));
                    String name = res.getString(res.getColumnIndex(NAME_COLUMN));
                    Float valor = res.getFloat(res.getColumnIndex(VALOR_COLUMN));
                    produtos.add(new Produto(id, name,valor));
                } while (res.moveToNext());
            }

        }catch (Exception e){
            produtos = null;
        }finally {
            readDB.close();
        }
        return produtos;
    }
}
