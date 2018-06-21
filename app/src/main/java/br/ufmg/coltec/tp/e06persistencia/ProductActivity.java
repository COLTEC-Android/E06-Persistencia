package br.ufmg.coltec.tp.e06persistencia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {
    private ProdutosAdapter mProdutoAdapter;
    private ArrayList<Produto> mProdutos;
    private ListView mProdutoListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        mProdutoListView = findViewById(R.id.list);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ProdutoDAO dao = new ProdutoDAO(ProductActivity.this);
        this.atualizarLista(dao.getAll());
    }

    private void atualizarLista(ArrayList<Produto> produtos) {
        if (mProdutos == null)
            mProdutos = new ArrayList<Produto>();

        mProdutos.clear();
        mProdutos.addAll(produtos);

        // Se o adapter for null, cria o adapter, se não notifica que seu dataset teve alteração (No seu caso a lista de livros).
        if (mProdutoAdapter == null) {
            mProdutoAdapter = new ProdutosAdapter(ProductActivity.this, mProdutos);
            mProdutoListView.setAdapter(mProdutoAdapter);
        } else {
            mProdutoAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_add:
                Intent intent = new Intent(ProductActivity.this, CreateProductActivity.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
