package br.ufmg.coltec.tp.e06persistencia;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class Produtos extends Activity {
    ProdutosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        ListView ProdutosListView = findViewById(R.id.lista_produtos);
        adapter = new ProdutosAdapter(this);
        ProdutosListView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Produtos.this.adapter.notifyDataSetChanged();
    }
}