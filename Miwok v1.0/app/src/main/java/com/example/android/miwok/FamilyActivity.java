package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    private AudioManager audioManager;
    private AudioFocusRequest focusRequest;

    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    private AudioManager.OnAudioFocusChangeListener focusListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            switch (i) {
                case AudioManager.AUDIOFOCUS_GAIN:
                    mediaPlayer.start();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    releaseMediaPlayer();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK: {
                    mediaPlayer.pause();
                    mediaPlayer.seekTo(0);
                }
                break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        focusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK)
                .setAcceptsDelayedFocusGain(true)
                .setOnAudioFocusChangeListener(focusListener)
                .build();

        final ArrayList<Word> words = new ArrayList<Word>(){{
            add(new Word("father","әpә",R.drawable.family_father, R.raw.family_father));
            add(new Word("mother","әṭa",R.drawable.family_mother, R.raw.family_mother));
            add(new Word("son","angsi",R.drawable.family_son, R.raw.family_son));
            add(new Word("daughter","tune",R.drawable.family_daughter, R.raw.family_daughter));
            add(new Word("older brother","taachi",R.drawable.family_older_brother, R.raw.family_older_brother));
            add(new Word("younger brother","chalitti",R.drawable.family_younger_brother, R.raw.family_younger_brother));
            add(new Word("older sister","teṭe",R.drawable.family_older_sister, R.raw.family_older_sister));
            add(new Word("younger sister","kolliti",R.drawable.family_younger_sister, R.raw.family_younger_sister));
            add(new Word("grandmother","ama",R.drawable.family_grandmother, R.raw.family_grandmother));
            add(new Word("grandfather","paapa",R.drawable.family_grandfather, R.raw.family_grandfather));
        }};

        WordAdapter itemsAdapter = new WordAdapter(this, words,R.color.category_family);

        ListView listView = findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                releaseMediaPlayer();

                int res = audioManager.requestAudioFocus(focusRequest);
                if (res == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(FamilyActivity.this, words.get(i).getAudioResourceId());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(completionListener);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        releaseMediaPlayer();
    }

    private boolean releaseMediaPlayer(){
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
            audioManager.abandonAudioFocusRequest(focusRequest);
            return true;
        }
        return false;
    }
}
