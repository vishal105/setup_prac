package com.example.setup_prac.ui.main

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.setup_prac.base.BaseActivity
import com.example.setup_prac.base.BaseViewModel
import com.example.setup_prac.databinding.ActivityMainBinding
import com.example.setup_prac.repo.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityVm @Inject constructor(private val dataRepository: DataRepository): BaseViewModel() {



}