package br.ufmg.coltec.tp.e06persistencia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class ProductActivity extends AppCompatActivity {
    br.ufmg.coltec.tp.e06persistencia.ProdutoAdapter ProdutoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        ListView petListView = (ListView) findViewById(R.id.list);
        ProdutoAdapter = new ProdutoAdapter(this, null);
        petListView.setAdapter(ProdutoAdapter);
    }
}
