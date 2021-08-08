package br.ufmg.coltec.tp.e06persistencia;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private static final int REQUEST_CODE = 1;
    private static final String APP_PREF_ID = "SP_MAIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences pref2 = this.getSharedPreferences(APP_PREF_ID, 0);
        String data = pref2.getString("chaveData", "");

        if (data != null) {
            Toast.makeText(MainActivity.this, data, Toast.LENGTH_LONG).show();
        }

        String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

        SharedPreferences pref = this.getSharedPreferences(APP_PREF_ID, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("chaveData", currentDate + " - " + currentTime);
        editor.commit();


        Button btnTirarFoto = findViewById(R.id.btn_tirar_foto);
        btnTirarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });


    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
                final ImageView foto = findViewById(R.id.foto);
                final Bitmap photo = (Bitmap) data.getExtras().get("data");
                foto.setImageBitmap(photo);

                Button btnSalvarFoto = findViewById(R.id.btn_salvar_foto);
                btnSalvarFoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String filename = "MyPhoto.png";
                        File file = new File(MainActivity.this.getExternalFilesDir(null), filename);

                        if (Environment.getExternalStorageState(file).equals(Environment.MEDIA_MOUNTED)) {
                            try {
                                FileOutputStream out = new FileOutputStream(file);
                                photo.compress(Bitmap.CompressFormat.PNG, 100, out);
                                out.flush();
                                out.close();
                                Toast.makeText(MainActivity.this, R.string.string_foto_salva, Toast.LENGTH_LONG).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        } catch (Exception ex) {
            Toast.makeText(MainActivity.this, ex.toString(),
                    Toast.LENGTH_SHORT).show();
        }

    }
}
