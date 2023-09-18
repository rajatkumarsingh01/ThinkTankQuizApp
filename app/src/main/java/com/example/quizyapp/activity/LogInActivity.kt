package com.example.quizyapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.quizyapp.R
import com.google.firebase.auth.FirebaseAuth

class LogInActivity : AppCompatActivity() {
    lateinit var  firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        val email=findViewById<EditText>(R.id.logInemail)
        val pass=findViewById<EditText>(R.id.logINPass)
        val logInBtn=findViewById<Button>(R.id.logInBtn)
        firebaseAuth=FirebaseAuth.getInstance()

        val signUpPage=findViewById<TextView>(R.id.signUpText)





        signUpPage.setOnClickListener {
            val intent= Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()

        }


        logInBtn.setOnClickListener {
           val logInemail=email.text.toString()
            val logInpass=pass.text.toString()


            if(logInemail.isEmpty()||logInpass.isEmpty()){
                Toast.makeText(this,"Fill the required field",Toast.LENGTH_SHORT).show()
                return@setOnClickListener


            }
            firebaseAuth.signInWithEmailAndPassword(logInemail,logInpass).addOnCompleteListener(this){
                if(it.isSuccessful){
                    Toast.makeText(this,"LogIn Successful",Toast.LENGTH_SHORT).show()
                    val intent= Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()

                }
                else{
                    Toast.makeText(this,"LogIn Failed",Toast.LENGTH_SHORT).show()

                }
            }



        }











    }
}