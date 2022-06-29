package br.bybrizael.persistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static int FOTO_CODE = 1;
    private static final String APP_PREF_ID = "AppPrefID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_tirar_foto = findViewById(R.id.btn_tirar_foto);
        Button btn_salvar_foto = findViewById(R.id.btn_salvar_foto);
        Button btn_produtos = findViewById(R.id.btn_produtos);
        ImageView fotoImageView = findViewById(R.id.img_foto);

        btn_produtos.setOnClickListener((view)->{
            Intent intent = new Intent(MainActivity.this, ProdutoActivity.class);
            startActivity(intent);
        });

        //Salvando ultimo acesso com SharedPreferences
        SharedPreferences sharedpreferences = MainActivity.this.getBaseContext().getSharedPreferences(APP_PREF_ID, 0);
        String ultimo_acesso = sharedpreferences.getString("data_e_hora", "");

        if (!ultimo_acesso.isEmpty()){
            Toast.makeText(getBaseContext(), "Último acesso: " + ultimo_acesso, Toast.LENGTH_LONG).show();
        }

        SimpleDateFormat data_atual = new SimpleDateFormat("dd/MM/yyyy - HH:mm", Locale.getDefault());
        String data_e_hora = data_atual.format(new Date());
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("data_e_hora", data_e_hora);


        editor.commit();

        //Salvando fotos no aparelho
        btn_tirar_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(fotoIntent, FOTO_CODE);
            }
        });

        btn_salvar_foto.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Long tslong = System.currentTimeMillis()/1000;
                String timeStamp = tslong.toString();
                File file = new File(MainActivity.this.getBaseContext().getExternalFilesDir(null), "" + timeStamp + ".png");
                Log.d("MAIN_ACTIVITY", file.getAbsolutePath());
                try {
                    if(Environment.getExternalStorageState(file).equals(Environment.MEDIA_MOUNTED)) {
                        Log.d("MAIN_ACTIVITY", "OK");
                        FileOutputStream out = new FileOutputStream(file);
                        BitmapDrawable drawable = (BitmapDrawable) fotoImageView.getDrawable();
                        Bitmap bitmap = drawable.getBitmap();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                    }
                } catch(Exception e) {
                    Log.e("File", "Erro ao gravar arquivo:" + e.toString());
                }

            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView imageView = findViewById(R.id.img_foto);

        if (requestCode == FOTO_CODE && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);

        }
    }
}