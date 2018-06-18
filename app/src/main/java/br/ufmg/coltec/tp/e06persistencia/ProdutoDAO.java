package br.ufmg.coltec.tp.e06persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private static SQLiteDatabase db;
    private static appDB newBase;

    public ProdutoDAO(Context context) {
        this.newBase = new appDB(context);
    }

    public static boolean addProduto(Produto produto) {
        ContentValues values = new ContentValues();
        db = newBase.getWritableDatabase();
        long resultado;

        values.put(appDB.NAME, produto.getNome());
        values.put(appDB.PRICE, produto.getPreco());
        resultado = db.insert(appDB.TABLE, null, values);
        db.close();

        if(resultado == -1) {
            return false;
        }
        else{
            return true;
        }
    }

    public static List<Produto> getAll() {
        Cursor cursor;
        List<Produto> list = new ArrayList();
        String[] data = {newBase.ID, newBase.NAME, newBase.PRICE};
        db = newBase.getReadableDatabase();
        cursor = db.query(newBase.TABLE, data, null, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        if(cursor.getCount() > 0) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(appDB.NAME));
                Float price = cursor.getFloat(cursor.getColumnIndex(appDB.PRICE));
                Produto produto = new Produto(name, price);
                list.add(produto);
            } while (cursor.moveToNext());
        }
        return list;
    }
}
