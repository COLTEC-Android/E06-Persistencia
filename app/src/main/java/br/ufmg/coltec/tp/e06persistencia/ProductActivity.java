package br.ufmg.coltec.tp.e06persistencia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class ProductActivity extends AppCompatActivity {
    ProdutoCursorAdapter mProdutoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        ListView petListView = (ListView) findViewById(R.id.list);
        mProdutoAdapter = new ProdutoCursorAdapter(this, null);
        petListView.setAdapter(mProdutoAdapter);
    }
}
