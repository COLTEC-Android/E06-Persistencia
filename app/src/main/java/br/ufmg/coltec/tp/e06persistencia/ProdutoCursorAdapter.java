package br.ufmg.coltec.tp.e06persistencia;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by a2016952894 on 14/06/18.
 */

public class ProdutoCursorAdapter extends CursorAdapter {
    public ProdutoCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nomeTextView = (TextView) view.findViewById(R.id.nome_et);
        nomeTextView.setText(cursor.getString
                (cursor.getColumnIndex("nome")));

        TextView precoTextView = (TextView) view.findViewById(R.id.preco_et);

        String preco = cursor.getString
                (cursor.getColumnIndex("preco"));

        precoTextView.setText(preco);
    }


}
