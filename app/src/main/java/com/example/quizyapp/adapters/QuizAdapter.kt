package com.example.quizyapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizyapp.R
import com.example.quizyapp.activity.QuestionActivity
import com.example.quizyapp.models.Quiz
import com.example.quizyapp.utils.colorPicker
import com.example.quizyapp.utils.colorPicker.colors
import com.example.quizyapp.utils.iconPicker

class QuizAdapter (val context: Context,val quizzes:List<Quiz>): RecyclerView.Adapter<QuizAdapter.QuizViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):QuizViewHolder {
        val view=   LayoutInflater.from(context).inflate(R.layout.item_quiz,parent,false)
        return QuizViewHolder(view)

    }


    override fun getItemCount(): Int {
       return quizzes.size

    }


    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.textViewTitle.text=quizzes[position].title
        holder.cardContainer.setCardBackgroundColor(Color.parseColor(colorPicker.getColor()))
        holder.iconView.setImageResource(iconPicker.getIcon())
        holder.itemView.setOnClickListener {
            Toast.makeText(context,quizzes[position].title,Toast.LENGTH_SHORT).show()
            val intent =Intent(context,QuestionActivity::class.java)
            intent.putExtra("DATE",quizzes[position].title)
            context.startActivity(intent)


        }



    }



    inner class QuizViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var textViewTitle:TextView = itemView.findViewById(R.id.quizTitle)
        var iconView:ImageView=itemView.findViewById(R.id.quizIcon)
        var cardContainer:CardView=itemView.findViewById(R.id.CardContainer)

    }


}