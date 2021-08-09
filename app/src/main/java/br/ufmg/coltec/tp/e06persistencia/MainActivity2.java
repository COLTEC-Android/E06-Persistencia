package br.ufmg.coltec.tp.e06persistencia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity2 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ProdutoDAO produtoDAO = new ProdutoDAO(MainActivity2.this);
        ListView listaDeProdutos = findViewById(R.id.produtos_lista);
        listaDeProdutos.setAdapter(new ProdutoAdapter(this, produtoDAO.getAll()));

        Button btnCadastrar = findViewById(R.id.btn_cadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText produtoText = findViewById(R.id.txt_produto);
                EditText precoText = findViewById(R.id.txt_preco);

                Produto p = new Produto();
                p.setProduto(produtoText.getText().toString());
                String precoString = precoText.getText().toString();
                double precoDouble = Double.parseDouble(precoString);
                p.setPreco(precoDouble);

                ProdutoDAO produtoDAO = new ProdutoDAO(MainActivity2.this);
                produtoDAO.insert(p);
                ListView listaDeProdutos = findViewById(R.id.produtos_lista);
                listaDeProdutos.setAdapter(new ProdutoAdapter(MainActivity2.this, produtoDAO.getAll()));

                produtoText.setText("");
                precoText.setText("");
            }
        });

    }
}