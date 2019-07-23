package com.example.santoshlokhande.ratingapp.activity

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.os.Handler
import android.support.v7.app.ActionBar
import android.support.v7.app.AlertDialog
import android.view.*
import android.widget.*
import java.util.*
import com.example.santoshlokhande.ratingapp.R
import com.example.santoshlokhande.ratingapp.R.id.tvTitle
import com.example.santoshlokhande.ratingapp.adapter.BookAdapter
import com.example.santoshlokhande.ratingapp.db.entity.Book
import com.example.santoshlokhande.ratingapp.viewmodel.BookViewModel


class MainActivity : AppCompatActivity() {

    val rnd = Random()
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

    fun itemClick(currentNote: Book) {
        showDialog(currentNote)
    }

    private val runnable = object : Runnable {

        override fun run() {
            /* do what you need to do */

            var randomValue = rnd.nextInt(10)
            val currentBook = bookList[randomValue]

            val random = Random().nextInt(5 - 1 + 1) + 1

            val updateRate = Book(currentBook.title, random.toFloat())
            bookViewModel.update(updateRate)

            /* and here comes the "trick" */
            handler.postDelayed(this, 3000)
        }
    }

    private fun showDialog(currentNote: Book) {

        val viewGroup = findViewById<ViewGroup>(android.R.id.content)

        val dialogView = LayoutInflater.from(this).inflate(R.layout.rate_dialog, viewGroup, false)

        val builder = AlertDialog.Builder(this)

        builder.setView(dialogView)

        val dialog = builder.create()

        val bookTitle = dialogView.findViewById(R.id.text_view_title) as TextView
        val retingBar = dialogView.findViewById(R.id.retingBar) as RatingBar
        val rate = dialogView.findViewById(R.id.rate) as Button
        val cancel = dialogView.findViewById(R.id.cancel) as Button

        bookTitle.text = currentNote.title
        retingBar.rating = currentNote.rating.toFloat()

        rate.setOnClickListener {

            val newNote = Book(currentNote.title, retingBar.rating)
            dialog.dismiss()
            bookViewModel.update(newNote)
        }

        cancel.setOnClickListener {

            dialog.dismiss()

        }

        dialog.setCancelable(false)
        dialog.show()
    }

    override fun onPause() {
        super.onPause()
        started = false
        progressBar.visibility = View.INVISIBLE
        handler.removeCallbacks(runnable)
    }


}