package com.example.testmercadolibre.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testmercadolibre.R
import com.example.testmercadolibre.adapters.SearchAdapter
import com.example.testmercadolibre.utils.Status
import com.example.testmercadolibre.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var searchAdapter : SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        initView()
        initViewModel()
        observeViewModel()
        searchListener()
    }

    private fun initViewModel(){
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    private fun observeViewModel(){
        searchViewModel.search.observe(this, Observer {
            searchAdapter.setData(it.results as ArrayList)
        })

        searchViewModel.status.observe(this, Observer {
            when(it){
                Status.DONE->{
                    loader.visibility = GONE
                    resultsRV.visibility = VISIBLE

                }
                Status.LOADING->{
                    loader.visibility = VISIBLE
                    resultsRV.visibility = GONE

                }
                Status.ERROR->{
                    loader.visibility = GONE
                    resultsRV.visibility = GONE
                    Toast.makeText(this,"Ha ocurrido un error", Toast.LENGTH_SHORT).show()
                }

            }
        })
    }
    private fun searchAPI(query : String){
        searchViewModel.let {
            it.getSearch(query)
        }
    }

    private fun initView(){
        searchAdapter = SearchAdapter()
        resultsRV.layoutManager = LinearLayoutManager(this)
        resultsRV.adapter = searchAdapter
    }

    private fun searchListener(){
        search.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty())
                    searchAPI("Samsung")
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                searchAPI(query)
                return false
            }

        })
    }

}