package com.example.newapplication.Models

data class NewsModels(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)