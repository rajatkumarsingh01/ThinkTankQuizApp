package com.example.quizyapp.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import com.example.quizyapp.R
import com.example.quizyapp.models.Quiz
import com.google.gson.Gson

class ResultActivity : AppCompatActivity() {
    lateinit var quiz: Quiz

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        setUpViews()


    }

    private fun setUpViews() {
        val quizData=intent.getStringExtra("QUIZ")
         quiz=Gson().fromJson<Quiz>(quizData,Quiz::class.java)
        calculateScore()
        setAnswerView()

    }

    private fun setAnswerView() {
        val builder=StringBuilder("")
        for(entry in quiz.questions.entries){
            val question=entry.value
            builder.append("<font color '#18206F'><b>Question:  ${question.description}</b></font><br/><br/>")
            builder.append("<font color '#01F9C6'>Answer:  ${question.answer}</font><br/><br/>")
            builder.append("<font color '#FF0000'>UserAnswer:  ${question.userAnswer}</font><br/><br/>")

        }
        val txtAnswer=findViewById<TextView>(R.id.txtAnswer)
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){

            txtAnswer.text=Html.fromHtml(builder.toString(),Html.FROM_HTML_MODE_COMPACT);

        }
        else{
            txtAnswer.text=Html.fromHtml(builder.toString());


        }


    }


    private fun calculateScore() {
        var score=0
        val txtScore=findViewById<TextView>(R.id.txtScore)

        for (entry in quiz.questions.entries){

            val question=entry.value
            if(question.answer==question.userAnswer){
                score+= 10

            }
        }

        txtScore.text= "Your Score is :$score"

    }

    override fun onBackPressed() {
        startActivity(Intent(this,MainActivity::class.java))
        super.onBackPressed()
    }
}