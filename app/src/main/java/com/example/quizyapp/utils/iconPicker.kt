package com.example.quizyapp.utils

import com.example.quizyapp.R

object iconPicker {
    val icons= arrayOf(
        R.drawable.ic_icon_1,
        R.drawable.ic_icon_2,
        R.drawable.ic_icon_3,
        R.drawable.ic_icon_4,
        R.drawable.ic_icon_5,
        R.drawable.ic_icon_6,
        R.drawable.ic_icon_7,
        R.drawable.ic_icon_8,
        R.drawable.ic_icon_9,
        R.drawable.ic_icon_10,
        R.drawable.ic_icon_11,

    )
    var currentIconIndex=0

    fun getIcon():Int{
        currentIconIndex=(currentIconIndex+1)% icons.size
        return icons[currentIconIndex]

    }


}