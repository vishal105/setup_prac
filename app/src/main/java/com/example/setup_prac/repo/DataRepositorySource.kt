package com.example.setup_prac.repo

import com.example.setup_prac.data.remote.api.ResourceHandling
import com.example.setup_prac.data.remote.model.PhotosData
import com.example.setup_prac.data.remote.model.ResponseData
import kotlinx.coroutines.flow.Flow

interface DataRepositorySource {
    suspend fun getPhotoData(): Flow<ResourceHandling<PhotosData>>

    suspend fun getFakeData() : ResourceHandling<PhotosData>
}