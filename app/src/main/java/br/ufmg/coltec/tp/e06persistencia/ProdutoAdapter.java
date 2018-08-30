package br.ufmg.coltec.tp.e06persistencia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by a2016951790 on 02/08/18.
 */

public class ProdutoAdapter extends BaseAdapter{
    private ArrayList<Produto> produtos;
    private Context context;
    private ProdutoDAO dao;



    public ProdutoAdapter(Context context, ProdutoDAO Pdao, ArrayList<Produto> produtos) {
        this.dao = Pdao;
        this.context = context;
        this.produtos = produtos;
        //Pegamos os filmes por meio do cursor na classe FilmesDAO
        //produtos = dao.retornarTodos();

    }
    @Override
    public int getCount() {
        return this.produtos.size();
    }
    @Override
    public Object getItem(int i) {
        return this.produtos.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Produto produtos = this.produtos.get(i);

        View newView = LayoutInflater.from(this.context).inflate(R.layout.products_adapter, viewGroup, false);

        // cria o componente que será carregado na lista

        TextView lblNome = newView.findViewById(R.id.lbl_nome);
        TextView lblValor = newView.findViewById(R.id.lbl_valor);


        lblNome.setText(produtos.getNome());
        lblValor.setText(produtos.getValor().toString());

        return newView;
    }

}
