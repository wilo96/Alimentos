package ec.edu.ups.controlpeso;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class programar extends AppCompatActivity implements View.OnClickListener {
    Spinner opc;
    MediaPlayer mp;
    EditText co1, co2, co3, co4, co5;
    TextView t1, t2, t3, t4, t5, recom;
    ImageButton h1, h2, h3, h4, h5, e1, e2, e3, e4, e5;
    int ho, min;
    String [] vector;
    Thread iniReloj = null;
    Runnable r;
    boolean isUpdate = false;
    int hora, minuto, seg;
    String h, m, s;
    Date date;
    TextView horas, horac;
    NotificationCompat.Builder nBuilder;
    int not=001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programar);
        nBuilder = new NotificationCompat.Builder(this);
        nBuilder.setAutoCancel(true);
        mp=MediaPlayer.create(getBaseContext(),R.raw.sonidito);

        //Intent intent = new Intent(MainActivity.this, programar.class);
        //PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0,intent, 0);
        date = new Date();

        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        //System.out.println();
        horas= (TextView) findViewById(R.id.reloj);
        horac= (TextView) findViewById(R.id.horac);
        r = new programar.RefreshClock();
        iniReloj = new Thread(r);
        iniReloj.start();
        FloatingActionButton atras = (FloatingActionButton) findViewById(R.id.atras);
        FloatingActionButton guardar = (FloatingActionButton) findViewById(R.id.guardar);
        co1=(EditText)findViewById(R.id.txth1);
        co2=(EditText)findViewById(R.id.txth2);
        co3=(EditText)findViewById(R.id.txth3);
        co4=(EditText)findViewById(R.id.txth4);
        co5=(EditText)findViewById(R.id.txth5);
        recom = (TextView) findViewById(R.id.recom);
        h1=(ImageButton)findViewById(R.id.btnco1);
        h2=(ImageButton)findViewById(R.id.btnco2);
        h3=(ImageButton)findViewById(R.id.btnco3);
        h4=(ImageButton)findViewById(R.id.btnco4);
        h5=(ImageButton)findViewById(R.id.btnco5);
        e1=(ImageButton)findViewById(R.id.btned1);
        e2=(ImageButton)findViewById(R.id.btned2);
        e3=(ImageButton)findViewById(R.id.btned3);
        e4=(ImageButton)findViewById(R.id.btned4);
        e5=(ImageButton)findViewById(R.id.btned5);
        t1=(TextView)findViewById(R.id.t1);
        t2=(TextView)findViewById(R.id.t2);
        t3=(TextView)findViewById(R.id.t3);
        t4=(TextView)findViewById(R.id.t4);
        t5=(TextView)findViewById(R.id.t5);
        co1.setVisibility(View.INVISIBLE);
        co2.setVisibility(View.INVISIBLE);
        co3.setVisibility(View.INVISIBLE);
        co4.setVisibility(View.INVISIBLE);
        co5.setVisibility(View.INVISIBLE);
        h1.setVisibility(View.INVISIBLE);
        h2.setVisibility(View.INVISIBLE);
        h3.setVisibility(View.INVISIBLE);
        h4.setVisibility(View.INVISIBLE);
        h5.setVisibility(View.INVISIBLE);
        t1.setVisibility(View.INVISIBLE);
        t2.setVisibility(View.INVISIBLE);
        t3.setVisibility(View.INVISIBLE);
        t4.setVisibility(View.INVISIBLE);
        t5.setVisibility(View.INVISIBLE);
        e1.setVisibility(View.INVISIBLE);
        e2.setVisibility(View.INVISIBLE);
        e3.setVisibility(View.INVISIBLE);
        e4.setVisibility(View.INVISIBLE);
        e5.setVisibility(View.INVISIBLE);
        e1.setEnabled(false);
        e2.setEnabled(false);
        e3.setEnabled(false);
        e4.setEnabled(false);
        e5.setEnabled(false);
        h1.setOnClickListener(this);
        h2.setOnClickListener(this);
        h3.setOnClickListener(this);
        h4.setOnClickListener(this);
        h5.setOnClickListener(this);
        e1.setOnClickListener(this);
        e2.setOnClickListener(this);
        e3.setOnClickListener(this);
        e4.setOnClickListener(this);
        e5.setOnClickListener(this);

        recom.setText("                             RECOMENDACIONES DE COMIDA\n•Guineo: Fruta alta en Potacio---Te Brinda Nutrientes para soportar 1 Hora\n•Frutos Secos: Ricos en Vitaminas---Sacian la necesidad de hambre por un tiempo prolongado\n" +
                "•Sanduche de Atun: Rico en calcio---Brinda Proteinas Necesarias para el día a día\n•Manzana: Te brinda energía---Al comer 2 te sacia el hambre y da más energía\n•Jugo de Naranja con Zanahoria: Te brina la energia " +
                "necesaria para tu día\n•Batido de Frutas: Una mezcla de Frutas puede generar mas vitaminas y minerales en tu cuerpo de los que puedes necesitar para tu día a día.");


        opc= (Spinner) findViewById(R.id.spin);
        ArrayAdapter<CharSequence> array = ArrayAdapter.createFromResource(this, R.array.opc, android.R.layout.simple_spinner_item);
        opc.setAdapter(array);

        opc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selec= opc.getSelectedItem().toString();
                vector= new String[Integer.parseInt(selec)];
                //Toast.makeText(getApplicationContext(),selec,Toast.LENGTH_LONG).show();
                if(selec.equals("1"))
                {
                    t1.setVisibility(View.VISIBLE);
                    co1.setVisibility(View.VISIBLE);
                    h1.setVisibility(View.VISIBLE);
                    e1.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.INVISIBLE);
                    co2.setVisibility(View.INVISIBLE);
                    h2.setVisibility(View.INVISIBLE);
                    e2.setVisibility(View.INVISIBLE);
                    t3.setVisibility(View.INVISIBLE);
                    co3.setVisibility(View.INVISIBLE);
                    h3.setVisibility(View.INVISIBLE);
                    e3.setVisibility(View.INVISIBLE);
                    t4.setVisibility(View.INVISIBLE);
                    co4.setVisibility(View.INVISIBLE);
                    h4.setVisibility(View.INVISIBLE);
                    e4.setVisibility(View.INVISIBLE);
                    t5.setVisibility(View.INVISIBLE);
                    co5.setVisibility(View.INVISIBLE);
                    h5.setVisibility(View.INVISIBLE);
                    e5.setVisibility(View.INVISIBLE);
                }
                else if(selec.equals("2"))
                {
                    t1.setVisibility(View.VISIBLE);
                    co1.setVisibility(View.VISIBLE);
                    h1.setVisibility(View.VISIBLE);
                    e1.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.VISIBLE);
                    co2.setVisibility(View.VISIBLE);
                    h2.setVisibility(View.VISIBLE);
                    e2.setVisibility(View.VISIBLE);
                    t3.setVisibility(View.INVISIBLE);
                    co3.setVisibility(View.INVISIBLE);
                    h3.setVisibility(View.INVISIBLE);
                    e3.setVisibility(View.INVISIBLE);
                    t4.setVisibility(View.INVISIBLE);
                    co4.setVisibility(View.INVISIBLE);
                    h4.setVisibility(View.INVISIBLE);
                    e4.setVisibility(View.INVISIBLE);
                    t5.setVisibility(View.INVISIBLE);
                    co5.setVisibility(View.INVISIBLE);
                    h5.setVisibility(View.INVISIBLE);
                    e5.setVisibility(View.INVISIBLE);
                }
                else if(selec.equals("3"))
                {
                    t1.setVisibility(View.VISIBLE);
                    co1.setVisibility(View.VISIBLE);
                    h1.setVisibility(View.VISIBLE);
                    e1.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.VISIBLE);
                    co2.setVisibility(View.VISIBLE);
                    h2.setVisibility(View.VISIBLE);
                    e2.setVisibility(View.VISIBLE);
                    t3.setVisibility(View.VISIBLE);
                    co3.setVisibility(View.VISIBLE);
                    h3.setVisibility(View.VISIBLE);
                    e3.setVisibility(View.VISIBLE);
                    t4.setVisibility(View.INVISIBLE);
                    co4.setVisibility(View.INVISIBLE);
                    h4.setVisibility(View.INVISIBLE);
                    e4.setVisibility(View.INVISIBLE);
                    t5.setVisibility(View.INVISIBLE);
                    co5.setVisibility(View.INVISIBLE);
                    h5.setVisibility(View.INVISIBLE);
                    e5.setVisibility(View.INVISIBLE);
                }
                else if(selec.equals("4"))
                {
                    t1.setVisibility(View.VISIBLE);
                    co1.setVisibility(View.VISIBLE);
                    h1.setVisibility(View.VISIBLE);
                    e1.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.VISIBLE);
                    co2.setVisibility(View.VISIBLE);
                    h2.setVisibility(View.VISIBLE);
                    e2.setVisibility(View.VISIBLE);
                    t3.setVisibility(View.VISIBLE);
                    co3.setVisibility(View.VISIBLE);
                    h3.setVisibility(View.VISIBLE);
                    e3.setVisibility(View.VISIBLE);
                    t4.setVisibility(View.VISIBLE);
                    co4.setVisibility(View.VISIBLE);
                    h4.setVisibility(View.VISIBLE);
                    e4.setVisibility(View.VISIBLE);
                    t5.setVisibility(View.INVISIBLE);
                    co5.setVisibility(View.INVISIBLE);
                    h5.setVisibility(View.INVISIBLE);
                    e5.setVisibility(View.INVISIBLE);
                }
                else if(selec.equals("5"))
                {
                    t1.setVisibility(View.VISIBLE);
                    co1.setVisibility(View.VISIBLE);
                    h1.setVisibility(View.VISIBLE);
                    e1.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.VISIBLE);
                    co2.setVisibility(View.VISIBLE);
                    h2.setVisibility(View.VISIBLE);
                    e2.setVisibility(View.VISIBLE);
                    t3.setVisibility(View.VISIBLE);
                    co3.setVisibility(View.VISIBLE);
                    h3.setVisibility(View.VISIBLE);
                    e3.setVisibility(View.VISIBLE);
                    t4.setVisibility(View.VISIBLE);
                    co4.setVisibility(View.VISIBLE);
                    h4.setVisibility(View.VISIBLE);
                    e4.setVisibility(View.VISIBLE);
                    t5.setVisibility(View.VISIBLE);
                    co5.setVisibility(View.VISIBLE);
                    h5.setVisibility(View.VISIBLE);
                    e5.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(programar.this,MainActivity.class));
                onBackPressed();
            }
        });
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(vector.length==1)
                {
                    vector[0]=String.valueOf(co1.getText());
                    e1.setEnabled(true);
                    co1.setEnabled(false);
                    h1.setEnabled(false);
                    //vector[1]="0";
                   // vector[2]="0";
                   // vector[3]="0";
                  //  vector[4]="0";
                }
                else if(vector.length==2)
                {
                    vector[0]=String.valueOf(co1.getText());
                    e1.setEnabled(true);
                    co1.setEnabled(false);
                    h1.setEnabled(false);
                    vector[1]=String.valueOf(co2.getText());
                    e2.setEnabled(true);
                    co2.setEnabled(false);
                    h2.setEnabled(false);
                   // vector[2]="0";
                   // vector[3]="0";
                   // vector[4]="0";
                }
                else if(vector.length==3)
                {
                    vector[0]=String.valueOf(co1.getText());
                    e1.setEnabled(true);
                    co1.setEnabled(false);
                    h1.setEnabled(false);
                    vector[1]=String.valueOf(co2.getText());
                    e2.setEnabled(true);
                    co2.setEnabled(false);
                    h2.setEnabled(false);
                    vector[2]=String.valueOf(co3.getText());
                    e3.setEnabled(true);
                    co3.setEnabled(false);
                    h3.setEnabled(false);
                  //  vector[3]="0";
                  //  vector[4]="0";
                }
                else if(vector.length==4)
                {
                    vector[0]=String.valueOf(co1.getText());
                    e1.setEnabled(true);
                    co1.setEnabled(false);
                    h1.setEnabled(false);
                    vector[1]=String.valueOf(co2.getText());
                    e2.setEnabled(true);
                    co2.setEnabled(false);
                    h2.setEnabled(false);
                    vector[2]=String.valueOf(co3.getText());
                    e3.setEnabled(true);
                    co3.setEnabled(false);
                    h3.setEnabled(false);
                    vector[3]=String.valueOf(co4.getText());
                    e4.setEnabled(true);
                    co4.setEnabled(false);
                    h4.setEnabled(false);
                   // vector[4]="0";
                }
                else if(vector.length==5)
                {
                    vector[0]=String.valueOf(co1.getText());
                    e1.setEnabled(true);
                    co1.setEnabled(false);
                    h1.setEnabled(false);
                    vector[1]=String.valueOf(co2.getText());
                    e2.setEnabled(true);
                    co2.setEnabled(false);
                    h2.setEnabled(false);
                    vector[2]=String.valueOf(co3.getText());
                    e3.setEnabled(true);
                    co3.setEnabled(false);
                    h3.setEnabled(false);
                    vector[3]=String.valueOf(co4.getText());
                    e4.setEnabled(true);
                    co4.setEnabled(false);
                    h4.setEnabled(false);
                    vector[4]=String.valueOf(co5.getText());
                    e5.setEnabled(true);
                    co5.setEnabled(false);
                    h5.setEnabled(false);
                }
                control();
               // Toast.makeText(getApplicationContext(),vector.length,Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        //this is only needed if you have specific things
        //that you want to do when the user presses the back button.
        /* your specific things...*/
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
       if(view==h1)
       {
           final Calendar c = Calendar.getInstance();
           ho=c.get(Calendar.HOUR_OF_DAY);
           min=c.get(Calendar.MINUTE);


           TimePickerDialog tpd = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
               @Override
               public void onTimeSet(TimePicker timePicker, int hor, int minut) {
                   String hh, mm;
                   if(hor < 10)
                   {
                       hh="0"+hor;
                   }
                   else
                   {
                       hh=hor+"";
                   }
                   if(minut < 10)
                   {
                       mm="0"+minut;
                   }
                   else
                   {
                       mm=minut+"";
                   }
                   co1.setText(hh+":"+mm+":00");
               }
           },ho,min,true);
           tpd.show();
       }
       else if(view==h2)
       {
           final Calendar c = Calendar.getInstance();
           ho=c.get(Calendar.HOUR_OF_DAY);
           min=c.get(Calendar.MINUTE);


           TimePickerDialog tpd = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
               @Override
               public void onTimeSet(TimePicker timePicker, int hor, int minut) {
                   String hh, mm;
                   if(hor < 10)
                   {
                       hh="0"+hor;
                   }
                   else
                   {
                       hh=hor+"";
                   }
                   if(minut < 10)
                   {
                       mm="0"+minut;
                   }
                   else
                   {
                       mm=minut+"";
                   }
                   co2.setText(hh+":"+mm+":00");
               }
           },ho,min,true);
           tpd.show();
       }
       else if(view==h3)
       {
           final Calendar c = Calendar.getInstance();
           ho=c.get(Calendar.HOUR_OF_DAY);
           min=c.get(Calendar.MINUTE);


           TimePickerDialog tpd = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
               @Override
               public void onTimeSet(TimePicker timePicker, int hor, int minut) {
                   String hh, mm;
                   if(hor < 10)
                   {
                       hh="0"+hor;
                   }
                   else
                   {
                       hh=hor+"";
                   }
                   if(minut < 10)
                   {
                       mm="0"+minut;
                   }
                   else
                   {
                       mm=minut+"";
                   }
                   co3.setText(hh+":"+mm+":00");
               }
           },ho,min,true);
           tpd.show();
       }
       else if(view==h4)
       {
           final Calendar c = Calendar.getInstance();
           ho=c.get(Calendar.HOUR_OF_DAY);
           min=c.get(Calendar.MINUTE);


           TimePickerDialog tpd = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
               @Override
               public void onTimeSet(TimePicker timePicker, int hor, int minut) {
                   String hh, mm;
                   if(hor < 10)
                   {
                       hh="0"+hor;
                   }
                   else
                   {
                       hh=hor+"";
                   }
                   if(minut < 10)
                   {
                       mm="0"+minut;
                   }
                   else
                   {
                       mm=minut+"";
                   }
                   co4.setText(hh+":"+mm+":00");
               }
           },ho,min,true);
           tpd.show();
       }
       else if(view==h5)
       {
           final Calendar c = Calendar.getInstance();
           ho=c.get(Calendar.HOUR_OF_DAY);
           min=c.get(Calendar.MINUTE);


           TimePickerDialog tpd = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
               @Override
               public void onTimeSet(TimePicker timePicker, int hor, int minut) {
                   String hh, mm;
                   if(hor < 10)
                   {
                       hh="0"+hor;
                   }
                   else
                   {
                       hh=hor+"";
                   }
                   if(minut < 10)
                   {
                       mm="0"+minut;
                   }
                   else
                   {
                       mm=minut+"";
                   }
                   if(hor == 24)
                   {
                       hh="00";
                   }
                   co5.setText(hh+":"+mm+":00");
               }
           },ho,min,true);
           tpd.show();
       }
       else if(view==e1)
       {
           co1.setEnabled(true);
           h1.setEnabled(true);
       }
       else if(view==e2)
       {
           co2.setEnabled(true);
           h2.setEnabled(true);
       }
       else if(view==e3)
       {
           co3.setEnabled(true);
           h3.setEnabled(true);
       }
       else if(view==e4)
       {
           co4.setEnabled(true);
           h4.setEnabled(true);
       }
       else if(view==e5)
       {
           co5.setEnabled(true);
           h5.setEnabled(true);
       }

    }
    int co=1;
    private void initClock() {
        runOnUiThread(new Runnable() {
            public void run() {

                try{
                    updateTime();
                    horas.setText(h+":"+m+":"+s);
                    if(horas.getText().equals(horac.getText()))
                    {
                        if(co < vector.length) {
                            //Toast.makeText(getApplicationContext(), "Come Mierda", Toast.LENGTH_LONG).show();
                            nBuilder.setSmallIcon(R.drawable.ic_action_notif);
                            nBuilder.setTicker("Debes Comer");
                            nBuilder.setContentTitle("Es hora de Comer");
                            nBuilder.setContentText("No te pases de tu hora de comida");
                            mp.start();
                            //Intent intent = new Intent(programar.this,programar.class);
                            //PendingIntent pi= PendingIntent.getActivity(programar.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                            //nBuilder.setContentIntent(pi);
                            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                            nm.notify(not,nBuilder.build());
                            horac.setText(vector[co]);
                            co++;
                        } else if(co == vector.length){
                            co=0;
                            //Toast.makeText(getApplicationContext(), "Come Mierda", Toast.LENGTH_LONG).show();
                            nBuilder.setSmallIcon(R.drawable.ic_action_notif);
                            nBuilder.setTicker("Debes Comer");
                            nBuilder.setContentTitle("Es hora de Comer");
                            nBuilder.setContentText("No te pases de tu hora de comida");
                            mp.start();
                            //Intent intent = new Intent(programar.this,programar.class);
                            //PendingIntent pi= PendingIntent.getActivity(programar.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                            //nBuilder.setContentIntent(pi);
                            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                            nm.notify(not,nBuilder.build());

                            horac.setText(vector[co]);

                        }



                    }
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

    public void control()
    {
        int co=0;
        if(vector.length > 0)
        {
               // Toast.makeText(getApplicationContext(),vector.length+"", Toast.LENGTH_LONG).show();
                horac.setText(vector[0]);

        }
        else
        {
            horac.setText("00:00:00");
        }
    }
}
