package br.ufmg.coltec.tp.e06persistencia;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static int FOTO_CODE = 1;
    private static String APP_PREF_ID = "br.ufmg.coltec.tp.e06persistencia";

    public ImageButton takePic;
    public Button savePic;
    public ImageView pic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        takePic = findViewById(R.id.takePic);
        savePic = findViewById(R.id.savePic);
        pic     = findViewById(R.id.image);


        takePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newFt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(newFt, FOTO_CODE);
            }
        });


        savePic.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveImage(pic);
            }
        });

        getAcces();
        setAccess();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == FOTO_CODE && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            pic.setImageBitmap(photo);
        }
    }


    private void saveImage(ImageView image){
        BitmapDrawable img = (BitmapDrawable)image.getDrawable();
        Bitmap bmp = img.getBitmap();

        String filename;
        Date date = new Date(0);
        SimpleDateFormat sdf = new SimpleDateFormat ("yyyyMMddHHmmss");
        filename =  sdf.format(date);

        try{
            String path = Environment.getExternalStorageDirectory().toString();
            OutputStream fOut = null;
            File file = new File(path, "/DCIM/Signatures/"+filename+".jpg");
            fOut = new FileOutputStream(file);

            bmp.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
            fOut.flush();
            fOut.close();

            MediaStore.Images.Media.insertImage(getContentResolver()
                    ,file.getAbsolutePath(),file.getName(),file.getName());

        }catch (Exception e) {
            e.printStackTrace();
        } 
    }

    private void getAcces () {
        SharedPreferences pref = getBaseContext().getSharedPreferences(APP_PREF_ID, 0);
        String value = pref.getString("keyTimeNow", "");

        if (!value.equals(""))
            Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();

    }

    private void setAccess () {
        String timeNow = new SimpleDateFormat("dd/mm/yyyy HH:MM").format(System.currentTimeMillis());
        SharedPreferences pref = getApplicationContext().getSharedPreferences(APP_PREF_ID, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("time", timeNow);
        editor.apply();
    }
}
