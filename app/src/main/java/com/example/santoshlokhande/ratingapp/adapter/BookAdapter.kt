package com.example.santoshlokhande.ratingapp.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.AppCompatRatingBar
import android.widget.RelativeLayout
import com.example.santoshlokhande.ratingapp.R
import com.example.santoshlokhande.ratingapp.activity.MainActivity
import com.example.santoshlokhande.ratingapp.db.entity.Book
import kotlin.collections.ArrayList

class BookAdapter(val applicationContext: Context) : RecyclerView.Adapter<BookAdapter.BookHolder>() {

    private var books: List<Book> = ArrayList()
    lateinit var topholder: BookHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.book_item, parent, false)

        return BookHolder(itemView)

    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) {

        topholder = holder

        val currentNote = books[position]
        holder.textViewTitle.text = currentNote.title
        holder.retingBar.rating = currentNote.rating.toFloat()


        holder.mainRel.setOnClickListener {

            (applicationContext as MainActivity).itemClick(currentNote)

        }

    }

    override fun getItemCount(): Int {
        return books.size
    }

    fun setBooks(books: List<Book>) {
        this.books = books.sortedByDescending { books -> books.rating }
        notifyDataSetChanged()
    }

    class BookHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textViewTitle: TextView = itemView.findViewById(R.id.text_view_title)
        var retingBar: AppCompatRatingBar = itemView.findViewById(R.id.retingBar)
        var mainRel: ConstraintLayout = itemView.findViewById(R.id.mainRel);


    }

}
