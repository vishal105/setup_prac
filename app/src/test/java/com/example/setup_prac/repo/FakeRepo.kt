package com.example.setup_prac.repo

import com.example.setup_prac.data.remote.api.ResourceHandling
import com.example.setup_prac.data.remote.model.PhotosData
import com.example.setup_prac.data.remote.model.PhotosDataItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FakeRepo : DataRepositorySource {

    var tempData = ResourceHandling.Success(
            PhotosData().apply {
                add(PhotosDataItem(
                        albumId =  1,
                        id =  1,
                        thumbnailUrl =  "accusamus beatae ad facilis cum similique qui sunt",
                        title =  "https://via.placeholder.com/600/92c952",
                        url =  "https://via.placeholder.com/150/92c952"
                ))
                add(PhotosDataItem(
                        albumId =  1,
                        id =  2,
                        thumbnailUrl =  "reprehenderit est deserunt velit ipsam",
                        title =  "https://via.placeholder.com/600/771796",
                        url =  "https://via.placeholder.com/150/771796"
                ))
                add(PhotosDataItem(
                        albumId =  1,
                        id =  3,
                        thumbnailUrl =  "officia porro iure quia iusto qui ipsa ut modi",
                        title =  "https://via.placeholder.com/600/24f355",
                        url =  "https://via.placeholder.com/150/24f355"
                ))
            },
            message = "data received successfully"
    )

    override suspend fun getPhotoData(): Flow<ResourceHandling<PhotosData>> {
        return flow {
            emit(tempData)
        }
    }

    override suspend fun getFakeData(): ResourceHandling<PhotosData> {
        return tempData
    }

}