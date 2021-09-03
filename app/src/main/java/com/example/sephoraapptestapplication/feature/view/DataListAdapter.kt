package com.example.sephoraapptestapplication.feature.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sephoraapptestapplication.R
import com.example.sephoraapptestapplication.model.Included
import kotlinx.android.synthetic.main.layout_data_adapter.view.*
import java.util.*

class DataListAdapter(var context: Context, private var arrayList: ArrayList<Included>) :
    RecyclerView.Adapter<DataListAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = DataViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.layout_data_adapter, parent, false)
    )

    override fun getItemCount() = arrayList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        var data = arrayList[position]
        holder.brandName.text = data.attributes.brand_name
        holder.productName.text = data.attributes.product_name
        holder.originalPrice.text = data.attributes.original_price
        holder.productRating.text = data.attributes.rating
        Glide.with(context)
            .load(data.attributes.image_urls[0])
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.productImage)

    }

    class DataViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val brandName = view.brand_name
        val productName = view.product_name
        val productImage = view.imageView
        val originalPrice = view.product_original_price
        val productRating = view.product_rating

    }
}