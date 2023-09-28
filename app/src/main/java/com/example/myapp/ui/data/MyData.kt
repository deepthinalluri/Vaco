package com.example.myapp.ui

data class MyData(
    val items: List<Item>
)

data class Item(
    val title: String,
    val date: String,
    val description: String,
    val img: String,

)