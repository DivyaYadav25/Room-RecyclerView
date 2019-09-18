package com.example.room

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Divya Yadav on 08-Aug-19.
 */
class WordListAdapter internal constructor(context: Context, private val updateWordListener: UpdateWordListener) :
    RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private  var words: MutableList<Word> = ArrayList<Word>()

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordTv: TextView = itemView.findViewById(R.id.tv_word)
        val meaningTv: TextView = itemView.findViewById(R.id.tv_meaning)
        val updateWordIcon: TextView = itemView.findViewById(R.id.tv_edit)

        init{
            wordTv.setOnClickListener{
                removeWord(adapterPosition)
            }
            updateWordIcon.setOnClickListener {
                updateWordListener.getOldWord(wordTv.text.toString(),meaningTv.text.toString())
                updateWordListener.updateWord(adapterPosition)
            }
        }

    }

    //listener for replacing old entry by new
    interface UpdateWordListener{
        fun getOldWord(word:String,meaning:String)
        fun updateWord(position: Int)
    }
    private fun removeWord(adapterPosition: Int) {
        words.removeAt(adapterPosition)
        notifyItemRemoved(adapterPosition)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = words[position]
        holder.wordTv.text = current.word
        holder.meaningTv.text = current.meaning
    }

    internal fun setWords(words: MutableList<Word>) {
        this.words = words
        notifyDataSetChanged()
    }

    override fun getItemCount() = words.size
}