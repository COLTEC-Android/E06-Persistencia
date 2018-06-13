package br.ufmg.coltec.tp.e06persistencia;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends Activity {
    private static int FOTO_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final ImageView foto = (ImageView) findViewById(R.id.imagemView);
        Button changePic = (Button) findViewById(R.id.changePicbtn);
        Button savePic = (Button) findViewById(R.id.savePicbtn);

        changePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(fotoIntent, FOTO_CODE);
            }
        });

        savePic.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               FileOutputStream output = null;
               String path = Environment.getExternalStorageDirectory().toString();

               BitmapDrawable bitmapDrawable = ((BitmapDrawable) foto.getDrawable());
               Bitmap bitmap = bitmapDrawable .getBitmap();

               Toast toast = Toast.makeText(getApplicationContext(), path, Toast.LENGTH_SHORT);
               toast.show();

               try {
                   output = new FileOutputStream(path+"imagem.png");
                   bitmap.compress(Bitmap.CompressFormat.PNG, 100, output); // bmp is your Bitmap instance

               }catch (Exception e){
                   e.printStackTrace();
               }finally {
                   try {
                       if (output != null) {
                           output.close();
                       }
                   } catch (IOException e) {
                       e.printStackTrace();
                   }

               }
           }
       });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageView imagem = findViewById(R.id.imagemView);

        // verifica se retorno pertence a Intent chamada anterioremente,
        // e se ela foi executada corretamente
        if (requestCode == FOTO_CODE && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imagem.setImageBitmap(photo);
        }
    }
}
