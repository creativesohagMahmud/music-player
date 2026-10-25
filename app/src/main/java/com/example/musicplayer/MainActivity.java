package com.example.musicplayer; // আপনার প্রোজেক্টের প্যাকেজ নাম অনুযায়ী এটি অটোমেটিক থাকবে, পরিবর্তন করবেন না।

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Button btnPlay, btnPause, btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI বাটনগুলোর সাথে জাভা কোডের লিংক করা
        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        btnStop = findViewById(R.id.btnStop);

        // MediaPlayer-এ আপনার আপলোড করা গানটি অ্যাসাইন করুন
        // (এখানে 'my_song' হলো আপনার res/raw ফোল্ডারের mp3 ফাইলের নাম)
        mediaPlayer = MediaPlayer.create(this, R.raw.my_song);

        // Play বাটনের কাজ
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                    Toast.makeText(MainActivity.this, "Music Playing", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Pause বাটনের কাজ
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    Toast.makeText(MainActivity.this, "Music Paused", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Stop বাটনের কাজ
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    // স্টপ করার পর পুনরায় প্লে করার জন্য মিডিয়া প্লেয়ার রিলোড করতে হয়
                    mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.my_song);
                    Toast.makeText(MainActivity.this, "Music Stopped", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // অ্যাপ বন্ধ হয়ে গেলে ব্যাকগ্রাউন্ডে গান যেন চলতে না থাকে, তাই মেমোরি ক্লিয়ার করা
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
