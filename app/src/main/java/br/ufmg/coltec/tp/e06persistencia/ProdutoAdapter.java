package br.ufmg.coltec.tp.e06persistencia;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;


public class ProdutoAdapter extends CursorAdapter {
    public ProdutoAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nomeTextView = (TextView) view.findViewById(R.id.nome);
        nomeTextView.setText(cursor.getString
                (cursor.getColumnIndex("nome")));

        TextView precoTextView = (TextView) view.findViewById(R.id.preco);

        String preco = cursor.getString
                (cursor.getColumnIndex("preco"));

        precoTextView.setText(preco);
    }


}
