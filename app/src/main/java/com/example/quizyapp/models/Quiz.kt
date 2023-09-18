package com.example.quizyapp.models

data class Quiz(
  var id:String ="",

    var questions: MutableMap<String,Question>  = mutableMapOf(),
    var title:String=""


)
