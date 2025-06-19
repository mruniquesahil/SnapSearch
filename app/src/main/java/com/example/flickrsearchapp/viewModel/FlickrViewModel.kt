package com.example.flickrsearchapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flickrsearchapp.Model.PhotosResponse
import com.example.flickrsearchapp.network.PhotosRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * The view model for flickr
 */
class FlickrViewModel constructor(private val repository: PhotosRepository) : ViewModel() {
    val list = MutableLiveData<PhotosResponse>()
    var imageUrl = MutableLiveData<String>()
    private val error = MutableLiveData<String>()
    private val apiKey = "1508443e49213ff84d566777dc211f2a"
    private val method = "flickr.photos.search"
    private val format = "json"
    private val noJsonCallBack = 1
    private val extras = "url_s"

    fun getPhotos(tags: String) {
        val response = repository.getPhotos(method, apiKey, format, tags, extras, noJsonCallBack)
        response.enqueue(object : Callback<PhotosResponse> {
            override fun onResponse(
                call: Call<PhotosResponse>,
                response: Response<PhotosResponse>
            ) {
                list.postValue(response.body())
            }

            override fun onFailure(call: Call<PhotosResponse>, t: Throwable) {
                error.postValue(t.message)
            }
        })
    }
}
