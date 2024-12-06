package com.pro.electronic.listener

import com.pro.electronic.model.Voucher

interface IOnAdminManagerVoucherListener {
    fun onClickUpdateVoucher(voucher: Voucher?)
    fun onClickDeleteVoucher(voucher: Voucher?)
}
