package br.ufmg.coltec.tp.e06persistencia;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
    private static final String APP_PREF_ID="Android-E06-Persistencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView foto = (ImageView) findViewById(R.id.imagemView);
        Button changePic = (Button) findViewById(R.id.changePicbtn);
        Button savePic = (Button) findViewById(R.id.savePicbtn);

        /* Ultimo acesso tem uma classe própria com o objetivo de melhorar a legibilidade do código
            Na prática, não sei se seria muito útil.
         */
        UltimoAcesso ultimo = new UltimoAcesso(getApplicationContext(),APP_PREF_ID );
        if(ultimo.existeChave()){
            Toast toast = Toast.makeText(getApplicationContext(), ultimo.getUltimoAcesso(), Toast.LENGTH_LONG);
            toast.show();
        }
        ultimo.setUltimoAcesso();

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

    private boolean salvaFoto(String nome, ImageView foto) {
        final String filename = nome+".png";
        final File file = new File(this.getExternalFilesDir(Environment.DIRECTORY_DCIM), filename);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_prod:
                // inicia a activity Cadastro_Produto quando o usuário clica no botão correspondente
                Intent intent = new Intent(MainActivity.this, Produtos.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}


