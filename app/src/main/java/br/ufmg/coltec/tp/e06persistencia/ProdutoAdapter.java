package br.ufmg.coltec.tp.e06persistencia;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a2016951820 on 14/06/18.
 */

public class ProdutoAdapter extends BaseAdapter {
    private List<Produto> produtos;
    private Context context;

    public ProdutoAdapter (Context context, List<Produto> produtos){
        this.context = context;
        this.produtos = produtos;
    }

    @Override
    public int getCount() {
        return this.produtos.size();
    }

    @Override
    public Object getItem(int position) {
        return this.produtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Produto p = this.produtos.get(position);

        TextView lblLanguage = new TextView(this.context);
        lblLanguage.setText("Produto: " + p.getNome() + " ---- Preço: R$" + p.getPreco());

        return lblLanguage;
    }
}
