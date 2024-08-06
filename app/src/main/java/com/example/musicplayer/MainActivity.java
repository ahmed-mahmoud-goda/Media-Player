package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageButton btn_play ,btn_stop,btn_forward,btn_backward;
    private boolean started = false;
    private int[] songs = {R.raw.song0, R.raw.song1, R.raw.song2};
    private String[] songNames = {"Song 0","Song 1","Song 2"};
    private int current = 0;
    private SeekBar bar1;
    private TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_play = findViewById(R.id.startBTN);
        btn_stop = findViewById(R.id.stopBTN);
        btn_forward = findViewById(R.id.forward);
        btn_backward = findViewById(R.id.backward);
        txt1 = findViewById(R.id.songName);
        bar1 = findViewById(R.id.seekBar);


        final MediaPlayer[] mp = {MediaPlayer.create(getBaseContext(), songs[current])};
        bar1.setMax(mp[0].getDuration());
        txt1.setText(songNames[current]);
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!started) {
                    mp[0].start();
                    btn_play.setImageResource(R.drawable.pause);
                    started = true;
                }
                else{
                    mp[0].pause();
                    btn_play.setImageResource(R.drawable.start);
                    started = false;
                }
            }
        });
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp[0].stop();
                btn_play.setImageResource(R.drawable.start);
                started = false;
            }
        });
        btn_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp[0].stop();
                current = (current+1)%3;
                mp[0] = MediaPlayer.create(getBaseContext(),songs[current]);
                mp[0].start();
                bar1.setMax(mp[0].getDuration());
                bar1.setProgress(0);
                btn_play.setImageResource(R.drawable.pause);
                txt1.setText(songNames[current]);
            }
        });
        btn_backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp[0].stop();
                if(current == 0){
                    current = 2;
                }
                else{
                    current = (current-1);
                }
                mp[0] = MediaPlayer.create(getBaseContext(),songs[current]);
                mp[0].start();
                bar1.setMax(mp[0].getDuration());
                bar1.setProgress(0);
                btn_play.setImageResource(R.drawable.pause);
                txt1.setText(songNames[current]);
            }
        });
        bar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mp[0].seekTo(bar1.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
