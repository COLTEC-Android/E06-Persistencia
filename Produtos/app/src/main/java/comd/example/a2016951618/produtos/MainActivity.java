package comd.example.a2016951618.produtos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AppDB appDB = new AppDB(this);

        final EditText lbl_nome = findViewById(R.id.lbl_nome);
        final EditText lbl_preco = findViewById(R.id.lbl_preco);
        Button btn_salvar = findViewById(R.id.btn_salvar);
        Button btn_carregar = findViewById(R.id.btn_carregar);

        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Produto produto = new Produto(lbl_nome.getText().toString(), Integer.parseInt(lbl_preco.getText().toString()));
                appDB.insert(produto);
                Toast.makeText(getApplicationContext(), "Salvo.", Toast.LENGTH_LONG).show();
            }
        });

        btn_carregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<Produto> produtos = appDB.getAll();

                Toast.makeText(getApplicationContext(), produtos.get(produtos.size()-1).getNome(), Toast.LENGTH_LONG).show();

                ListView produtos_ListView = findViewById(R.id.produtos_list);

                produtos_ListView.setAdapter(new ProdutoAdapter(MainActivity.this, produtos));

                //Toast.makeText(getApplicationContext(), , Toast.LENGTH_LONG).show();
            }
        });
    }
}
