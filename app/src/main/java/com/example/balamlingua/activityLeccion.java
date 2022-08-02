package com.example.balamlingua;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

public class activityLeccion extends AppCompatActivity {

    private String respuesta, respuestaComp;
    RequestQueue requestQeue;
    JsonObjectRequest jsonObjectRequest;
    RadioButton rb;
    String URL_QUERY;
    int counter = 0;
    int buenas = 0;
    int numero=0;
    String a,b,c,d;
    LottieAnimationView vida;
    int vidasRestantes=0;
    TextView vidaR;
    int mili=0,seg=0,min=0;
    Boolean comenzarCrono=false;
    Handler h = new Handler();
    String m="",s="",mi="";
    TextView preguntaB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leccion);

        requestQeue = Volley.newRequestQueue(this);
        int categoria = getIntent().getExtras().getInt("category");
        //Toast.makeText(getApplicationContext(), categoria + "", Toast.LENGTH_SHORT).show();
        numero = ThreadLocalRandom.current().nextInt(0, 5 );
        URL_QUERY = "https://denniscetina.000webhostapp.com/fetch2.php?id=" + numero + "&category="+categoria+"";
        //Toast.makeText(getApplicationContext(), numero + "", Toast.LENGTH_SHORT).show();
        ImageView imageViewBtn = (ImageView) findViewById(R.id.verificarBtn);
        ImageView imageViewBalam = (ImageView) findViewById(R.id.Balam);
        RadioButton rb1 = (RadioButton) findViewById(R.id.radioButton1);
        RadioButton rb2 = (RadioButton) findViewById(R.id.radioButton2);
        RadioButton rb3 = (RadioButton) findViewById(R.id.radioButton3);
        RadioButton rb4 = (RadioButton) findViewById(R.id.radioButton4);
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.ProgressBar);
        preguntaB = (TextView) findViewById(R.id.PreguntaB);
        vidaR = (TextView) findViewById(R.id.vidasRes);
        progressBar.setProgress(counter);
        progressBar.setMax(5);

        Cronometro cronos = new Cronometro();
        Thread crononometro = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    if(comenzarCrono=true){
                        try {
                            Thread.sleep(1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        mili++;
                        if(mili==999){
                            seg++;
                            mili=0;
                        }
                        if(seg==59){
                            min++;
                            seg=0;
                        }

                        h.post(new Runnable() {
                            @Override
                            public void run() {
                                if (mili < 10 ) {
                                    m="00"+mili;
                                }else{
                                    if (mili < 100) {
                                        m="0"+mili;
                                    }else{
                                        m=""+mili;
                                    }

                                }
                                if (seg < 10) {
                                    s="0"+seg;
                                }else{
                                    s=""+seg;
                                }
                                if (min < 10) {
                                    mi="0"+min;
                                }else{
                                    mi=""+min;
                                }

                                cronos.setMilisegundos(m);
                                cronos.setSegundos(s);
                                cronos.setMinutos(mi);
                                //Toast.makeText(getApplicationContext(), cronos.getMilisegundos()+":"+cronos.getSegundos()+":"+cronos.getMinutos()+":" , Toast.LENGTH_SHORT).show();


                            }



                        });


                        //



                        //

                    }
                }

            }
        });
        crononometro.start();



        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_QUERY, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {


                    preguntaB.setText(response.getString("Pregunta"));
                    rb1.setText(response.getString("Respuesta"));
                    rb2.setText(response.getString("Alternativo1"));
                    rb3.setText(response.getString("Alternativo2"));
                    rb4.setText(response.getString("Alternativo3"));
                    respuesta = response.getString("Respuesta");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        imageViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;

                progressBar.setProgress(counter);
                progressBar.setMax(6);

                if (rb1.isChecked()) {
                    respuestaComp = rb1.getText().toString();

                } else {
                    if (rb2.isChecked()) {
                        respuestaComp = rb2.getText().toString();
                    } else {
                        if (rb3.isChecked()) {
                            respuestaComp = rb3.getText().toString();
                        } else {
                            if (rb4.isChecked()) {
                                respuestaComp = rb4.getText().toString();
                            }
                        }
                    }
                }
                //Toast.makeText(getApplicationContext(), respuestaComp, Toast.LENGTH_SHORT).show();


                numero = ThreadLocalRandom.current().nextInt(0, 5);

                //Toast.makeText(getApplicationContext(), numero + "", Toast.LENGTH_SHORT).show();
                URL_QUERY = "https://denniscetina.000webhostapp.com/fetch2.php?id=" + numero + "&category="+categoria+"";
                jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_QUERY, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            TextView preguntaB = (TextView) findViewById(R.id.PreguntaB);
                            preguntaB.setText(response.getString("Pregunta"));
                            respuesta = response.getString("Respuesta");
                            int pos;
                            int nCartas = 4;
                            Stack < Integer > pCartas = new Stack< Integer >();
                            for (int i = 0; i < nCartas ; i++) {
                                pos = (int) Math.floor(Math.random() * nCartas );
                                while (pCartas.contains(pos)) {
                                    pos = (int) Math.floor(Math.random() * nCartas );
                                }
                                pCartas.push(pos);
                            }
                            int[] ids_answer ={
                                    R.id.radioButton1,
                                    R.id.radioButton2,
                                    R.id.radioButton3,
                                    R.id.radioButton4
                            };

                            final String answers[]={
                                    a=response.getString("Respuesta"),
                                    b=response.getString("Alternativo1"),
                                    c=response.getString("Alternativo2"),
                                    d=response.getString("Alternativo3")
                            };

                            for (int i = 0; i < ids_answer.length; i++) {
                                rb = (RadioButton)findViewById(ids_answer[i]);
                                rb.setText(answers[pCartas.get(i)]);
                            }
                           // Toast.makeText(getApplicationContext(), pCartas+"", Toast.LENGTH_SHORT).show();
                            //Toast.makeText(getApplicationContext(), answers[2]+"", Toast.LENGTH_SHORT).show();
                           // Toast.makeText(getApplicationContext(), answers[3]+"", Toast.LENGTH_SHORT).show();
                           // Toast.makeText(getApplicationContext(), answers[4]+"", Toast.LENGTH_SHORT).show();
                            /*
                            preguntaB.setText(response.getString("Pregunta"));
                            rb1.setText(response.getString("Respuesta"));
                            rb2.setText(response.getString("Alternativo1"));
                            rb3.setText(response.getString("Alternativo2"));
                            rb4.setText(response.getString("Alternativo3"));
                            respuesta = response.getString("Respuesta");
                            */


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                requestQeue.add(jsonObjectRequest);
                //Toast.makeText(getApplicationContext(), counter + "", Toast.LENGTH_SHORT).show();
                if (respuesta != respuestaComp) {
                   /* if (counter == 6) {
                        imageViewBtn.setEnabled(false);
                    }
                    */
                    Toast.makeText(getApplicationContext(), "Respuesta incorrecta", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(), counter + "", Toast.LENGTH_SHORT).show();
                    imageViewBtn.setImageResource(R.drawable.ic_baseline_cancel_24);
                    imageViewBtn.setEnabled(false);
                    imageViewBalam.setImageResource(R.drawable.balamdormido);
                    vida = (LottieAnimationView)findViewById(R.id.vidaCount);
                    //vida.useHardwareAcceleration();
                    vida.setSpeed(4);
                    vida.playAnimation();
                    String vidaMas,vidaMenos;


                    vidaMas=vidaR.getText().toString();
                    vidasRestantes = (Integer.valueOf(vidaMas))-1;
                    vidaR.setText(vidasRestantes+"");



                    Thread threadRespuestaMala = new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                sleep(2000);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    imageViewBtn.setEnabled(true);
                                    imageViewBalam.setImageResource(R.drawable.balamtranquilo);
                                    imageViewBtn.setImageResource(R.drawable.ic_round_forward_24);
                                    vida.setProgress(0);
                                }
                            });
                        }

                    });
                    threadRespuestaMala.start();

                } else {

                    //Toast.makeText(getApplicationContext(), "No es!", Toast.LENGTH_SHORT).show();
                   // Toast.makeText(getApplicationContext(), counter + "", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "Respuesta correcta", Toast.LENGTH_SHORT).show();
                    imageViewBtn.setImageResource(R.drawable.ic_round_check_circle_24);
                    imageViewBtn.setEnabled(false);
                    imageViewBalam.setImageResource(R.drawable.balamfeliz);




                    Thread threadRespuestaBuena = new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                sleep(2000);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    imageViewBtn.setEnabled(true);
                                    imageViewBalam.setImageResource(R.drawable.balamtranquilo);
                                    imageViewBtn.setImageResource(R.drawable.ic_round_forward_24);
                                }
                            });
                        }

                    });
                    threadRespuestaBuena.start();

                    /*if(buenas==2){
                        imageViewBtn.setEnabled(false);
                    }*/

                    buenas++;
                }



                int vidaO = (Integer.valueOf(vidaR.getText().toString()));
                if(vidaO<=0||counter==6){
                    comenzarCrono=false;
                    String tiempoRec;
                    tiempoRec = cronos.getMinutos()+":"+cronos.getSegundos();
                    Intent intent = new Intent(activityLeccion.this,activityResultados.class);
                    intent.putExtra("progress", (100*buenas)/6);
                    intent.putExtra("time", tiempoRec);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), counter+"", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }

        });

        requestQeue.add(jsonObjectRequest);



    }


}