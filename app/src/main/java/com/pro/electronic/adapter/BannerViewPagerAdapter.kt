package com.pro.electronic.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.pro.electronic.R
import com.pro.electronic.adapter.BannerViewPagerAdapter.BannerViewHolder
import com.pro.electronic.listener.IClickProductListener
import com.pro.electronic.model.Product
import com.pro.electronic.utils.GlideUtils.loadUrlBanner

class BannerViewPagerAdapter(
    private val mListProduct: List<Product>?,
    private val iClickProductListener: IClickProductListener
) : RecyclerView.Adapter<BannerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_banner, parent, false)
        return BannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val product = mListProduct!![position]
        loadUrlBanner(product.banner, holder.imgBanner)
        holder.imgBanner.setOnClickListener {
            iClickProductListener.onClickProductItem(
                product
            )
        }
    }

    override fun getItemCount(): Int {
        if (mListProduct != null) {
            return mListProduct.size
        }
        return 0
    }

    class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgBanner: ImageView = itemView.findViewById(R.id.img_banner)
    }
}
