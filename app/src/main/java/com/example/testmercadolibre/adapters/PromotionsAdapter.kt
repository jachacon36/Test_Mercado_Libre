package com.example.testmercadolibre.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testmercadolibre.R
import com.example.testmercadolibre.model.ItemModel
import kotlinx.android.synthetic.main.item_promotion.view.*

class PromotionsAdapter() : RecyclerView.Adapter<PromotionsAdapter.ViewHolder>() {

    var listResult: ArrayList<ItemModel> = arrayListOf()

    fun setData(listResult: ArrayList<ItemModel>) {
        this.listResult = listResult;
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_promotion, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = listResult.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(listResult[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("StringFormatMatches")
        fun bindItems(itemModel: ItemModel) {
            itemView.title.text = itemModel.title
            itemView.price.text = "$ ${itemModel.price.value}"
            itemView.discount.text= itemModel.discount.text
            Glide.with(itemView.context).load(itemModel.picture.url).into(itemView.picture);
        }
    }
}