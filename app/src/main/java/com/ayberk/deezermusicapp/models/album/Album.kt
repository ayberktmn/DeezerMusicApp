package com.ayberk.deezermusicapp.models.album

data class Album(
    val `data`: List<Data>,
    val next: String,
    val total: Int
)