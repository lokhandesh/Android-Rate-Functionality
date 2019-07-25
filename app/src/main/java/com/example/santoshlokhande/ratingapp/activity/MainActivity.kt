package com.example.santoshlokhande.ratingapp.activity

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.os.Handler
import android.support.v7.app.ActionBar
import android.view.*
import android.widget.*
import com.example.santoshlokhande.ratingapp.R
import com.example.santoshlokhande.ratingapp.R.id.tvTitle
import com.example.santoshlokhande.ratingapp.adapter.BookAdapter
import com.example.santoshlokhande.ratingapp.db.entity.Book
import com.example.santoshlokhande.ratingapp.utility.showCustomDialogView
import com.example.santoshlokhande.ratingapp.viewmodel.BookViewModel


class MainActivity : AppCompatActivity() {

    lateinit var progressBar: ProgressBar
    var bookList = listOf<Book>()
    private val handler = Handler()
    var started = false
    private lateinit var bookViewModel: BookViewModel
    private val adapter = BookAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar?.setCustomView(R.layout.header);

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val randomRating = findViewById(R.id.randomRating) as TextView
        progressBar = findViewById(R.id.progressBar)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)
        bookViewModel.getAllBooks().observe(this,
                Observer<List<Book>> { t ->
                    adapter.setBooks(t!!)
                    bookList = t

                })

        randomRating.setOnClickListener {

            if (!started) {
                started = true
                progressBar.visibility = View.VISIBLE
                handler.postDelayed(runnable, 3000);
            } else {
                started = false
                progressBar.visibility = View.INVISIBLE
                handler.removeCallbacks(runnable)
            }

        }
    }

    fun itemClick(currentbook: Book) {
       // showDialog(currentNote)
        showCustomDialogView(this,currentbook,bookViewModel)
    }

    private val runnable = object : Runnable {

        override fun run() {

            val currentBook = bookList[bookViewModel.getRandomValue(10)]

            val updateRate = Book(currentBook.title,(bookViewModel.getRandomValue(5)+1).toFloat())
            bookViewModel.update(updateRate)

            handler.postDelayed(this, 3000)
        }
    }

    override fun onPause() {
        super.onPause()
        started = false
        progressBar.visibility = View.INVISIBLE
        handler.removeCallbacks(runnable)
    }


}