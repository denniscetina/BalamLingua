package com.example.balamlingua.ui.home;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.balamlingua.R;

import org.json.JSONObject;

import java.io.IOException;

public class activityApuntesSaludos extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    RequestQueue requestQeue;
    JsonObjectRequest jsonObjectRequest;
    String URL_QUERY_AUDIO;
    String audioUrl;
    Handler h = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apuntes_saludos);

        requestQeue = Volley.newRequestQueue(this);
        ImageView audio1 = (ImageView) findViewById(R.id.audio1);
        TextView txt1 = (TextView) findViewById(R.id.audioText1);

        audio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                audio1.setEnabled(false);
                playAudio(txt1.getText().toString());
                Thread threadAudio = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            sleep(4000);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                audio1.setEnabled(true);
                            }
                        });
                    }

                });
                threadAudio.start();



            }
        });



    }
    private void playAudio(String audio) {

        //String audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3";

       /* if (mediaPlayer.isPlaying()) {
            // pausing the media player if media player
            // is playing we are calling below line to
            // stop our media player.
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();

            // below line is to display a message
            // when media player is paused.
        }*/


        URL_QUERY_AUDIO = "http://192.168.1.12/BalamLingua/audio.php?audio=" + audio + "";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_QUERY_AUDIO, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    ///
                   audioUrl = response.getString("url_audio");
                    mediaPlayer = new MediaPlayer();

                    // below line is use to set the audio
                    // stream type for our media player.
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

                    // below line is use to set our
                    // url to our media player.
                    try {
                        mediaPlayer.setDataSource(audioUrl);
                        // below line is use to prepare
                        // and start our media player.
                        mediaPlayer.prepare();
                        mediaPlayer.start();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // below line is use to display a toast message.
                    //Toast.makeText(this, "Audio started playing..", Toast.LENGTH_SHORT).show();

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
                // initializing media player

    }
}