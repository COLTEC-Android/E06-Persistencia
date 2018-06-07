package br.ufmg.coltec.tp.e06persistencia;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static int PHOTO_CODE = 0;
    private static Bitmap photo;
    private static final String APP_PREF_ID = "IDdoApp";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Recuperando último acesso
        SharedPreferences pref = getApplicationContext().getSharedPreferences(APP_PREF_ID, 0);
        String ultimoAcesso = pref.getString("data", "");
        if(!ultimoAcesso.equals("")){
            Toast.makeText(getApplicationContext(), "Último Acesso: "+ultimoAcesso, Toast.LENGTH_LONG).show();
        }

        // Armazenando data atual
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
        String agora = sdf.format(new Date());

        SharedPreferences.Editor editor = pref.edit();
        editor.putString("data", agora);
        editor.commit();


        // Buttons Listeners

        Button btn_takepic = findViewById(R.id.btn_takepic);
        Button btn_save = findViewById(R.id.btn_save);

        btn_takepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, PHOTO_CODE);
                TextView texto = findViewById(R.id.texto);
                texto.setText("");

            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(getApplicationContext().getExternalFilesDir(null), "foto.png");
                FileOutputStream out = null;
                try {
                    out = new FileOutputStream(file);
                    photo.compress(Bitmap.CompressFormat.PNG, 100, out);
                    Toast.makeText(getApplicationContext(), "Imagem salva", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (out != null) {
                            out.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageView imageView = findViewById(R.id.img);

        if (requestCode == PHOTO_CODE && resultCode == Activity.RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
    }
}
