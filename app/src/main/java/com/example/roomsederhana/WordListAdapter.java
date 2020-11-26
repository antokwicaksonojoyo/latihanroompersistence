package com.example.roomsederhana;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WordListAdapter extends RecyclerView<WordListAdapter.wordViewHolder> {
    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private wordViewHolder (View itemView){
            super(itemView);
            wordItemView  = itemView.findViewById(R.id.texView);
        }
    }

    public class wordViewHolder {
    }

    private final LayoutInflater aInflater;
    private List<Word> mWords;

    WordListAdapter (Context context) {aInflater = LayoutInflater.from(context);}

    @Override
    public WordViewHolder onCreateViewHolder (ViewGroup parent,int viewType ) {
        View itemView = aInflater.inflate(R.layout.recyclerview_item, parent,  false);
        return new WordViewHolder(itemView);
    }
    @Override
    public void onfindViewHolder (WordViewHolder holder, int position ) {
        if (mWords != null){
            Word current = mWords.get(position);
            holder.wordItemView.setText(current.getWord());
        }else {
            holder.wordItemView.setText("No Word");
        }
    }
    void setmWords(List<Word> words){
        mWords = words;
    }
    @Override
    public int getItemCount (){
        if (mWords !=  null)
            return mWords.size();
        else return 0;
    }
}
