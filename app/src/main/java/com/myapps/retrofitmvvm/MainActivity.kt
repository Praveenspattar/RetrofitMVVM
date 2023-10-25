package com.myapps.retrofitmvvm

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.myapps.retrofitmvvm.api.ApiInterface
import com.myapps.retrofitmvvm.api.ApiUtilities
import com.myapps.retrofitmvvm.repository.MemesRepository
import com.myapps.retrofitmvvm.viewModel.MemesViewModel
import com.myapps.retrofitmvvm.viewModel.MemesViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var memesViewModel: MemesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiInterface = ApiUtilities.getInstance().create(ApiInterface::class.java)

        val memesRepository = MemesRepository(apiInterface)

        memesViewModel = ViewModelProvider(this,MemesViewModelFactory(memesRepository))[MemesViewModel::class.java]

        memesViewModel.memes.observe(this) {
           // Log.d("mem", "onCreate: $it")
            it.data.memes.iterator().forEach {meme ->
                Log.d("mem","name: ${meme.name}\nimage : ${meme.url}")
            }
        }
    }
}