package br.ufmg.coltec.tp.e06persistencia;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a2016951715 on 20/06/18.
 */

public class ProdutoDAO {
    void insert(Produto p){

    }
    public List<Produto> getAllUsers() {
        List<Produto> produtos = new ArrayList<>();   // lista que será retornada como resposta
        SQLiteDatabase db = getReadableDatabase();

        try {
            // faz a leitura dos dados do banco
            Cursor c = db.query("produto", null, null, null, null, null, null);

            if(c.moveToFirst()) {
                do {
                    // recupera os campos
                    String name = c.getString(c.getColumnIndex("nome"));
                    int preco = c.getInt(c.getColumnIndex("preco"));

                    // cria objeto user a partir dos dados retornados do banco
                    Produto p = new Produto(name, preco);

                    // adiciona user na lista que será retornada
                    produtos.add(p);
                } while(c.moveToNext());
            }
        } catch (Exception e) {
            // trata exceção
        } finally {
            db.close();
        }

        return produtos;
    }
}
