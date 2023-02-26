package com.example.setup_prac.ui.main

import androidx.appcompat.app.ActionBar
import com.example.setup_prac.R
import com.example.setup_prac.base.BaseActivity
import com.example.setup_prac.databinding.ActivityMainBinding
import com.example.setup_prac.ui.main.main_frag.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainActivityVm>() {

    override fun setupToolbar(supportActionBar: ActionBar?) {
        supportActionBar?.apply {
            title = "MainAct with frag"
        }
    }

    override fun observeViewModel() {

    }

    override fun initViewBinding() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragContainer, MainFragment(), "MainFragment").commit()
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun getViewModelClass(): Class<out MainActivityVm> {
        return MainActivityVm::class.java
    }

}