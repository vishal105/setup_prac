package com.example.setup_prac.data.remote.api

import com.example.setup_prac.R
import com.example.setup_prac.base.BaseActivity
import com.example.setup_prac.data.remote.model.PhotosData
import com.example.setup_prac.data.remote.model.ResponseData
import com.example.setup_prac.utils.NetworkConnectivity
import com.example.setup_prac.utils.ResourceUtils.getString
import org.greenrobot.eventbus.EventBus
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class RemoteData @Inject constructor(
    private val serviceGenerator: ApiService, private val networkConnectivity: NetworkConnectivity
) : RemoteDataSource {

    override suspend fun getPhotosData(): ResourceHandling<PhotosData> {
        return when (val response =
            processCallUnstructured { serviceGenerator.getPhotosData() }) {

            is String -> {
                ResourceHandling.ErrorState(null, response.toString())
            }

            else -> {
                ResourceHandling.Success(response as PhotosData, "")
            }
        }

//        val response = serviceGenerator.getPhotosData()
//        val body = response.body()
//        return if (response.isSuccessful && body != null) {
//            ResourceHandling.Success(body, response.message())
//        } else if (response.code() == 401) {
//            ResourceHandling.ErrorState(body, response.toString())
//        } else if (response.code() == 500) {
//            ResourceHandling.ErrorState(body, "Internal server error")
//        } else {
//            ResourceHandling.ErrorState(null, "Something went wrong")
//        }
    }

    private suspend fun processCallUnstructured(responseCall: suspend () -> Response<*>): Any? {
        if (!networkConnectivity.isConnected()) {
            return getString(R.string.internet_not_available)
        }
        return try {
            val response =
                responseCall.invoke() //if (response.isSuccessful && (response.body() as ApiResponse<*>).status == true) {
            if (response.isSuccessful && (response.body() != null)) {
                response.body()
            } else if (response.code() == 401) {
                response.message()
            } else if (response.code() == 500) {
                return "Internal server error"
            } else {
                return "Something went wrong"
            }
        } catch (e: IOException) {
            e.message
        }
    }
}