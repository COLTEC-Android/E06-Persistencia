package br.ufmg.coltec.tp.e06persistencia.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.ufmg.coltec.tp.e06persistencia.Product;

public class ProductDAO {

    private static final String TABLE_NAME = "products";

    private SQLiteOpenHelper helper;

    public ProductDAO(SQLiteOpenHelper helper){
        this.helper = helper;
    }

    public void insert(Product p){
        SQLiteDatabase db = helper.getWritableDatabase();

        try{
            ContentValues params = new ContentValues();
            params.put("name", p.getName());
            params.put("price", p.getPrice());

            db.insert(TABLE_NAME, null, params);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }
    }

    public List<Product> getAll(){
        List<Product> productsList = new ArrayList<>();
        SQLiteDatabase db = this.helper.getReadableDatabase();

        try {
            Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);

            if(c.moveToFirst()){
                do {
                    String name = c.getString(c.getColumnIndex("name"));
                    Double price = c.getDouble(c.getColumnIndex("price"));

                     productsList.add(new Product(name, price));
                }while (c.moveToNext());
            }
        }catch (Exception e){

        }finally {
            db.close();
        }

        return productsList;
    }
}
