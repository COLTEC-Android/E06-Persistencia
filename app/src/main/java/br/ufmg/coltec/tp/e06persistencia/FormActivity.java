package br.ufmg.coltec.tp.e06persistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        DBHelper DBHelper = new DBHelper(this);
        ProdutoDAO produtoDAO = new ProdutoDAO(DBHelper);

        EditText txtName = findViewById(R.id.txt_name);
        EditText txtValue = findViewById(R.id.txt_value);
        Button btnSaveProduct = findViewById(R.id.btn_save);
        Button btnLoadProduct = findViewById(R.id.btn_load);
        TextView txtProductList = findViewById(R.id.txt_product_list);

        btnSaveProduct.setOnClickListener(v -> {
            String name = txtName.getText().toString();
            String value = txtValue.getText().toString();
            // Só insere se tiver algo digitado
            if(!name.isEmpty() && !value.isEmpty()){
                produtoDAO.insert(new Produto(name, value));
                Toast.makeText(FormActivity.this, "Produto inserido com sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(FormActivity.this, "Campos obrigatórios!", Toast.LENGTH_SHORT).show();
            }
        });
        btnLoadProduct.setOnClickListener(v -> {
            List<Produto> products = produtoDAO.getAll();
            // Cria uma string com todos os itens e coloca no TextView.
            // O ideal seria uma lista de fragments ou algo do tipo mas para apenas ver se funciona entao serve.
            StringBuilder list = new StringBuilder("Lista de produtos cadastrados\n\n");
            for (Produto p : products) {
                list.append(p.getName()).append(" - R$").append(p.getValue()).append("\n");
            }
            txtProductList.setText(list.toString());
        });

    }
}