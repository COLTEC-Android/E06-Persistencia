package br.ufmg.coltec.tp.e06persistencia;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {

    private static int FOTO_CODE = 1;
    private ImageView imageView;
    private String APP_PREF_ID = "PersistenciaID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy - HH:mm", Locale.getDefault());
        String currentDateTime = currentDate.format(new Date());

        SharedPreferences pref = getApplicationContext().getSharedPreferences(APP_PREF_ID,0) ;
        SharedPreferences.Editor editor = pref.edit();
        String valueStr = pref.getString("data/hora","");

        if(valueStr != ""){

            Toast.makeText(MainActivity.this, "Ultimo acesso:"+valueStr, Toast.LENGTH_LONG).show();
            editor.putString("data/hora",currentDateTime);
            editor.commit();

        }else{
            Toast.makeText(MainActivity.this,"Primeiro acesso:"+ currentDateTime, Toast.LENGTH_LONG).show();
            editor.putString("data/hora",currentDateTime);
            editor.commit();
        }



        Button fotoBtn=findViewById(R.id.btn_foto);
        Button salvarBtn = findViewById(R.id.btn_salvar);
        Button produtosBtn = findViewById(R.id.btn_produtos);

        fotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(fotoIntent,FOTO_CODE);
            }
        });

        salvarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bitmap bm=((BitmapDrawable)imageView.getDrawable()).getBitmap();

                File file = new File(getApplicationContext().getExternalFilesDir(" / "),"foto.png");

                try{
                    if(Environment.getExternalStorageState(file).equals(Environment.MEDIA_MOUNTED)){
                        FileOutputStream out = new FileOutputStream(file);
                        bm.compress(Bitmap.CompressFormat.PNG,90,out);
                        out.flush();
                        out.close();
                        Toast.makeText(MainActivity.this, "Imagem salva!", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e){
                    Log.e("File","Erro ao gravar arquivo:"+ e.toString());
                }
            }
        });

        produtosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ProdutosActivity.class);
                startActivity(intent);
            }
        });


    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        Bitmap photo;
        imageView=findViewById(R.id.img_foto);

        if(requestCode==FOTO_CODE && resultCode== Activity.RESULT_OK){
            photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);

        }
    }



}
