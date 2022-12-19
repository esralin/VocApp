package com.example.vocapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Word::class], version = 4, exportSchema = true)
abstract class WordDatabase : RoomDatabase() {
    abstract val wordDao : WordDao
    companion object {
        @Volatile
        private var INSTANCE: WordDatabase? = null
        fun getInstance(context: Context): WordDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        WordDatabase::class.java,
                        "word_database"
                    ).createFromAsset("database/word.db").allowMainThreadQueries().fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}