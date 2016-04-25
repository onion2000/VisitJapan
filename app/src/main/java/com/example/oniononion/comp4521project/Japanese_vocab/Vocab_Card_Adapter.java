package com.example.oniononion.comp4521project.Japanese_vocab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.oniononion.comp4521project.R;

import java.util.ArrayList;

/**
 * Created by oniononion on 2016/04/20.
 */

// This is the adapter of recycler card view for vocab list
public class Vocab_Card_Adapter extends RecyclerView.Adapter<Vocab_Card_Adapter.ViewHolder> {
    private Context mContext;
    private ArrayList<String> vocab_hiragana ;
    private ArrayList<String> vocab_romaji;
    private ArrayList<String> vocab_meaning;


    public Vocab_Card_Adapter(Context context,ArrayList<String> vocab_hiragana,ArrayList<String> vocab_romaji,ArrayList<String> vocab_meaning) {
        this.vocab_hiragana= vocab_hiragana;
        this.vocab_romaji=vocab_romaji;
        this.vocab_meaning=vocab_meaning;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
        return new ViewHolder(v,mContext);
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


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // The content of each card view
        public TextView m_vocab_hiragana;
        public TextView m_vocab_romaji;
        public TextView m_vocab_meaning;
        public final View mView;
        public Context mCon;

        public ViewHolder(View v, Context context) {
            super(v);
            mView = v;
            mCon = context;
            m_vocab_hiragana = (TextView) v.findViewById(R.id.vocab_hiragana_text);
            m_vocab_romaji = (TextView) v.findViewById(R.id.vocab_romaji_text);
            m_vocab_meaning = (TextView) v.findViewById(R.id.vocab_meaning_text);
        }

    }
}
