package com.example.roomsederhana;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")

    private String word;
    public word (NonNull String word) {this word = word;}
    public String getWord {return this.word;}
}
