package com.tosh.africaknow.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.tosh.africaknow.R
import com.tosh.africaknow.model.Answer
import com.tosh.africaknow.model.Question

class QuestionAdapter(private val questions: List<Answer>, private val clickListener: (List<Answer>) -> Unit) :  RecyclerView.Adapter<QuestionAdapter.QuestionView>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.answer_item, parent, false)

        return QuestionView(view)
    }

    override fun getItemCount() = questions.size

    override fun onBindViewHolder(holder: QuestionView, position: Int) {

        holder.bind(questions, clickListener)

    }

    class QuestionView(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind( question: List<Answer>,clickListener: (List<Answer>) -> Unit ){
            val answerBtn:Button = itemView.findViewById(R.id.answerBtn)

            answerBtn.text = question[adapterPosition].option
            answerBtn.setOnClickListener {
                clickListener(listOf(question[adapterPosition]))
            }
        }
    }

}