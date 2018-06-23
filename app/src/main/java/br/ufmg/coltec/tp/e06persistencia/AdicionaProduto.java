package br.ufmg.coltec.tp.e06persistencia;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdicionaProduto extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona_produto);

        Button Btn_Adiciona = findViewById(R.id.btn_add);

        Btn_Adiciona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nome = findViewById(R.id.nome_Produto);
                EditText valor = findViewById(R.id.valor_Produto);

                ProdutoDAO dao = new ProdutoDAO(getApplicationContext());

                String nomeTxt = nome.getText().toString();
                String valorTxt = valor.getText().toString();

                if (nomeTxt.equals("") || valorTxt.equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Não deixe nenhum campo em branco", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    dao.insert(new Produto(nomeTxt, Double.valueOf(valorTxt)));
                    finish();
                }

            }


        });

    }
}
