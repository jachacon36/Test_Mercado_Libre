package com.example.testmercadolibre.activity

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testmercadolibre.R
import com.example.testmercadolibre.adapters.ExhibitorAdapter
import com.example.testmercadolibre.adapters.PromotionsAdapter
import com.example.testmercadolibre.model.ItemModel
import com.example.testmercadolibre.utils.Status
import com.example.testmercadolibre.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.search
import kotlinx.android.synthetic.main.activity_search.loader


class HomeActivity : AppCompatActivity() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var exhibitorAdapter: ExhibitorAdapter
    private lateinit var promotionsAdapter: PromotionsAdapter
    private var scroll : Int = 0;
    private val timer: CountDownTimer by lazy {
        object : CountDownTimer(60000, 4000) {
            override fun onFinish() {
                timer.start()
            }

            override fun onTick(millisUntilFinished: Long) {
                if (scroll == 4){
                    exhibitorRV.smoothScrollToPosition(scroll);
                    scroll = 0
                }else {
                    exhibitorRV.smoothScrollToPosition(scroll);
                    scroll ++
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initView()
        initViewModel()
        observeViewModel()
        searchListener()
        if (savedInstanceState==null) homeViewModel.getHome(this)
    }

    private fun initViewModel(){
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    private fun observeViewModel(){
        homeViewModel.components.observe(this, Observer {
            it.components.forEach {
                if (it.type.equals("exhibitors")){
                    timer.start()
                    exhibitorAdapter.setData(it.elements as ArrayList)
                }else if (it.type.equals("recommendations")){
                    promotionsAdapter.setData(it.items as ArrayList)

                }
            }
        })

        homeViewModel.status.observe(this, Observer {
            when(it){
                Status.DONE->{
                    loader.visibility = View.GONE
                    exhibitorRV.visibility = VISIBLE
                    promotionsContainer.visibility = VISIBLE

                }
                Status.LOADING->{
                    loader.visibility = VISIBLE
                    exhibitorRV.visibility = GONE
                    promotionsContainer.visibility = GONE


                }
                Status.ERROR->{
                    loader.visibility = GONE
                    exhibitorRV.visibility = GONE
                    promotionsContainer.visibility = GONE

                    Toast.makeText(this,"Ha ocurrido un error", Toast.LENGTH_SHORT).show()
                }

            }
        })
    }

    private fun initView(){
        exhibitorAdapter = ExhibitorAdapter()
        exhibitorRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)
        exhibitorRV.adapter = exhibitorAdapter
        promotionsAdapter = PromotionsAdapter()
        promotionsRV.layoutManager = GridLayoutManager(this,2)
        promotionsRV.adapter = promotionsAdapter
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

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }
}