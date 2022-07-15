package br.ufmg.coltec.tp.e06persistencia;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class ProdutosActivity extends AppCompatActivity {

    private List<Produto> listaprodutos;
    AppDB appDB = new AppDB(this);
    ProdutoAdapter lista;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        Button cadastrarBtn = findViewById(R.id.btn_cadastrar);
        EditText nomeProdutos = findViewById(R.id.nome_produtos);
        EditText valorProdutos = findViewById(R.id.preco_produtos);

        cadastrarBtn.setOnClickListener(view -> {
            String nome = nomeProdutos.getText().toString();
            String valor = valorProdutos.getText().toString();
            Produto produto = new Produto(nome,valor);
            ProdutoDAO produtoDAO = new ProdutoDAO(appDB);
            produtoDAO.insert(produto);

            Intent intent = new Intent(ProdutosActivity.this,MainActivity.class);
            startActivity(intent);

        });

        ListView listProdutos = this.findViewById(R.id.lista_produtos);
        lista = new ProdutoAdapter(this);
        listProdutos.setAdapter(lista);

    }
}