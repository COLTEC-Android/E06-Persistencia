package br.bybrizael.persistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ProdutoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        ProdutoDAO produtoDAO = new ProdutoDAO(new AppDB(ProdutoActivity.this));

        EditText txt_nome = findViewById(R.id.txt_nome);
        EditText txt_valor = findViewById(R.id.txt_valor);
        Button btn_cadastrar = findViewById(R.id.btn_cadastrar);
        Button btn_listar = findViewById(R.id.btn_listar);
        TextView txtProdutosCadastrados = findViewById(R.id.txt_produtos);

        btn_cadastrar.setOnClickListener((view) -> {
            Produto produto = new Produto(txt_nome.getText().toString(), Double.parseDouble(txt_valor.getText().toString()));

            produtoDAO.insert(produto);

            Toast.makeText(getBaseContext(), "Produto cadastrado", Toast.LENGTH_LONG).show();
        });

        btn_listar.setOnClickListener((view) -> {
            List <Produto> produtos = produtoDAO.getAll();
            String lista = "";

            for(Produto atual: produtos){
                lista += atual.toString();

                txtProdutosCadastrados.setText(lista);

            }
        });

    }
}