package br.ufmg.coltec.tp.e06persistencia;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ProdutoAdapter extends BaseAdapter {

    private ArrayList<Produto> produtos;
    private Context context;

    public ProdutoAdapter(Context context, List<Produto> list) {
        this.context = context;
        produtos = (ArrayList<Produto>) list;
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
        Produto produto = this.produtos.get(i);

        TextView lblProduto = new TextView(this.context);
        lblProduto.setText(produto.getProduto() + " ---- R$" + produto.getPreco());

        return lblProduto;
    }
}
