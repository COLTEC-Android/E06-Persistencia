package br.ufmg.coltec.tp.e06persistencia;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private final static int FOTO_CODE = 1;
    private static final String APP_PREF_ID = "MeuAppPrefID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences pref = this.getSharedPreferences(APP_PREF_ID, 0);
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");
        String hour = format.format(new Date());
        final String defValueHoraIndefinida = "none";
        final String keyHora = "acesso";
        String hourAnt = pref.getString(keyHora, defValueHoraIndefinida);
        if(!hourAnt.equals(defValueHoraIndefinida)) Toast.makeText(this,hourAnt,Toast.LENGTH_LONG).show();
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(keyHora, hour);
        editor.commit();


        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageView imageView = findViewById(R.id.image);

        if (requestCode == FOTO_CODE && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
    }

    public void editImage(View view) {
        Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(fotoIntent, FOTO_CODE);
    }

    public void saveImage(View view) {
        ImageView imageView = findViewById(R.id.image);
        BitmapDrawable bitmapDrawable = ((BitmapDrawable) imageView.getDrawable());
        Bitmap bitmap = bitmapDrawable.getBitmap();
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        path.mkdirs();
        File file = new File(path, "Foto.png");
        FileOutputStream out = null;
        try {
            if(Environment.getExternalStorageState(file).equals(Environment.MEDIA_MOUNTED)) {
                out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                Toast.makeText(this,"Foto salva com sucesso!",Toast.LENGTH_LONG).show();
            }
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
    public void produtos(View view) {
        Intent intent = new Intent(MainActivity.this, Produtos.class);
        startActivity(intent);
    }
}