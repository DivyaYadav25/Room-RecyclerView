package com.example.room

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

/**
 * Created by Divya Yadav on 07-Aug-19.
 */
class WordRepository(private val wordDao: WordDao) {
    val allWords: LiveData<MutableList<Word>> = wordDao.getAllWords()
   // @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

    fun deleteEntries(){
        wordDao.deleteAll()
    }
    fun updateWord(oldWord: String,newWord: String,newMeaning:String){
        wordDao.updateWord(oldWord,newWord,newMeaning)
    }
}