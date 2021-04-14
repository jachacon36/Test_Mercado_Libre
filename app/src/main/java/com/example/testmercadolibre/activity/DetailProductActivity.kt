package com.example.testmercadolibre.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testmercadolibre.R
import com.example.testmercadolibre.adapters.PicturesAdapter
import com.example.testmercadolibre.model.DetailProductModel
import com.example.testmercadolibre.utils.Status
import com.example.testmercadolibre.viewmodel.DetailProductViewModel
import kotlinx.android.synthetic.main.activity_detail_product.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.loader
import kotlinx.android.synthetic.main.activity_search.search_failed
import java.lang.Exception

class DetailProductActivity : AppCompatActivity() {

    private lateinit var detailProductViewModel: DetailProductViewModel
    private lateinit var picturesAdapter: PicturesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_product)
        initView()
        initViewModel()
        observeViewModel()
        getExtras(savedInstanceState)
    }

    private fun initViewModel(){
        detailProductViewModel = ViewModelProvider(this).get(DetailProductViewModel::class.java)
    }

    private fun observeViewModel(){
        detailProductViewModel.details.observe(this, Observer {
            picturesAdapter.setData(it.pictures as ArrayList)
            setDataView(it)
        })

        detailProductViewModel.status.observe(this, Observer {
            when(it){
                Status.DONE->{
                    loader.visibility = View.GONE
                    search_failed.visibility = View.GONE
                    container.visibility = View.VISIBLE
                }
                Status.LOADING->{
                    loader.visibility = View.VISIBLE
                    container.visibility = View.GONE
                    search_failed.visibility = View.GONE
                }
                Status.ERROR->{
                    loader.visibility = View.GONE
                    container.visibility = View.GONE
                    search_failed.visibility = View.VISIBLE
                }

            }
        })
    }

    private fun getItemAPI(id : String){
        detailProductViewModel.let {
            it.getItem(id)
        }
    }
    private fun initView(){
        picturesAdapter = PicturesAdapter()
        picturesRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)
        picturesRV.adapter = picturesAdapter
    }

    private fun setDataView(detailProductModel: DetailProductModel){
        title_view.text = detailProductModel.title
        if (detailProductModel.condition.equals("new")) condition.text = getString(R.string.new_title) else condition.text = getString(R.string.used_title)
        price.text = "$ ${detailProductModel.price}"
        if (!detailProductModel.shipping.free_shipping) shipping.visibility= GONE
        quantity.text ="${detailProductModel.available_quantity}/${detailProductModel.initial_quantity}"
        picture_count.text ="${detailProductModel.pictures.size}/${detailProductModel.pictures.size}"
    }
    private fun getExtras(savedInstanceState: Bundle?) {
        try {
            if (savedInstanceState==null)
                intent.getStringExtra("id")?.let {
                    getItemAPI(it)
                }
        }catch (e: Exception){

        }
    }
}