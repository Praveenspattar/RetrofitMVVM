package com.myapps.retrofitmvvm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.myapps.retrofitmvvm.api.ApiInterface
import com.myapps.retrofitmvvm.model.Jokes

class MemesRepository(private val apiInterface: ApiInterface) {

    private val memesLiveData = MutableLiveData<Jokes>()

    val memes : LiveData<Jokes>
        get() = memesLiveData

    suspend fun getMemes(){
        val result = apiInterface.getJokes()
        if (result.body() != null){
            memesLiveData.postValue(result.body())
        }
    }
}