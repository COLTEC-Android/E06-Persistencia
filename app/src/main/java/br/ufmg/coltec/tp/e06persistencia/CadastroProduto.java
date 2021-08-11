package br.ufmg.coltec.tp.e06persistencia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class CadastroProduto extends AppCompatActivity {

    private ProductDAO productDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);

        EditText newProduct = findViewById(R.id.edit_product);
        EditText newPrice = findViewById(R.id.edit_price);
        Button register = findViewById(R.id.btn_register);
        Button refresh = findViewById(R.id.btn_refresh);
        ListView list = findViewById(R.id.list);
        productDAO = new ProductDAO(this);

        register.setOnClickListener(view -> {
            String name = String.valueOf(newProduct.getText());
            Double price = Double.parseDouble(String.valueOf(newPrice.getText()));
            Product product = new Product(name, price);
            productDAO.insert(product);

            Toast.makeText(this, "Produto cadastrado com sucesso", Toast.LENGTH_SHORT).show();
            newProduct.setText("");
            newPrice.setText("");
        });

        refresh.setOnClickListener(view -> {
            list.setAdapter(new ProductAdapter(this, productDAO.getAll()));

//            List<Product> products = productDAO.getAll();
//            for (Product product : products) {
//                Log.d("Products", String.format("Produto: %s | Preço: %s", product.getName(), product.getPrice()));
//            }
        });


    }
}