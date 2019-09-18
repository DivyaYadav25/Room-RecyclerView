package com.example.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Divya Yadav on 08-Aug-19.

 */
class WordViewModel (application: Application): AndroidViewModel(application){
    private val repository: WordRepository
    val allWords: LiveData<MutableList<Word>>

    init {
        val wordsDao = WordRoomDatabase.getDatabase(application, viewModelScope).wordDao()
        repository = WordRepository(wordsDao)
        allWords = repository.allWords
    }
    fun insert(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(word)
    }

    fun deleteEntries()= viewModelScope.launch (Dispatchers.IO){
        repository.deleteEntries()
    }
    fun updateWord(oldWord: String,newWord: String,newMeaning:String)= viewModelScope.launch (Dispatchers.IO){
        repository.updateWord(oldWord,newWord,newMeaning)
    }
}