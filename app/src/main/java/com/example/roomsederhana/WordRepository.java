package com.example.roomsederhana;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class WordRepository {
    private WordDao mwordDao;
    private LiveData <List<Word>> mAllWords;

    WordRepository(Application application){
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mwordDao = db.wordDao();
        mAllWords = mwordDao.getAlphabeticWord();
    }
    LiveData<List<Word>> getAllWords() {return mAllWords;}
    void insert  {final Word word} {
        WordRoomDatabase.databaseswriterExecutor.execute(new Runnable() {
            @Override
            public void run() {
               mwordDao.insert(word);
            }
        });
    }
}
