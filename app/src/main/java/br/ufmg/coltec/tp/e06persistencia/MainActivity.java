package br.ufmg.coltec.tp.e06persistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final String APP_PREF_ID = "AppPrefs";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 0) {
            ImageView image = findViewById(R.id.img_photo);
            Bitmap photo = data.getExtras().getParcelable("data");
            image.setImageBitmap(photo);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences pref = this.getSharedPreferences(APP_PREF_ID, 0);
        SharedPreferences.Editor editor = pref.edit();

        ImageView image = findViewById(R.id.img_photo);
        Button btnTakePhoto = findViewById(R.id.btn_take_photo);
        Button btnSavePhoto = findViewById(R.id.btn_save_photo);
        Button btnForm = findViewById(R.id.btn_product_form);

        btnForm.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, FormActivity.class)));
        btnTakePhoto.setOnClickListener(v -> startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), 0));
        btnSavePhoto.setOnClickListener(v -> {
            try {
                // Se salvar outra, salva por cima
                File file = new File(this.getFilesDir(), "photo.png");
                FileOutputStream output = new FileOutputStream(file);
                Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, output);
                output.close();
            } catch (Exception e) {
                Log.d("ERRO>>>", "Erro ao salvar a foto: " + e.getMessage());
            } finally {
                Toast.makeText(MainActivity.this, " Foto salva!", Toast.LENGTH_SHORT).show();
            }
        });

        // Se não tinha nada pra recuperar, não mostra nada
        String lastAcess = pref.getString("time", "");
        if (!lastAcess.isEmpty()) {
            Toast.makeText(MainActivity.this, "Último acesso em: " + lastAcess, Toast.LENGTH_SHORT).show();
        }

        SimpleDateFormat currentTime = new SimpleDateFormat("dd/MM/yyyy - HH:mm", Locale.getDefault());
        String currentTimeString = currentTime.format(new Date());
        editor.putString("data", currentTimeString);
        editor.apply();

    }
}