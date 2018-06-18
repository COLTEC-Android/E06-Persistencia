package br.ufmg.coltec.tp.e06persistencia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
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
            Produto produto = new Produto(nome.getText().toString(), Float.parseFloat(preco.getText().toString()));
            new ProdutoDAO(getBaseContext()).addProduto(produto);
        }
        nome.setText("");
        preco.setText("");
    }

    public void getAll(View view) {
        List<Produto> list = new ProdutoDAO(getBaseContext()).getAll();
        ListView listView = (ListView) findViewById(R.id.list);
        ArrayAdapter<Produto> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }
}
