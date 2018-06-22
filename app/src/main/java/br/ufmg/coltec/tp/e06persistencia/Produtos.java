package br.ufmg.coltec.tp.e06persistencia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import java.util.List;

public class Produtos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);
    }
    public void addProduto(View view) {
        EditText nome = findViewById(R.id.nome);
        EditText preco = findViewById(R.id.preco);
        if(!nome.getText().toString().matches("") && !preco.getText().toString().matches("")) {
            GetSetProdutos novoproduto = new GetSetProdutos(nome.getText().toString(), Float.parseFloat(preco.getText().toString()));
            new ProdutoDAO(getBaseContext()).addProduto(novoproduto);
        }
        nome.setText("");
        preco.setText("");
    }
    public void getAll(View view) {
        List<GetSetProdutos> list = new ProdutoDAO(getBaseContext()).getAll();
        ListView listView = (ListView) findViewById(R.id.list);
        ArrayAdapter<GetSetProdutos> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }
}