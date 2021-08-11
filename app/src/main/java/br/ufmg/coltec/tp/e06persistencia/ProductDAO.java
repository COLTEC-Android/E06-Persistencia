package br.ufmg.coltec.tp.e06persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private ProductDB productDB;
    private SQLiteDatabase db;

    public ProductDAO(Context context) {
        productDB = new ProductDB(context);
    }

    public void insert (Product product) {

        db = productDB.getWritableDatabase();

        try{
            ContentValues values = new ContentValues();
            values.put("product", product.getName());
            values.put("price", product.getPrice());

            db.insert("products", null, values);

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        db = productDB.getReadableDatabase();

        try {
            Cursor c = db.query("products", null, null, null, null, null, null);

            if(c.moveToFirst()) {
                do {
                    String name = c.getString(c.getColumnIndex("product"));
                    Double price = c.getDouble(c.getColumnIndex("price"));

                    Product product = new Product();
                    product.setName(name);
                    product.setPrice(price);
                    products.add(product);

                } while(c.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            db.close();
        }

        return products;
    }
}


