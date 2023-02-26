package com.example.setup_prac.ui.main.main_frag

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.setup_prac.R
import com.example.setup_prac.base.BaseFragment
import com.example.setup_prac.base.BaseViewModel
import com.example.setup_prac.data.remote.model.PhotosDataItem
import com.example.setup_prac.databinding.ActivityMainBinding
import com.example.setup_prac.repo.DataRepository
import com.example.setup_prac.repo.DataRepositorySource
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentVm @Inject constructor(private val dataRepository: DataRepositorySource) : BaseViewModel(){

    var itemsList = MutableLiveData<ArrayList<PhotosDataItem>?>()

    fun fetchData() {
        viewModelScope.launch {
            dataRepository.getPhotoData().catch {
                Log.d("TAG", "fetchCalls: failed ${it.printStackTrace()}")
            }.collect {
                itemsList.value = it.data
                Log.d("TAG", "fetchCalls: data recieved ${it.data?.size}}")
            }
        }
    }

}