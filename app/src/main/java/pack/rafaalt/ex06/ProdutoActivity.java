package pack.rafaalt.ex06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ProdutoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        ProdutoDAO dao = new ProdutoDAO(new Database(this));
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnShow = findViewById(R.id.btnMostrar);
        EditText txtNome = findViewById(R.id.txtNome);
        EditText txtPreco = findViewById(R.id.txtPreco);
        TextView produtos = findViewById(R.id.txtProdutos);

        btnAdd.setOnClickListener((view) -> {
            Produto p = new Produto(txtNome.getText().toString(), Double.parseDouble(txtPreco.getText().toString()));
            dao.adicionarProduto(p, this);
        });

        btnShow.setOnClickListener((view)->{
            Log.d("Produtos", "Func");
            String string = "";
            ArrayList<Produto> array = dao.getProdutos();

            for(Produto x : array)
                string += x.toString();

            produtos.setText(string);
        });
    }
}