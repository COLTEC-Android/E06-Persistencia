package br.ufmg.coltec.tp.e06persistencia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ProdutoDAO dao = new ProdutoDAO(this);
        dao.getWritableDatabase();

        ListView ProductsListView = findViewById(R.id.languages_list);
        Produto produto = new Produto("teste", 2.0);
        ArrayList<Produto> lista = new ArrayList<>();
        lista.add(produto);
        ProductsListView.setAdapter(new ProdutoAdapter(this, dao, lista));

        super.onStart();

        Intent intent = new Intent(SecondActivity.this, FormActivity.class);
        startActivity(intent);
    }
}
