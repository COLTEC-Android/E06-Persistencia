package pack.rafaalt.ex06;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String PREF_ID = "PrefID";
    private static final int PHOTOCODE = 1;

    private Bitmap bitmapPadrao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences(PREF_ID, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String acesso = sharedPreferences.getString("ultimoAcesso", "Primeiro Acesso");
        Toast.makeText(this, acesso, Toast.LENGTH_LONG).show();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
        Date date = Calendar.getInstance().getTime();
        editor.putString("ultimoAcesso", dateFormat.format(date));

        editor.commit();

        Button btnTirar = findViewById(R.id.btnTirar);
        Button btnSalvar = findViewById(R.id.btnCarregar);
        Button btnMudar = findViewById(R.id.btnProdutos);
        ImageView imagem = findViewById(R.id.imagem);

        btnMudar.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ProdutoActivity.class);
            startActivity(intent);
        });

        btnTirar.setOnClickListener(view -> {
            Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(fotoIntent, PHOTOCODE);
        });

        btnSalvar.setOnClickListener(view -> {

            try {
                String filename = "imagem.jpeg";
                File file = new File(MainActivity.this.getExternalFilesDir(null), filename);
                FileOutputStream fout = new FileOutputStream(file);
                bitmapPadrao.compress(Bitmap.CompressFormat.PNG, 90, fout);
                Toast.makeText(this, "Imagem salva com sucesso", Toast.LENGTH_SHORT).show();
                fout.flush();
                fout.close();
                } catch (Exception e) {
                Toast.makeText(this, "ERRO", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageView foto = findViewById(R.id.imagem);
        if (requestCode == PHOTOCODE && resultCode == Activity.RESULT_OK) {
            Bitmap fotoBit = (Bitmap) data.getExtras().get("data");
            foto.setImageBitmap(fotoBit);
            bitmapPadrao = fotoBit;
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}