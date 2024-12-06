package com.pro.electronic.listener

import com.pro.electronic.model.Product

interface IClickProductListener {
    fun onClickProductItem(product: Product?)
}
