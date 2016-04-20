package com.example.oniononion.comp4521project.Japanese_vocab;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.oniononion.comp4521project.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by oniononion on 2016/04/20.
 */

public class PhrasesFragment extends Fragment {
    private ArrayList<String> vocab_hiragana ;
    private ArrayList<String> vocab_romaji;
    private ArrayList<String> vocab_meaning;
    TextView title;
    private RecyclerView mRecyclerView;
    private static Vocab_Card_Adapter vocab_card_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.vocabulary_activity_phrases_fragment, container, false);
        vocab_hiragana = new ArrayList<>();
        vocab_romaji = new ArrayList<>();
        vocab_meaning = new ArrayList<>();
        getFromText();

        title= (TextView)v.findViewById(R.id.title);
        mRecyclerView = (RecyclerView)v.findViewById(R.id.vocab_list);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(llm);
        vocab_card_adapter = new Vocab_Card_Adapter(getActivity(), vocab_hiragana, vocab_romaji, vocab_meaning);

        mRecyclerView.setAdapter(vocab_card_adapter);

        return v;
    }

    private void getFromText() {
        try {
            Resources res = getResources();
            InputStream in_s = res.openRawResource(R.raw.text_1);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in_s , "UTF8"));
            String line;
            int count=0;
            while ((line = reader.readLine()) != null) {
                if(count%3==0){
                    vocab_hiragana.add(line);
                }else if(count%3==1){
                    vocab_romaji.add(line);
                }else if(count%3==2){
                    vocab_meaning.add(line);
                }
                count++;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }



}
