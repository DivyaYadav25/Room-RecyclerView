package com.example.room

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.room.databinding.ActivityNewWordBinding

class NewWordActivity : AppCompatActivity() {

    lateinit var mBinding : ActivityNewWordBinding

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_new_word)

        val wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)

        //add new entry and observe changes by word view model
        wordViewModel.allWords.observe(this, Observer { words ->
            mBinding.buttonSave.setOnClickListener{
                wordViewModel.insert(Word((mBinding.editWord.text.toString()),mBinding.editMeaning.text.toString()))
                finish()
            }
        })
    }

}
