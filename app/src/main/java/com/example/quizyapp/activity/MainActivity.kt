package com.example.quizyapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.quizyapp.R
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizyapp.adapters.QuizAdapter
import com.example.quizyapp.models.Quiz
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date


class MainActivity : AppCompatActivity() {
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    lateinit var adapter: QuizAdapter
    lateinit var firestore: FirebaseFirestore

    private var quizList= mutableListOf<Quiz>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViews()
    }



    fun setUpViews(){
        setUpFireStore()

        setUpDrawerLayout()
        setUpRecyclerView()
        setUpDatePicker()


    }

    @SuppressLint("SimpleDateFormat")
    private fun setUpDatePicker() {
        val btnDatePicker=findViewById<FloatingActionButton>(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener {
            val datePicker=MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager,"DatePicker" )
            datePicker.addOnPositiveButtonClickListener {
                Log.d("DATEPICKER",datePicker.headerText)
                val dateFormatter=SimpleDateFormat("dd-mm-yyyy")
                val date=dateFormatter.format(Date(it))

                val intent=Intent(this,QuestionActivity::class.java)
                intent.putExtra("DATE",date)
                startActivity(intent)


            }
            datePicker.addOnNegativeButtonClickListener {
                Log.d("DATEPICKER",datePicker.headerText)

            }
            datePicker.addOnCancelListener {
                Log.d("DATEPICKER","DatePicker Cancelled")

            }
        }
    }

    private fun setUpFireStore() {
        firestore=FirebaseFirestore.getInstance()
        val collectionReference=firestore.collection("quizzes")
        collectionReference.addSnapshotListener { value, error ->
            if(value==null || error !=null){
                Toast.makeText(this,"Error fetching data",Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
//            Log.d("DATA",value.toObjects(Quiz::class.java).toString())

            quizList.clear()
            quizList.addAll(value.toObjects(Quiz::class.java))
            adapter.notifyDataSetChanged()
        }


    }

    private fun setUpRecyclerView() {
        adapter= QuizAdapter(this,quizList)
        val quizRecyclerView=findViewById<RecyclerView>(R.id.quizRecyclerView)

        quizRecyclerView.layoutManager=GridLayoutManager(this,2)
        quizRecyclerView.adapter=adapter

    }

    fun setUpDrawerLayout(){

         val appBar=findViewById<Toolbar>(R.id.appBar)
         setSupportActionBar(appBar)

         val mainDrawer = findViewById<DrawerLayout>(R.id.mainDrawer)
         actionBarDrawerToggle= ActionBarDrawerToggle(this, mainDrawer, R.string.app_name, R.string.app_name)
        actionBarDrawerToggle.syncState()
        val navigationView=findViewById<NavigationView>(R.id.navigationView)
        navigationView.setNavigationItemSelectedListener {
            val intent=Intent(this,ProfileActivity::class.java)

            startActivity(intent)
            mainDrawer.closeDrawers()
            true

        }





    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }




}