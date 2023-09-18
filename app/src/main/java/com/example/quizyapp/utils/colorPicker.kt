package com.example.quizyapp.utils

object colorPicker {
    val colors= arrayOf("#cd2f00", "#9348af",	"#01ac53",	"#c5a4fb",	"#996635",	"#b11573",	"#4bb473",	"#75d89e",
        "#2f3f94",	"#2f7b99",	"#da967d",	"#34891f",	"#b0d87b",	"#ca4751",	"#7e50a8",
      )
    var currentColorIndex=0

    fun getColor():String{
        currentColorIndex=(currentColorIndex+1)% colors.size
        return colors[currentColorIndex]

    }


}