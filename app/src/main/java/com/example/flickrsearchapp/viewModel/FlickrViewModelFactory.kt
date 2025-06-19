package com.example.flickrsearchapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flickrsearchapp.network.PhotosRepository

/**
 * This is a viewmodel factory class for Flickr viewmodel.
 */
class FlickrViewModelFactory constructor(val repository: PhotosRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(FlickrViewModel::class.java)) {
            FlickrViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}