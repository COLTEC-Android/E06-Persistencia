package br.ufmg.coltec.tp.e06persistencia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {

    private void mostrarProdutos(){
        TextView listaProdutos = findViewById(R.id.txt_produtos);
        ProdutoDAO dao = ProdutoDAO.getInstance(AddActivity.this);

        String produtos = "";
        ArrayList<Produto> lista = (ArrayList<Produto>) dao.getAll();

        if(lista.size()<=0){
            Toast.makeText(this,"Você não adicionou produtos ainda",Toast.LENGTH_SHORT).show();
        }
        else {
            for (int i = 0; i < lista.size(); i++) {
                produtos = produtos + lista.get(i).getNome() + " -- R$" + lista.get(i).getPreco() + "\n";
            }
            listaProdutos.setText(produtos);
        }
    }

    @Override
    protected void onResume() {
        mostrarProdutos();
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final EditText nomeProduto = findViewById(R.id.txt_nome);
        final EditText precoProduto = findViewById(R.id.txt_preco);
        Button botao = findViewById(R.id.botao_add);

        final ProdutoDAO dao = ProdutoDAO.getInstance(AddActivity.this);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Produto p = new Produto(nomeProduto.getText().toString(),precoProduto.getText().toString());
                dao.insert(p);
                mostrarProdutos();
            }
        });

    }

    @Override
    protected void onDestroy() {
        //db.close(); //pode ser usado
        super.onDestroy();
    }
}
