package com.example.testmercadolibre.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testmercadolibre.R
import com.example.testmercadolibre.utils.Status
import com.example.testmercadolibre.viewmodel.SearchViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        observeViewModel()
        searchViewModel.let {
            it.getSearch("Samsung")
        }
    }

    private fun initViewModel(){
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    private fun observeViewModel(){
        searchViewModel.search.observe(this, Observer {
            it.results
        })

        searchViewModel.status.observe(this, Observer {
            when(it){
                Status.DONE->{
                }
                Status.LOADING->{
                }
                Status.ERROR->{
                    Toast.makeText(this,"Ha ocurrido un error", Toast.LENGTH_SHORT).show()
                }

            }
        })
    }
}