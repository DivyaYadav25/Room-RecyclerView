package com.example.room

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.room.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),WordListAdapter.UpdateWordListener {

    lateinit var oldWord:String
    lateinit var oldMeaning:String
    lateinit var mBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        //initialize recycler view of entries
        val adapter = initRecycler()

        val wordViewModel= ViewModelProviders.of(this).get(WordViewModel::class.java)
        wordViewModel.allWords.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.setWords(it) }
            //delete all entries
            mBinding.deleteAllButton.setOnClickListener{
                wordViewModel.deleteEntries()
            }
        })
        //add new entry
        fab_add!!.setOnClickListener {
            startActivity(Intent(this, NewWordActivity::class.java))
        }

    }

    private fun initRecycler(): WordListAdapter {
        val adapter = WordListAdapter(this, this)
        mBinding.recyclerview.adapter = adapter
        mBinding.recyclerview.layoutManager = LinearLayoutManager(this)
        return adapter
    }

    //update an entry
    override fun updateWord(position: Int) {
        val intent =Intent(this, UpdateNewWordActivity::class.java)
        intent.putExtra("old_word",oldWord)
        startActivity(intent)
    }

    override fun getOldWord(word: String, meaning: String) {
        oldWord=word
        oldMeaning= meaning
    }

}
