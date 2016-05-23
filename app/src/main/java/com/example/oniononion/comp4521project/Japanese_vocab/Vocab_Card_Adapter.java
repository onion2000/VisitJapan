package com.example.oniononion.comp4521project.Japanese_vocab;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.oniononion.comp4521project.R;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by oniononion on 2016/04/20.
 */

// This is the adapter of recycler card view for vocab list
public class Vocab_Card_Adapter extends RecyclerView.Adapter<Vocab_Card_Adapter.ViewHolder>  {
    private Context mContext;
    private ArrayList<String> vocab_hiragana ;
    private ArrayList<String> vocab_romaji;
    private ArrayList<String> vocab_meaning;
    private MyItemClickListener mItemClickListener;


    public Vocab_Card_Adapter(Context context,ArrayList<String> vocab_hiragana,ArrayList<String> vocab_romaji,ArrayList<String> vocab_meaning) {
        this.vocab_hiragana= vocab_hiragana;
        this.vocab_romaji=vocab_romaji;
        this.vocab_meaning=vocab_meaning;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
        return new ViewHolder(v,mContext,mItemClickListener);
    }


    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        // set the value of each card view
        if(vocab_hiragana.size()>0) {
            viewHolder.m_vocab_hiragana.setText(vocab_hiragana.get(i).toString());
            viewHolder.m_vocab_romaji.setText(vocab_romaji.get(i).toString());
            viewHolder.m_vocab_meaning.setText(vocab_meaning.get(i).toString());
        }

    }

    @Override
    public int getItemCount() {
        return vocab_hiragana.size();
    }

    public void setOnItemClickListener(MyItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // The content of each card view
        public TextView m_vocab_hiragana;
        public TextView m_vocab_romaji;
        public TextView m_vocab_meaning;
        public final View mView;
        public Context mCon;
        private MyItemClickListener mListener;


        public ViewHolder(View v, Context context, MyItemClickListener listener) {
            super(v);
            mView = v;
            mCon = context;
            m_vocab_hiragana = (TextView) v.findViewById(R.id.vocab_hiragana_text);
            m_vocab_romaji = (TextView) v.findViewById(R.id.vocab_romaji_text);
            m_vocab_meaning = (TextView) v.findViewById(R.id.vocab_meaning_text);
            mListener = listener;
        }

//        @Override
//        public void onClick(View v) {
//            MediaPlayer player = new MediaPlayer();
//            String playuri = "http://translate.google.com/translate_tts?ie=UTF-8&tl=ja&client=tw-ob&" + "q=" + m_vocab_hiragana.toString().replace(" ","%20");
//            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
//            try {
//                player.setDataSource(playuri);
//                player.prepare();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            player.start();
//        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(v, getPosition());
            }
        }

    }
}
