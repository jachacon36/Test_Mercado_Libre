package com.example.testmercadolibre.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testmercadolibre.R
import com.example.testmercadolibre.`interface`.OpenItemDetail
import com.example.testmercadolibre.model.ResultModel
import kotlinx.android.synthetic.main.item_result.view.*

class SearchAdapter(val openItemDetail: OpenItemDetail) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    var listResult: ArrayList<ResultModel> = arrayListOf()

    fun setData(listResult: ArrayList<ResultModel>) {
        this.listResult = listResult;
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_result, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = listResult.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(listResult[position])
        holder.itemView.container.setOnClickListener {
            openItemDetail.openDetailActivity(listResult[position].id)
        }


    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("StringFormatMatches")
        fun bindItems(result: ResultModel) {
            itemView.price.text = "$ ${result.price}"
            itemView.title.text = result.title
            itemView.installments.text = "en ${result.installments.quantity}x $ ${result.installments.amount}"
            if (result.shipping.free_shipping){
                itemView.shipping.visibility = VISIBLE
            }else{
                itemView.shipping.visibility = GONE
            }
            Glide.with(itemView.context).load(result.thumbnail).into(itemView.thumbnail);
        }
    }
}