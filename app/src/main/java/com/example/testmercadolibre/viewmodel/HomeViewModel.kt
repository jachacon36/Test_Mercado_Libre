package com.example.testmercadolibre.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testmercadolibre.model.ComponetsJSON
import com.example.testmercadolibre.model.SearchModel
import com.example.testmercadolibre.network.Api
import com.example.testmercadolibre.utils.LoadHome
import com.example.testmercadolibre.utils.Status
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private var _components = MutableLiveData<ComponetsJSON>()
    val components: LiveData<ComponetsJSON>
        get() = _components

    private var _status = MutableLiveData<Status>()
    val status: LiveData<Status>
        get() = _status

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope( viewModelJob + Dispatchers.Main )


    fun getHome(context: Context){
        try {
            _status.value = Status.LOADING
            _components.value = Gson().fromJson(LoadHome.loadJSONFromAsset(context), ComponetsJSON::class.java)
            _status.value = Status.DONE
        }catch (e : java.lang.Exception){
            e.printStackTrace()
            _status.value = Status.ERROR
        }

    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}