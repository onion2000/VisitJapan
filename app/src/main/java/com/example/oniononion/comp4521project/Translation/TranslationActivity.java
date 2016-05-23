package com.example.oniononion.comp4521project.Translation;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaDataSource;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oniononion.comp4521project.R;
import com.example.oniononion.comp4521project.ToolbarInstaller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ylcheung on 18/5/16.
 */
public class TranslationActivity extends AppCompatActivity {
    private static final String TAG = TranslationActivity.class.getSimpleName();
    private final String uri = "http://www.systranet.com/dictionary/english-japanese/";
    private final String gguri = "http://translate.google.com/translate_tts?ie=UTF-8&tl=ja&client=tw-ob&";
    private String jptrans;
    EditText input;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Transition mEnterTran =
                TransitionInflater.from(this).
                        inflateTransition(R.transition.enter_transition);
        Transition mReturnTran =
                TransitionInflater.from(this).
                        inflateTransition(R.transition.return_transition);

        getWindow().setEnterTransition(mEnterTran);
        getWindow().setReturnTransition(mReturnTran);

        setContentView(R.layout.translation_activity);

        ToolbarInstaller.installOnActivity(this);

        input = (EditText) findViewById(R.id.transinput);

        Button translate = (Button) findViewById(R.id.transbtn);
        translate.setOnClickListener(buttonClickListener);
        Button pronounce = (Button) findViewById(R.id.playbtn);
        pronounce.setOnClickListener(buttonClickListener);

        result = (TextView) findViewById(R.id.transoutput);

    }

    private void getTranslation() throws InterruptedException {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
            Document doc = null;
            String transurl = uri + input.getText().toString().replace(" ","%20");
            try {
                doc = Jsoup.connect(transurl).timeout(5000).get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try{
                Element translation = doc.getElementsByClass("dl_target_word").first();
                jptrans = translation.text();
            }catch(Exception e){
                jptrans = "Error 404.";
            }
            }
        });

        thread.start();
        thread.join();
        result.setText(jptrans);

    }

    private void playSound() throws Exception {
        MediaPlayer player = new MediaPlayer();
        String playuri = gguri + "q=" + result.getText().toString().replace(" ","%20");
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setDataSource(playuri);
        player.prepare();
        player.start();
    }

    protected View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.transbtn:
                    try {
                        getTranslation();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.playbtn:
                    try {
                        playSound();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;

            }
        }
    };


}
