package br.ufmg.coltec.tp.e06persistencia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class Produtos extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        ListView ProdutosListView = findViewById(R.id.lista_produtos);

        ProdutosAdapter adapter = new ProdutosAdapter(this);
        ProdutosListView.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                // inicia a activity Cadastro_Produto quando o usuário clica no botão correspondente
                Intent intent = new Intent(Produtos.this, AdicionaProduto.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_produto, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();


        ListView ProdutosListView = findViewById(R.id.lista_produtos);
        ProdutosAdapter adapter = new ProdutosAdapter(this);
        ProdutosListView.setAdapter(adapter);

    }
}


