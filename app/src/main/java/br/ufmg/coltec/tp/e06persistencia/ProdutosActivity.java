package br.ufmg.coltec.tp.e06persistencia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ProdutosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        Button cadastrarBtn = findViewById(R.id.btn_cadastrar);
        EditText nomeProdutos = findViewById(R.id.nome_produtos);
        EditText valorProdutos = findViewById(R.id.preco_produtos);




    }
}