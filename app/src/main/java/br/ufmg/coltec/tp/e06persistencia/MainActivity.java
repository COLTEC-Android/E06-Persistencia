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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final int FOTO_CODE = 281;

    private static final String filename = "foto.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final File file = new File(this.getExternalFilesDir(Environment.DIRECTORY_DCIM), filename); // /storage/0/android/data/package/files/dcim/foto.png

        final ImageView foto = findViewById(R.id.imagem);
        Button tiraFoto = findViewById(R.id.btn_tirar);
        Button salvaFoto = findViewById(R.id.btn_salvar);

        tiraFoto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(fotoIntent, FOTO_CODE);
            }
        });

        salvaFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bm=((BitmapDrawable)foto.getDrawable()).getBitmap(); //Extrair foto do imageview

                FileOutputStream out = null;
                try {
                    out = new FileOutputStream(file);
                    bm.compress(Bitmap.CompressFormat.PNG, 100, out); // PNG is a lossless format, the compression factor (100) is ignored
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
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case FOTO_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    ImageView imageView = findViewById(R.id.imagem);
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    imageView.setImageBitmap(photo);
                }
                break;
            default:
                //nada
                break;
        }
    }

}
