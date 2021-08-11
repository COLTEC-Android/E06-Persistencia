package br.ufmg.coltec.tp.e06persistencia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    private static final int IMG_CODE = 1;
    private static final String PREF_ID = "PrefID";
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button getImage = findViewById(R.id.btn_get);
        Button saveImage = findViewById(R.id.btn_save);
        Button dbActivity = findViewById(R.id.btn_bd);
        ImageView img = findViewById(R.id.img_photo);

        accessToast();

        getImage.setOnClickListener(view -> {
            Intent getPhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(getPhoto,1);
        });

        saveImage.setOnClickListener((view) ->{

            Bitmap b = ((BitmapDrawable) img.getDrawable()).getBitmap();
            File file = new File(MainActivity.this.getExternalFilesDir(null), "img.png");

            try {
                if(Environment.getExternalStorageState(file).equals(Environment.MEDIA_MOUNTED)) {

                    FileOutputStream out = new FileOutputStream(file);
                    b.compress(Bitmap.CompressFormat.PNG, 90, out);
                    out.flush();
                    out.close();
                }
            } catch(Exception e) {
                Log.e("File", "Erro ao gravar arquivo:" + e.toString());
            }
            img.setImageResource(R.drawable.ic_launcher_foreground);
            Toast.makeText(MainActivity.this, "Foto salva com sucesso", Toast.LENGTH_SHORT).show();
        });

        dbActivity.setOnClickListener((view) ->{

            Intent intent = new Intent(this, CadastroProduto.class);
            startActivity(intent);
        });


    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == IMG_CODE && resultCode == Activity.RESULT_OK){
            Bitmap user = (Bitmap) data.getExtras().get("data");
            ImageView userImage = findViewById(R.id.img_photo);
            userImage.setImageBitmap(user);
        }

    }

    protected void accessToast(){
        sp = this.getSharedPreferences(PREF_ID,0);
        String time = sdf.format(new Date());
        String str = sp.getString("time","");

        if (str.equals("")){
            Toast.makeText(MainActivity.this, "Data atual: " + time, Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MainActivity.this, "Data do último acesso: " + str, Toast.LENGTH_LONG).show();
        }
        ;
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("time", time);
        editor.apply();
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//
//        SharedPreferences.Editor editor = sp.edit();
//        editor.putString("time", time);
//        editor.apply();
//        Log.d ("commit","Commit realizado com sucesso");
//    }

}
