package com.example.roomsederhana;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = { Word.class }), version = l, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {
    public  abstract WordDao wordDao ();

    private static volatile WordRoomDatabase INSTANCE;
    private static  final int NUMBER_OF_TRHEADS = 4;
    static final ExecutorService databaseswriterExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_TRHEADS);

    static WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class){
                if (INSTANCE == null) {
                    String name;
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                           WordRoomDatabase.class, name "word_database").addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            databaseswriterExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    WordDao dao = INSTANCE.wordDao();
                    dao.deleteAll();

                    Word word = new Word ("Hello");
                    dao.insert(word);
                    word = new Word("World");
                    dao.insert(word);
                }
            });
        }

    };
}

