package com.example.dogsinfowithofflinecaching.model.dogs

data class DogsItem(
    val breeds: List<Breed>,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)