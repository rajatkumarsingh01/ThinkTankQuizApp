package com.example.quizyapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizyapp.R
import com.example.quizyapp.adapters.OptionAdapter
import com.example.quizyapp.models.Question
import com.example.quizyapp.models.Quiz
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson

class QuestionActivity : AppCompatActivity() {

    var quizzes:MutableList<Quiz>?=null
    var questions:MutableMap<String,Question>?=null
    var index=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        setUpFirestore()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun setUpFirestore() {
        val firestore=FirebaseFirestore.getInstance()
        var date=intent.getStringExtra("DATE")

            if(date!=null){
                firestore.collection("quizzes").whereEqualTo("title",date)
                    .get()
                    .addOnSuccessListener {
                        if(it !=null && !it.isEmpty){

                            quizzes=it.toObjects(Quiz::class.java)

                            questions=quizzes!![0].questions
                            bindViews()
                        }

                    }


            }


    }

    private fun bindViews() {
        val btnPrevious=findViewById<Button>(R.id.btnPrevious)
        val btnNext=findViewById<Button>(R.id.btnNext)
        val btnSubmit=findViewById<Button>(R.id.btnSubmit)
         btnPrevious.visibility= View.GONE
        btnSubmit.visibility=View.GONE
        btnNext.visibility=View.GONE

        if(index==1){ //when starts
            btnNext.visibility=View.VISIBLE
        }
        else if (index== questions!!.size ){// when last
            btnSubmit.visibility=View.VISIBLE
            btnPrevious.visibility=View.VISIBLE
        }
        else{ //in-case of middle
            btnPrevious.visibility=View.VISIBLE
            btnNext.visibility=View.VISIBLE
        }
        btnNext.setOnClickListener {
            index++
            bindViews()
        }

        btnPrevious.setOnClickListener {
            index--
            bindViews()

        }
        btnSubmit.setOnClickListener {
            Log.d("Final Quiz",questions.toString())
            val intent = Intent(this,ResultActivity::class.java)
            val json=Gson().toJson(quizzes!![0])
            intent.putExtra("QUIZ",json)
            startActivity(intent)
        }

         val question=questions!!["question$index"]
        question?.let {

            val description=findViewById<TextView>(R.id.description)
            val  optionList=findViewById<RecyclerView>(R.id.optionList)


            description.text=it.description
            val optionAdapter=OptionAdapter(this,it)
            optionList.layoutManager=LinearLayoutManager(this)
            optionList.adapter=optionAdapter
            optionList.setHasFixedSize(true)

        }





    }
}