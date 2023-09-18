package com.example.quizyapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.quizyapp.R
import com.google.firebase.auth.FirebaseAuth

class LoginIntro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_intro)
        val auth:FirebaseAuth=FirebaseAuth.getInstance()
        if(auth.currentUser!=null){
            Toast.makeText(this,"User is Already LoggedIn",Toast.LENGTH_SHORT).show()
            redirect("MAIN")
            }

       val  btnGetStarted=findViewById<Button>(R.id.btnGetstarted)

        btnGetStarted.setOnClickListener {
           redirect("LOGIN")

        }

    }
    private fun redirect(name:String){
        val intent:Intent=when(name){
            "LOGIN"-> Intent(this, LogInActivity::class.java)
            "MAIN"->Intent(this, MainActivity::class.java)
            else ->throw Exception("No Path Exists")
        }
        startActivity(intent)
        finish()


    }



}