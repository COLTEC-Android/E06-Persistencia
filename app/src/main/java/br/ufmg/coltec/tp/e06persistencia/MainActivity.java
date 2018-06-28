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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final String APP_PREF_ID = "MeuAppPrefID";
    private static int FOTO_CODE = 1;
    private Bitmap bitmap = null;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView preview = findViewById(R.id.imagePreview);

        if (requestCode == FOTO_CODE && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            bitmap = photo;
            preview.setImageBitmap(photo);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button capture = findViewById(R.id.capture);
        Button save = findViewById(R.id.save);
        Button btnp = findViewById(R.id.btnProduto);

        SharedPreferences pref = getBaseContext().getSharedPreferences(APP_PREF_ID, 0);
        SharedPreferences.Editor editor = pref.edit();
        String data = pref.getString("Data", "");

        btnp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, addProduto.class);
                startActivity(intent);
            }
        });

        if(!data.equals("")){
            Toast.makeText(getBaseContext(), data, Toast.LENGTH_LONG).show();
        }

        final Date currentTime = Calendar.getInstance().getTime();
        editor.putString("Data", currentTime.toString());
        editor.commit();

        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(fotoIntent, FOTO_CODE);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bitmap != null){
                    String filename = "Foto.jpg";
                    Toast.makeText(getBaseContext(), filename, Toast.LENGTH_SHORT).show();
                    File dest = new File(MainActivity.this.getExternalFilesDir(null), filename);
                    try {
                        FileOutputStream out = new FileOutputStream(dest);
                        bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
                        out.flush();
                        out.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else{
                    Toast.makeText(getBaseContext(), "Tire Uma Foto Antes De Tentar Salva-la", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
