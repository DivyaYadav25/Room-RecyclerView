package com.example.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by Divya Yadav on 07-Aug-19.
 */
@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM word_table")
    fun deleteAll()

    @Query("DELETE FROM word_table WHERE word=:word")
    fun deleteByWord(word: String)

    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getAllWords() : LiveData<MutableList<Word>>

    @Query("UPDATE word_table SET word=:newWord, meaning=:newMeaning WHERE word=:oldWord")
    fun updateWord(oldWord: String,newWord:String,newMeaning:String)
}