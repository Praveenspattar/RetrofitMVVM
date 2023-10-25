package com.myapps.retrofitmvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapps.retrofitmvvm.model.Jokes
import com.myapps.retrofitmvvm.repository.MemesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MemesViewModel(private val memesRepository: MemesRepository): ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            memesRepository.getMemes()
        }
    }

    val memes : LiveData<Jokes>
        get() = memesRepository.memes
}