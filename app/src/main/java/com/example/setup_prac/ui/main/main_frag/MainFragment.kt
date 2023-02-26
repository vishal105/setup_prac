package com.example.setup_prac.ui.main.main_frag

import com.example.setup_prac.R
import com.example.setup_prac.base.BaseFragment
import com.example.setup_prac.data.remote.model.PhotosDataItem
import com.example.setup_prac.databinding.FragmentMainBinding
import com.example.setup_prac.databinding.PhotoItemsRowBinding
import com.example.setup_prac.utils.ChoiceMode
import com.example.setup_prac.utils.KSelectionAdapter
import com.example.setup_prac.utils.setUpAdapter
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding, MainFragmentVm>() {

    private lateinit var adapter: KSelectionAdapter<PhotosDataItem, PhotoItemsRowBinding>

    override fun observeViewModel() {
        viewModel.apply {
            itemsList.observe(this@MainFragment) { list ->
                list?.let { it1 ->
                    setupRv(list)
                }
            }
        }
    }

    private fun setupRv(it: ArrayList<PhotosDataItem>) {
        adapter =
            setUpAdapter(it.toMutableList(), ChoiceMode.NONE, R.layout.photo_items_row, { item, binder: PhotoItemsRowBinding?, position, adapter ->
                binder?.apply {
                    Picasso.get().load(item.thumbnailUrl)
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .error(com.google.android.material.R.drawable.mtrl_ic_error)
                            .into(imageHolder)

                    tittleHolder.text = item.title
                }
            }, { item, position, adapter -> })

        binding.apply {
            imageList.adapter = adapter
        }
    }

    override fun initViewBinding() {
        viewModel.fetchData()
    }

    override fun menuData() {

    }

    override fun getLayout(): Int {
        return R.layout.fragment_main
    }

    override fun getViewModelClass(): Class<out MainFragmentVm> {
        return MainFragmentVm::class.java
    }

}