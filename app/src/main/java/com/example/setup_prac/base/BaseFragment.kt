package com.example.setup_prac.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<DB : ViewDataBinding , VM : BaseViewModel> : Fragment() {

    lateinit var binding : DB

    lateinit var viewModel : VM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        if (!::binding.isInitialized) {
            binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
            binding.lifecycleOwner = this
        }
        viewModel = ViewModelProvider(this)[getViewModelClass()]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstancState: Bundle?) {
        super.onViewCreated(view, savedInstancState)
        initViewBinding()
        observeViewModel()
        menuData()
    }

    abstract fun menuData()

    abstract fun observeViewModel()

    abstract fun initViewBinding()

    abstract fun getLayout(): Int

    abstract fun getViewModelClass(): Class<out VM>

}