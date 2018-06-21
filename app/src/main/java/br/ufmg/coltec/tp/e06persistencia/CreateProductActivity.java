package br.ufmg.coltec.tp.e06persistencia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateProductActivity extends AppCompatActivity {
    private Button mSaveBtn;
    private EditText mNameEditText;
    private EditText mPriceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_product);

        mSaveBtn = findViewById(R.id.save_btn);
        mNameEditText = findViewById(R.id.nome_et);
        mPriceText = findViewById(R.id.preco_et);

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Produto p = new Produto(
                        mNameEditText.getText().toString(),
                        Integer.parseInt(mPriceText.getText().toString()));

                ProdutoDAO dao = new ProdutoDAO(CreateProductActivity.this);
                dao.insertUser(p);
                finish();
            }
        });
    }
}
