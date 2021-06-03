package com.example.dogsinfowithofflinecaching.model.dogs

import com.example.dogsinfowithofflinecaching.model.dogs.Height
import com.example.dogsinfowithofflinecaching.model.dogs.Weight

data class Breed(
    val bred_for: String,
    val height: Height,
    val history: String,
    val id: Int,
    val life_span: String,
    val name: String,
    val reference_image_id: String,
    val temperament: String,
    val weight: Weight
)