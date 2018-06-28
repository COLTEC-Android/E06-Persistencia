package br.ufmg.coltec.tp.e06persistencia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addProduto extends AppCompatActivity {

    private ProdutoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_produto);
        dao = new ProdutoDAO(this);

        final EditText nome = findViewById(R.id.nomeProduto);
        final EditText preco = findViewById(R.id.precoProduto);
        Button btn = findViewById(R.id.btnAdd);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nome.getText().toString();
                Float price = Float.parseFloat(preco.getText().toString());

                Produtos p = new Produtos();
                p.setNome(name);
                p.setPreco(price);
                dao.insert(p);
                Toast.makeText(addProduto.this, "Produto Adicionado", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
