package br.ufmg.coltec.tp.e06persistencia;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class BancoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banco);

        final AppDB db = new AppDB(this);
        Button btnSave = findViewById(R.id.btn_saveProduct);
        Button btnRecuperar = findViewById(R.id.btn_recoverProduct);

        final EditText nome = findViewById(R.id.txt_nome);
        final EditText preco = findViewById(R.id.txt_preco);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Produto p = new Produto(nome.getText().toString(), Double.parseDouble(preco.getText().toString()));
                db.insert(p);
                Toast.makeText(BancoActivity.this, "Produto adicionado com sucesso", Toast.LENGTH_LONG).show();
            }
        });

        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Produto> produtos = db.getAllUsers();
                ListView lv_produtos = findViewById(R.id.lista_produtos);
                lv_produtos.setAdapter(new ProdutoAdapter(BancoActivity.this, produtos));
            }
        });


    }


}
