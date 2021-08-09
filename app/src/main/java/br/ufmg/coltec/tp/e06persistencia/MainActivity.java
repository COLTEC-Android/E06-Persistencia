package br.ufmg.coltec.tp.e06persistencia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    private Bitmap imageBitmap;
    private static final String APP_PREF_ID = "MyAppPrefID";
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = MainActivity.this.getSharedPreferences(APP_PREF_ID, 0);
        editor = pref.edit();
        setLastAcess(new Date());

        Button btnCamera = findViewById(R.id.buttonCamera);
        Button btnSavePhoto = findViewById(R.id.buttonSavePhoto);

        btnCamera.setOnClickListener(view -> {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        });
        btnSavePhoto.setOnClickListener(view -> {
            File file = new File(MainActivity.this.getExternalFilesDir(null), "photo.png");

            if(Environment.getExternalStorageState(file).equals(Environment.MEDIA_MOUNTED)){
                try (FileOutputStream out = new FileOutputStream(file)) {
                    imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                    out.flush();
                    out.close();
                    Toast.makeText(MainActivity.this, "Foto salva.", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageView photo = findViewById(R.id.imageView);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            photo.setImageBitmap(imageBitmap);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(MainActivity.this, "Last acess: "+getLastAcess(), Toast.LENGTH_LONG).show();
    }

    private void setLastAcess(Date currentDate){
        String date = new SimpleDateFormat("dd/MM/yyyy").format(currentDate);
        String hour = new SimpleDateFormat("HH:mm").format(currentDate);
        editor.putString("lastAcess", date + " - " + hour);
        editor.commit();
    }

    private String getLastAcess(){
        String lastAcess = pref.getString("lastAcess", "");
        return lastAcess;
    }
}
