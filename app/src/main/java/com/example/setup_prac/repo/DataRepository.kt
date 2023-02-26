package com.example.setup_prac.repo

import android.content.SharedPreferences
import com.example.setup_prac.data.remote.api.RemoteData
import com.example.setup_prac.data.remote.api.ResourceHandling
import com.example.setup_prac.data.remote.model.PhotosData
import com.example.setup_prac.data.remote.model.ResponseData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DataRepository @Inject constructor(
    private val remoteRepository: RemoteData,
    private val sharedPreferences: SharedPreferences,
    private val ioDispatcher: CoroutineContext
) : DataRepositorySource {

    override suspend fun getPhotoData(): Flow<ResourceHandling<PhotosData>> {
        return flow {
            emit(remoteRepository.getPhotosData())
        }.flowOn(ioDispatcher)
    }

    override suspend fun getFakeData(): ResourceHandling<PhotosData> {
        return remoteRepository.getPhotosData()
    }
}