package com.example.setup_prac.data.remote.api

import com.example.setup_prac.data.remote.model.PhotosData
import com.example.setup_prac.data.remote.model.ResponseData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET(ApiConstants.GET_PHOTOS)
    suspend fun getPhotosData(): Response<PhotosData>

}