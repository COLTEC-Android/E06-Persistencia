package com.example.e06_persistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static int FOTO_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button fotoBtn = findViewById(R.id.btn_tirarFoto);
        Button btnSalvarFoto = findViewById(R.id.btn_salvarFoto);
        ImageView fotoImageView = findViewById(R.id.img_foto);

        fotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(fotoIntent, FOTO_CODE);
            }
        });

        btnSalvarFoto.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Long tsLong = System.currentTimeMillis()/1000;
                String timeStamp = tsLong.toString();
                File file = new File(MainActivity.this.getBaseContext().getExternalFilesDir(null), "" + timeStamp + ".png");
                Log.d("MAIN_ACTIVITY", file.getAbsolutePath());
                try {
                    if(Environment.getExternalStorageState(file).equals(Environment.MEDIA_MOUNTED)) {
                        Log.d("MAIN_ACTIVITY", "ENTROU");
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