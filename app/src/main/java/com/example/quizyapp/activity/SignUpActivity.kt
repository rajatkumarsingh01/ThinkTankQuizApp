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

class SignUpActivity : AppCompatActivity() {
    lateinit var  firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        firebaseAuth= FirebaseAuth.getInstance()

        val LogInpage=findViewById<TextView>(R.id.LogInText)




        val  email=findViewById<EditText>(R.id.signUpemail)
        val password=findViewById<EditText>(R.id.signupPass)
        val confirmPassword=findViewById<EditText>(R.id.confirmPass)
        val signUpBtn=findViewById<Button>(R.id.signUpBtn)

          LogInpage.setOnClickListener {
              val intent=Intent(this, LogInActivity::class.java)
              startActivity(intent)
              finish()
          }



        signUpBtn.setOnClickListener{
          val signUpemail= email.text.toString()
            val signUpPass=password.text.toString()

           val confirmpass= confirmPassword.text.toString()

            if(signUpemail.isEmpty()||signUpPass.isEmpty()||confirmpass.isEmpty()){
                Toast.makeText(this,"Please Fill the required field ",Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
            if(signUpPass!=confirmpass){
                Toast.makeText(this,"Password didn't Match",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            firebaseAuth.createUserWithEmailAndPassword(signUpemail, signUpPass)
                .addOnCompleteListener(this){
                    if(it.isSuccessful){
                        val intent=Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()


                        Toast.makeText(this,"Registration Successful",Toast.LENGTH_SHORT).show()
                        //code
                    }
                    else{
                        Toast.makeText(this,"Some Error Ocuured creating user",Toast.LENGTH_SHORT).show()
                    }
                }








    }




    }





}
