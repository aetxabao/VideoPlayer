package com.pmdm.videoplayer;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

/*
            android:screenOrientation="landscape"
 */
public class MainActivity extends AppCompatActivity {

    Button btn;
    VideoView vv;
    String uriPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uriPath = "android.resource://" + getPackageName() + "/" + R.raw.miau;
        btn = (Button)findViewById(R.id.BtnPlay);
        vv = (VideoView)findViewById(R.id.VV);
        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                btn.setEnabled(true);
                mp.reset();
                vv.stopPlayback();
            }
        });
        vv.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.d("ERROR", "what=" + what + " extra=" + extra);
                uriPath = "android.resource://" + getPackageName() + "/" + R.raw.cat;
                play(btn);
                return true;
            }
        });
        play(btn);
    }

    public void play(View v){
        btn.setEnabled(false);
        Uri uri = Uri.parse(uriPath);
        vv.setVideoURI(uri);
        vv.requestFocus();
        vv.start();
        vv.seekTo(0);
    }

}

