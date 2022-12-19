package com.example.vocapp


import androidx.room.*


@Dao
interface WordDao {



   @Query("SELECT * FROM word_table WHERE level = :key ORDER BY RANDOM() LIMIT 1")
   fun getQuestion(key: String): Word

   @Query("SELECT * FROM word_table WHERE definition = :key")
   fun getWordByDefinition(key: String): Word

    @Query("SELECT * FROM word_table WHERE level = :key ORDER BY RANDOM() LIMIT 1")
    fun getQuestNew(key: String): Word


}