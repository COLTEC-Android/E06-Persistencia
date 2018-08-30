package br.ufmg.coltec.tp.e06persistencia;

import java.util.List;
import android.content.Context;

/**
 * Created by a2016951561 on 21/06/18.
 */

public class ProdutoDAO {

    private static ProdutoDAO instance;
    private static Context context;

    public static ProdutoDAO getInstance(Context c) {
        if(instance == null) {
            instance = new ProdutoDAO();
            context = c;
        }
        return instance;
    }

    private ProdutoDAO(){
        //nada
    }

    void insert(Produto p){
        AppDB db = new AppDB(context);
        db.adicionarProduto(p);
    }

    List<Produto> getAll(){
        AppDB db = new AppDB(context);
        return db.getDados();
    }
}
