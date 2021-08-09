package br.ufmg.coltec.tp.e06persistencia;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.ufmg.coltec.tp.e06persistencia.adapter.ProductAdapter;
import br.ufmg.coltec.tp.e06persistencia.dao.ProductDAO;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ApplicationDB applicationDB = new ApplicationDB(this);
        ProductDAO productDAO = new ProductDAO(applicationDB);

        Button btnListProducts = findViewById(R.id.buttonListProducts);
        Button btnSaveProduct = findViewById(R.id.buttonSaveProduct);
        EditText productName = findViewById(R.id.productNameField);
        EditText producPrice = findViewById(R.id.produtPriceField);
        ListView productList = findViewById(R.id.productList);

        btnSaveProduct.setOnClickListener(view -> {
            Log.d("PRODUCT DATA", productName.getText() + ":" + producPrice.getText());
            productDAO.insert(new Product(productName.getText().toString(), Double.parseDouble(producPrice.getText().toString())));
            Toast.makeText(this, "PRODUTO CADASTRADO!", Toast.LENGTH_LONG).show();
        });

        btnListProducts.setOnClickListener(view -> {
            List<Product> products = productDAO.getAll();

            for(Product p : products){
                Log.d("LIST", "NAME: "+p.getName()+"/PRICE: "+p.getPrice());
            }

            productList.setAdapter(new ProductAdapter(this, products));
        });
    }
}