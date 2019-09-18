package com.example.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Divya Yadav on 07-Aug-19.

 */
@Entity(tableName = "word_table")
data class Word(
    @PrimaryKey @ColumnInfo(name = "word")val word: String,
    @ColumnInfo(name="meaning")val meaning:String)