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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends Activity {
    private static int FOTO_CODE = 1;
    private int pictureCounter = 0;
    private boolean atualSalva = false; //indica de a foto atual do ImageView já foi salva
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
                try {
                    startActivityForResult(fotoIntent, FOTO_CODE);
                    atualSalva=false;
                }catch (Exception e){}
            }
        });

        savePic.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(!atualSalva) {//evita que uma imagem seja duplicada
                   atualSalva = salvaFoto(Integer.toString(pictureCounter), foto);
                   pictureCounter++;
               }else{
                   Toast toast = Toast.makeText(getApplicationContext(), "Essa imagem já foi salva", Toast.LENGTH_SHORT);
                   toast.show();
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
    private boolean salvaFoto(String nome, ImageView foto) {//essa função é a mesma que eu usei no trab de recuperação
        //final ImageView foto = findViewById(R.id.foto);
        final String filename = nome+".png";
        final File file = new File(this.getExternalFilesDir(Environment.DIRECTORY_DCIM), filename);  // /storage/0/android/data/package/files/dcim/foto.png
        boolean imagemSalva = false;

        if(((BitmapDrawable)foto.getDrawable())!=null){

            Bitmap bm=((BitmapDrawable)foto.getDrawable()).getBitmap(); //Extrair foto do imageview

            FileOutputStream out = null;
            try {

                out = new FileOutputStream(file);
                bm.compress(Bitmap.CompressFormat.PNG, 100, out);
            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                try {
                    if (out != null) {
                        out.close();
                        imagemSalva = true;
                        Toast toast = Toast.makeText(getApplicationContext(), "Imagem salva em '"+this.getExternalFilesDir(Environment.DIRECTORY_DCIM)+"'", Toast.LENGTH_LONG);
                        toast.show();

                    };
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return imagemSalva;
    }
}
