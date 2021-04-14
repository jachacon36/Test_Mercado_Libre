package com.example.testmercadolibre.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testmercadolibre.R
import com.example.testmercadolibre.adapters.ExhibitorAdapter
import com.example.testmercadolibre.utils.Status
import com.example.testmercadolibre.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.search
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.loader

class HomeActivity : AppCompatActivity() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var exhibitorAdapter: ExhibitorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initView()
        initViewModel()
        observeViewModel()
        searchListener()
        homeViewModel.getHome(this)
    }

    private fun initViewModel(){
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    private fun observeViewModel(){
        homeViewModel.components.observe(this, Observer {
            it.components.forEach {
                if (it.type.equals("exhibitors")){
                    exhibitorAdapter.setData(it.elements as ArrayList)
                    return@forEach
                }
            }
        })

        homeViewModel.status.observe(this, Observer {
            when(it){
                Status.DONE->{
                    loader.visibility = View.GONE
                    exhibitorRV.visibility = View.VISIBLE

                }
                Status.LOADING->{
                    loader.visibility = View.VISIBLE
                    exhibitorRV.visibility = View.GONE

                }
                Status.ERROR->{
                    loader.visibility = View.GONE
                    exhibitorRV.visibility = View.GONE
                    Toast.makeText(this,"Ha ocurrido un error", Toast.LENGTH_SHORT).show()
                }

            }
        })
    }

    private fun initView(){
        exhibitorAdapter = ExhibitorAdapter()
        exhibitorRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)
        exhibitorRV.adapter = exhibitorAdapter
    }

    private fun searchListener(){
        search.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                openSearchActivity(query)
                return false
            }

        })
    }

    private fun openSearchActivity(query: String){
        val intent : Intent = Intent(this, SearchActivity::class.java).putExtra("query",query )
        startActivityForResult(intent, 200)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 200){
            search.setQuery("", false)
            search.isIconified = true;
        }
    }

}