package com.example.setup_prac.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity<DB : ViewDataBinding , VM : BaseViewModel> : AppCompatActivity() {

    lateinit var binding : DB

    lateinit var viewModel : VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,getLayout())
        viewModel = ViewModelProvider(this)[getViewModelClass()]
        setupToolbar(supportActionBar)
        initViewBinding()
        observeViewModel()
    }

    abstract fun setupToolbar(supportActionBar: ActionBar?)

    abstract fun observeViewModel()

    abstract fun initViewBinding()

    abstract fun getLayout(): Int

    abstract fun getViewModelClass(): Class<out VM>

}