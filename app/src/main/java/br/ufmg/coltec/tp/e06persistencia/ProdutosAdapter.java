package br.ufmg.coltec.tp.e06persistencia;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by a2016952894 on 14/06/18.
 */

public class ProdutosAdapter extends ArrayAdapter<Produto> {

    public ProdutosAdapter(Context context, ArrayList<Produto> produtos) {
        super(context, 0, produtos);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item, parent, false);
        }

        Produto currentProduto = getItem(position);


        TextView nomeTextView = (TextView) convertView.findViewById(R.id.nome_et);
        nomeTextView.setText(currentProduto.getNome());

        TextView precoTextView = (TextView) convertView.findViewById(R.id.preco_et);
        precoTextView.setText(String.valueOf(currentProduto.getPreco()));

        return convertView;
    }
}
