package com.example.flickrsearchapp.network

/**
 * This is a repository class for making the retrofit call.
 */
class PhotosRepository constructor(private val retrofitService: RetrofitService) {

    fun getPhotos( method: String, api_key: String, format: String, tags: String, extras: String,nojsoncallback: Int) =
        retrofitService.getPhotos(method, api_key,format,tags, extras,nojsoncallback)
}