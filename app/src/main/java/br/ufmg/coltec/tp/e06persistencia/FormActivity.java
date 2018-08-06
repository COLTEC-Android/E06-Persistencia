package br.ufmg.coltec.tp.e06persistencia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FormActivity extends AppCompatActivity {

    Button botao;
    EditText nome;
    EditText valor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        botao = findViewById(R.id.botao);
        nome = findViewById(R.id.nome);
        valor = findViewById(R.id.valor);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Produto produto = new Produto();
                produto.setNome(nome.getText().toString());
                produto.setValor(Double.parseDouble(valor.getText().toString()));
                ProdutoDAO pDAO = new ProdutoDAO(FormActivity.this);


                pDAO.insertProduct(produto);

            }
        });
    }
}
