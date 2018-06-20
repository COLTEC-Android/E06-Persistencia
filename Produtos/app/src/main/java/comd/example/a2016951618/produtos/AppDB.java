package comd.example.a2016951618.produtos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a2016951618 on 14/06/18.
 */

public class AppDB extends SQLiteOpenHelper {

    private static String DB_NAME = "PRODUTOS";

    private static final int DB_VERSION = 1;

    private static final String SCRIPT_CREATE = "CREATE TABLE PRODUTOS (nome TEXT, preco INTEGER)";

    public AppDB(Context context) {
        super(context, DB_NAME,null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SCRIPT_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    }

    //insere
    public void insert(Produto p) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("nome", p.getNome());
            contentValues.put("preco", p.getPreco());
            sqLiteDatabase.insert("PRODUTOS", null, contentValues);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            sqLiteDatabase.close();
        }
    }

    //recupera
    public List<Produto> getAll() {
        List<Produto> produtos = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        try{
            Cursor cursor = sqLiteDatabase.query("PRODUTOS",null,null,null,null,null,null);

            if (cursor.moveToFirst()) {
                do {
                    String nome = cursor.getString(cursor.getColumnIndex("nome"));
                    int preco = cursor.getInt(cursor.getColumnIndex("preco"));

                    Produto produto = new Produto(nome, preco);

                    produtos.add(produto);

                } while (cursor.moveToNext());
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            sqLiteDatabase.close();
        }
        return produtos;
    }
}
