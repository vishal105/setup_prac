package com.example.setup_prac.ui.main.main_frag

import com.example.setup_prac.dispatcher_rule.MainDispatcherRule
import com.example.setup_prac.data.remote.model.PhotosData
import com.example.setup_prac.repo.FakeRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainFragmentVmTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel : MainFragmentVm

    private lateinit var fakeRepo : FakeRepo

    @Before
    fun setup(){
        fakeRepo = FakeRepo()
        viewModel = MainFragmentVm(fakeRepo)
    }

    @Test
    fun `get all photo data`() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            fakeRepo.getPhotoData().collectLatest {
                assertEquals((FakeRepo().tempData.data as PhotosData),it.data as PhotosData)
            }
        }
    }

    @Test
    fun `exception if no data should return false`() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            fakeRepo.tempData.data?.clear()
            fakeRepo.getPhotoData().collectLatest {
                assertEquals(PhotosData(),it.data as PhotosData)
            }
        }
    }

}