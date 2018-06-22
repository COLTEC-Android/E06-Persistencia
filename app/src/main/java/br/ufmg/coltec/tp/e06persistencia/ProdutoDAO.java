package br.ufmg.coltec.tp.e06persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guilherme Assis on 21/06/18.
 */

public class ProdutoDAO {

    private static SQLiteDatabase db;
    private static AppDB newBase;

    public ProdutoDAO(Context context) {
        this.newBase = new AppDB(context);
    }
    public static boolean addProduto(GetSetProdutos novoproduto) {
        ContentValues values = new ContentValues();
        db = newBase.getWritableDatabase();
        long resultado;

        values.put(AppDB.NOME, novoproduto.getNome());
        values.put(AppDB.PRECO, novoproduto.getPreco());
        resultado = db.insert(AppDB.TABLE, null, values);
        db.close();
        if(resultado == -1) {return false;}
        else{return true;}
    }
    public static List<GetSetProdutos> getAll() {
        Cursor cursor;
        List<GetSetProdutos> list = new ArrayList();
        String[] data = {newBase.ID, newBase.NOME, newBase.PRECO};
        db = newBase.getReadableDatabase();
        cursor = db.query(newBase.TABLE, data, null, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        if(cursor.getCount() > 0) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(AppDB.NOME));
                Float preco = cursor.getFloat(cursor.getColumnIndex(AppDB.PRECO));
                GetSetProdutos novoproduto = new GetSetProdutos(name, preco);
                list.add(novoproduto);
            } while (cursor.moveToNext());
        }
        return list;
    }
}