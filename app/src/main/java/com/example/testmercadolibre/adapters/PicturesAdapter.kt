package com.example.testmercadolibre.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testmercadolibre.R
import com.example.testmercadolibre.model.ElementModel
import com.example.testmercadolibre.model.PictureURLModel
import kotlinx.android.synthetic.main.item_exhibitor.view.*
import kotlinx.android.synthetic.main.item_picture.view.*
import kotlinx.android.synthetic.main.item_result.view.*

class PicturesAdapter() : RecyclerView.Adapter<PicturesAdapter.ViewHolder>() {

    var listResult: ArrayList<PictureURLModel> = arrayListOf()

    fun setData(listResult: ArrayList<PictureURLModel>) {
        this.listResult = listResult;
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_picture, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = listResult.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(listResult[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("StringFormatMatches")
        fun bindItems(pictureURLModel: PictureURLModel) {
            Glide.with(itemView.context).load(pictureURLModel.url).into(itemView.picture);
        }
    }
}