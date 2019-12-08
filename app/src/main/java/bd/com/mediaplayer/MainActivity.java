package bd.com.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;


import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    PlayerView playerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // SurfaceView surfaceView = findViewById(R.id.surfaceView);
        //  SurfaceHolder surfaceHolder = surfaceView.getHolder();

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                11);
        playerView = findViewById(R.id.pview);
        //  surfaceHolder.addCallback(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(this);
        playerView.setPlayer(player);
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "MediaPlayer"));
// This is the MediaSource representing the media to be played.
        MediaSource videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse("/storage/emulated/0/DCIM/noaudio.ts"));
// Prepare the player with the source.
        player.prepare(videoSource);
        player.setPlayWhenReady(true);
//https://drive.google.com/file/d/1JsqLpwFFEwIdTTRhG7T7SZWEEabDPkTg/view?usp=sharing

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        final MediaPlayer mp = new MediaPlayer();


        try {
            mp.setDataSource("/storage/emulated/0/DCIM/fsi.mp4");
        } catch (IOException e) {
            Log.e("dododo", e.toString());
        }
        mp.setSurface(surfaceHolder.getSurface());

        try {
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mp.start();
            }
        });

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
