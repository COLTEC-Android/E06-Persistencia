package br.ufmg.coltec.tp.e06persistencia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.util.Calendar;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static int FOTO_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String atual = Calendar.getInstance().getTime().toString();
        File file = new File(this.getFilesDir(), "arquivo.txt");
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        try {
            // encapsula streaming em um reader para facilitar a leitura
            FileReader in = new FileReader(file);
            BufferedReader reader = new BufferedReader(in);

            // faz a leitura do dado
            String conteudo = "";
            String line;

            while((line = reader.readLine()) != null)
                conteudo += line;

            Toast toast = Toast.makeText(context, conteudo, duration);
            toast.show();

            reader.close();

        } catch(Exception e) {
            Log.e("File", "Erro ao fazer leitura do arquivo");
        }

        try {
            // encapsula streaming em um writer para facilitar a escrita
            FileWriter out = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(out);

            // realiza a escrita do dad
            writer.write(atual);
            writer.close();
        } catch(Exception e) {
            Log.e("File", "Erro ao gravar arquivo:" + e.toString());
        }



    }

    public void onClick(View v) {
        Intent ifoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(ifoto, FOTO_CODE);
    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageView visu = findViewById(R.id.foto);

        // verifica se retorno pertence a Intent chamada anterioremente,
        // e se ela foi executada corretamente
        if (requestCode == FOTO_CODE && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            visu.setImageBitmap(photo);
        }

    }

    public void onClick2(View v){
        FileOutputStream out = null;
        String path = this.getExternalFilesDir(Environment.DIRECTORY_DCIM).toString();
        String filename = "merda.png";
        ImageView visu = findViewById(R.id.foto);
        Bitmap photo = ((BitmapDrawable)visu.getDrawable()).getBitmap();

        try {
            File file = new File(path, filename);
            out = new FileOutputStream(file);
            photo.compress(Bitmap.CompressFormat.PNG, 100, out);
            // PNG is a lossless format, the compression factor (100) is ignored
            MediaStore.Images.Media.insertImage(getContentResolver(),file.getAbsolutePath(),file.getName(),file.getName());
        } catch (Exception e) {
            Log.e("DEU RUIM", "DEU RUIM2");
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

    public void onClick3(View v){
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }
}
