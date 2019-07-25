package com.example.santoshlokhande.ratingapp.utility

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import com.example.santoshlokhande.ratingapp.R
import com.example.santoshlokhande.ratingapp.db.entity.Book
import com.example.santoshlokhande.ratingapp.viewmodel.BookViewModel

 fun showCustomDialogView(context: Context,currentNote: Book,bookViewModel:BookViewModel) {

    val dialogView = LayoutInflater.from(context).inflate(R.layout.rate_dialog, null, false)

    val builder = android.support.v7.app.AlertDialog.Builder(context)

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