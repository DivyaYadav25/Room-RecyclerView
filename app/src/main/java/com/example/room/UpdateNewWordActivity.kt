package com.example.room

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.room.databinding.ActivityUpdateNewWordBinding

class UpdateNewWordActivity : AppCompatActivity() {

    lateinit var mBinding : ActivityUpdateNewWordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_update_new_word)

        val wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
        val oldWord= intent.getStringExtra("old_word")

        //update new entry
        wordViewModel.allWords.observe(this, Observer { words ->
            mBinding.buttonReplace.setOnClickListener{
                wordViewModel.updateWord(oldWord,mBinding.editNewWord.text.toString(),mBinding.editNewMeaning.text.toString())
                finish()
            }
        })
    }


}
