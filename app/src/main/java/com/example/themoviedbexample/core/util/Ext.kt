package com.example.themoviedbexample.core.util

import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(): String {
    val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val date = parser.parse(this)
    return dateFormat.format(date)
}