package com.example.e06_persistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TelaProduto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_produto);

        ProdutoDAO produtoDAO = new ProdutoDAO(new AppDB(TelaProduto.this));

        Button btn_cadastrar = findViewById(R.id.btn_cadastrarProduto);
        EditText txtNome = findViewById(R.id.txt_nome);
        EditText txtPreco = findViewById(R.id.txt_preco);
        Button btn_mostrarProdutos = findViewById(R.id.btn_mostrarProduto);
        TextView txtProdutosCadastrados = findViewById(R.id.txt_produtos);

        btn_cadastrar.setOnClickListener((view) -> {
            Produto produto = new Produto(txtNome.getText().toString(), Double.parseDouble(txtPreco.getText().toString()));

            produtoDAO.insert(produto);

            Toast.makeText(getBaseContext(), "Produto cadastrado", Toast.LENGTH_LONG).show();
        });

        btn_mostrarProdutos.setOnClickListener((view) -> {
            List <Produto> produtos = produtoDAO.getAll();
            String lista = "";

            for(Produto atual: produtos){
                lista += atual.toString();

                txtProdutosCadastrados.setText(lista);

            }
        });
    }
}