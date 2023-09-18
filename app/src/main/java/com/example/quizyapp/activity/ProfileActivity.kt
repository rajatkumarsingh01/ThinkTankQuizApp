package com.example.quizyapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.quizyapp.R
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        firebaseAuth=FirebaseAuth.getInstance()
        val txtEmail=findViewById<TextView>(R.id.txtEmail)
        txtEmail.text=firebaseAuth.currentUser?.email
        val btnLogout=findViewById<Button>(R.id.btnLogout)
        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this,LogInActivity::class.java)
            startActivity(intent)

        }
    }
}