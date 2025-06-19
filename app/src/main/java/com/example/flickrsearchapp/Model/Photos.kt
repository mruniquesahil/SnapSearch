package com.example.flickrsearchapp.Model

/**
 * Data class for photos
 */
data class Photos(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val total: Int,
    val photo: ArrayList<Photo>
)

/**
 * data class for photo
 */
data class Photo(
    val id: Float,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Float,
    val title: String,
    val ispublic: Int,
    val isfriend: Int,
    val isfamily: Int,
    val url_s: String,
)