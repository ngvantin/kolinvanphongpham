package com.pro.electronic.listener

import com.pro.electronic.model.Product

interface IOnAdminManagerProductListener {
    fun onClickUpdateProduct(product: Product?)
    fun onClickDeleteProduct(product: Product?)
}
