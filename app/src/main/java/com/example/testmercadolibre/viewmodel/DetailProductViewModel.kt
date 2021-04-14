package com.example.testmercadolibre.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testmercadolibre.model.DetailProductModel
import com.example.testmercadolibre.network.Api
import com.example.testmercadolibre.utils.Status
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class DetailProductViewModel : ViewModel() {

    private var _details = MutableLiveData<DetailProductModel>()
    val details: LiveData<DetailProductModel>
        get() = _details

    private var _status = MutableLiveData<Status>()
    val status: LiveData<Status>
        get() = _status

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun getItem(id: String) {
        coroutineScope.launch {

            _status.value = Status.LOADING
            var getDetail = Api.retrofitService.getItem(id)

            try {
                var results = getDetail.await()
                results.let {
                    _details.value = results
                    _status.value = Status.DONE
                }
               } catch (e: Exception) {
                e.printStackTrace()
                _status.value = Status.ERROR
            }
        }
    }

    fun getHome() {

    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}