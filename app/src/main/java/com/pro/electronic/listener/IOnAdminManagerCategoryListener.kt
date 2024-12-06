package com.pro.electronic.listener

import com.pro.electronic.model.Category

interface IOnAdminManagerCategoryListener {
    fun onClickUpdateCategory(category: Category?)
    fun onClickDeleteCategory(category: Category?)
    fun onClickItemCategory(category: Category?)
}
