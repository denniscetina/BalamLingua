package com.example.balamlingua;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class activityResultados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        String tiempo = getIntent().getExtras().getString("time");
        int progreso = getIntent().getExtras().getInt("progress");
        TextView time = (TextView) findViewById(R.id.TiempoTransc);
        TextView progress = (TextView) findViewById(R.id.progresoFinal);
        TextView seccionB = (TextView) findViewById(R.id.seccionC);
        ImageView balamR = (ImageView)findViewById(R.id.balamResultados);


        if(progreso<=70){

            seccionB.setText("No se completó esta sección");
            balamR.setImageResource(R.drawable.balamdormido);
        }
            time.setText(tiempo);
            progress.setText(progreso+"%");



    }
}