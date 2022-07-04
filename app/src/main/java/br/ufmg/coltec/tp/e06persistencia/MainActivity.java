package br.ufmg.coltec.tp.e06persistencia;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static int FOTO_CODE = 1;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button fotoBtn=findViewById(R.id.btn_foto);
        Button salvarBtn = findViewById(R.id.btn_salvar);

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
                // não validei se o arquivo existe - o botão só devia funcionar se a foto tiver sido tirada

                Bitmap bm=((BitmapDrawable)imageView.getDrawable()).getBitmap();

                File file = new File(getApplicationContext().getExternalFilesDir("/"),"foto.png");

                try{
                    if(Environment.getExternalStorageState(file).equals(Environment.MEDIA_MOUNTED)){
                        FileOutputStream out = new FileOutputStream(file);
                        bm.compress(Bitmap.CompressFormat.PNG,90,out);
                        out.flush();
                        out.close();
                    }

                } catch (Exception e){
                    Log.e("File","Erro ao gravar arquivo:"+ e.toString());
                }
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
