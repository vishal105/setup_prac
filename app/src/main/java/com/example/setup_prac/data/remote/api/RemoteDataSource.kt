package com.example.setup_prac.data.remote.api

import com.example.setup_prac.data.remote.model.PhotosData
import com.example.setup_prac.data.remote.model.ResponseData
import retrofit2.Response

internal interface RemoteDataSource {
    suspend fun getPhotosData(): ResourceHandling<PhotosData>
}