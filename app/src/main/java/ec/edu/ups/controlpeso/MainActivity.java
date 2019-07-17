package ec.edu.ups.controlpeso;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.TextView;


import com.github.clans.fab.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Thread iniReloj = null;
    Runnable r;
    boolean isUpdate = false;
    int hora, minuto, seg;
    String h, m, s;
    Date date;
    TextView horas, horac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date = new Date();

        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        //System.out.println();

        r = new RefreshClock();
        iniReloj = new Thread(r);
        iniReloj.start();

        FloatingActionButton flota = (FloatingActionButton) findViewById(R.id.opciones);
         horas= (TextView) findViewById(R.id.reloj);
        flota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, programar.class));
            }
        });
    }


    private void initClock() {
        runOnUiThread(new Runnable() {
            public void run() {
                try{
                    updateTime();
                    horas.setText(h+":"+m+":"+s);
                }catch (Exception e) {}
            }
        });
    }
    class RefreshClock implements Runnable{
        // @Override
        @SuppressWarnings("unused")
        public void run() {

            while(!Thread.currentThread().isInterrupted()){
                try {
                    initClock();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }catch(Exception e){
                }
            }
        }
    }
    private void updateTime(){
        Calendar c = Calendar.getInstance();
        hora = c.get(Calendar.HOUR_OF_DAY);
        minuto = c.get(Calendar.MINUTE);
        seg = c.get(Calendar.SECOND);

        if(hora == 0)
        {
            h=0+"0";
        }
        else if(hora < 10)
        {
            h="0"+(hora);
        }
        else{
            h=(hora)+"";
        }
        if(minuto < 10)
        {
            m="0"+minuto;
        }
        else{
            m=minuto+"";
        }
        if(seg < 10)
        {
            s="0"+seg;
        }
        else{
            s=seg+"";
        }
    }


}
