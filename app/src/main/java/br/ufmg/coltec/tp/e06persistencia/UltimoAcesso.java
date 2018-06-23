package br.ufmg.coltec.tp.e06persistencia;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UltimoAcesso {
    private SharedPreferences Preferences;

    private String HORARIO_CHAVE = "horario";

    public UltimoAcesso(Context context, String APP_PREF_ID){
        this.Preferences = context.getSharedPreferences(APP_PREF_ID, 0);
    }
    public String getUltimoAcesso(){
        return this.Preferences.getString(HORARIO_CHAVE, "");

    }
    public boolean existeChave(){
        return Preferences.contains(HORARIO_CHAVE);
    }
    public void setUltimoAcesso() {
        Calendar c = Calendar.getInstance();
        //System.out.println("Current time =&gt; "+c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/YYYY - HH:mm");
        String formattedDate = df.format(c.getTime());

        SharedPreferences.Editor editor = this.Preferences.edit();
        editor.putString(HORARIO_CHAVE, formattedDate);
        editor.commit();

    }
}

