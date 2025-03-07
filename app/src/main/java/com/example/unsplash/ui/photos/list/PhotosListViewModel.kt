package com.example.unsplash.ui.photos.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplash.data.model.Photo
import com.example.unsplash.ui.photos.PhotosRepository
import com.example.unsplash.utilites.toModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosListViewModel @Inject constructor(
    private val repository: PhotosRepository
) : ViewModel() {

    private val coroutineContext = Dispatchers.IO + Job()

    private val _photosLiveData = MutableLiveData<List<Photo>>()
    val photosLiveData: LiveData<List<Photo>> get() = _photosLiveData


    fun loadPhotos() {
        viewModelScope.launch(coroutineContext) {

            val response = repository.requestPhotosList()

            if (response.isSuccessful) {
                val list = response.body()?.toModel() ?: emptyList()
                _photosLiveData.postValue(list)
            }

        }
    }

}