package br.ufmg.coltec.tp.e06persistencia;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    private static int FOTO_CODE = 1;
    private static final String APP_PREF_ID = "MeuAppPrefID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton img_preview_btn = findViewById(R.id.img_preview);

        leituraDataEHora();
        escritaDataEHora();

        img_preview_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(fotoIntent, FOTO_CODE);

            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ImageButton imageButton = findViewById(R.id.img_preview);
        Button btn_salvar = findViewById(R.id.btn_salvar);

        if(requestCode == FOTO_CODE && resultCode == Activity.RESULT_OK){
            final Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageButton.setImageBitmap(photo);

            btn_salvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Escrita(photo);
                }
            });
        }
    }

    protected void Escrita(Bitmap bmp) {

        File filename = new File(getApplicationContext().getExternalFilesDir(null), "photo.png");

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filename);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                    Toast.makeText(getApplicationContext(), "Imagem salva no dispositivo.", Toast.LENGTH_LONG).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected void escritaDataEHora() {
        String dataAtual = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(System.currentTimeMillis());

        SharedPreferences pref = getApplicationContext().getSharedPreferences(APP_PREF_ID, 0);

        SharedPreferences.Editor editor = pref.edit();
        editor.putString("chaveStr", dataAtual);

        editor.commit();
    }

    protected void leituraDataEHora () {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(APP_PREF_ID, 0);

        String valueStr = pref.getString("chaveStr", "");

        if (!(valueStr.equals(""))) {
            Toast.makeText(getApplicationContext(), "Último acesso: " + valueStr, Toast.LENGTH_LONG).show();
        }

    }
}
