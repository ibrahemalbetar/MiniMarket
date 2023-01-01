package com.ibrahimalbitar.minimarket.vvm.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ibrahimalbitar.minimarket.data.model.ProductsResp.Product
import com.ibrahimalbitar.minimarket.databinding.ProductItemBinding
import com.ibrahimalbitar.minimarket.listener.ProductItemActionsListener

class ProductsAdapter constructor(val listener: ProductItemActionsListener) : RecyclerView.Adapter<MainViewHolder>() {

    var productsList = mutableListOf<Product>()

    fun setMovies(movies: List<Product>) {
        this.productsList = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val product = productsList[position]
            holder.binding.name.text = product.name
           Glide.with(holder.itemView.context).load(product.image_urls_thumbnails[0]).into(holder.binding.imageview)

            holder.binding.shareFb.setOnClickListener{
                listener.shareProduct(FacebookAppID, product.image_urls_thumbnails[0])
            }

            holder.binding.shareInsta.setOnClickListener{
                listener.shareProduct(InstagramAppID, product.image_urls_thumbnails[0])
            }
        }

    override fun getItemCount(): Int {
        return productsList.size
    }
}

class MainViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {

}