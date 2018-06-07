package br.ufmg.coltec.tp.e06persistencia;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

import static java.lang.System.out;

public class MainActivity extends AppCompatActivity {
    private static int FOTO_CODE = 1;
    private static String APP_PREF_ID = "br.ufmg.coltec.tp.e06persistencia";
    public ImageButton mTakePhotoBtn;
    public Button mSaveBtn;
    public ImageView mViewPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSaveBtn = findViewById(R.id.save_btn);
        mTakePhotoBtn = findViewById(R.id.takePhoto_imBtn);
        mViewPhoto = findViewById(R.id.photo_iv);

        getLastAccess();
        setLastAccess();

        mTakePhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(fotoIntent, FOTO_CODE);
            }
        });

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePhotoFile(((BitmapDrawable)mViewPhoto.getDrawable()).getBitmap());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == FOTO_CODE && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            mViewPhoto.setImageBitmap(photo);
        }
    }

    private void savePhotoFile(Bitmap photo) {
        File file = new File(getBaseContext().getExternalFilesDir(null), "fotos.png");

        try {
            if(Environment.getExternalStorageState(file).equals(Environment.MEDIA_MOUNTED)) {

                // encapsula streaming em um writer para facilitar a escrita
                FileOutputStream out = new FileOutputStream(file);
                Log.d("MainActivity", "photo" + photo.toString());

                photo.compress(Bitmap.CompressFormat.PNG, 100, out);


            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    private void setLastAccess () {
        @SuppressLint("SimpleDateFormat")
        String timeNow = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(System.currentTimeMillis());
        SharedPreferences pref = getApplicationContext().getSharedPreferences(APP_PREF_ID, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("keyTimeNow", timeNow);
        editor.apply();
    }

    private void getLastAccess () {
        SharedPreferences pref = getBaseContext().getSharedPreferences(APP_PREF_ID, 0);
        String valueStr = pref.getString("keyTimeNow", "");

        if (!valueStr.equals("")) {
            Toast.makeText(MainActivity.this, valueStr, Toast.LENGTH_SHORT).show();
        }
    }
}
