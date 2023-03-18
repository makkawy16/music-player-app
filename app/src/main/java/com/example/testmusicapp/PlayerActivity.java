package com.example.testmusicapp;

import static com.example.testmusicapp.MainActivity.musicFiles;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.SeekBar;

import com.example.testmusicapp.databinding.ActivityPlayerBinding;
import com.example.testmusicapp.model.MusicFiles;

import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity {

    ActivityPlayerBinding binding ;

    int position = -1;
    static ArrayList<MusicFiles> listSongs = new ArrayList<>();
    static Uri uri;
    static MediaPlayer mediaPlayer;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentMethod();
        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(mediaPlayer != null && fromUser){
                    mediaPlayer.seekTo(progress*1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

         PlayerActivity.this.runOnUiThread(new Runnable() {
             @Override
             public void run() {
                 if(mediaPlayer != null)
                 {
                     int currentPosition = mediaPlayer.getCurrentPosition()/1000;
                     binding.seekBar.setProgress(currentPosition);
                     binding.durationPlayed.setText(formattedTime(currentPosition));

                 }
                 handler.postDelayed(this , 1000); //oneSecond
             }
         });

    }

    private String formattedTime(int currentPosition) {

        String totalout = "";
        String totalNew = "";
        String seconds = String.valueOf(currentPosition %60);
        String minutes = String.valueOf(currentPosition/60);
        totalout = minutes + ":" + seconds;
        totalNew = minutes + ":" + "0" + seconds;
        if(seconds.length() ==1 ){
            return totalNew;
        }
        else {
           return totalout;
        }
    }

    private void getIntentMethod() {

        position = getIntent().getIntExtra("position" , -1 );
        listSongs = musicFiles;
        if(listSongs != null)
        {
            binding.playPause.setImageResource(R.drawable.ic_pause);
            uri = Uri.parse(listSongs.get(position).getPath());
        }
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(getApplicationContext(),uri);
            mediaPlayer.start();
        }
        else{
            mediaPlayer = MediaPlayer.create(getApplicationContext(),uri);
            mediaPlayer.start();
        }
        binding.seekBar.setMax(mediaPlayer.getDuration()/1000);

    }
}