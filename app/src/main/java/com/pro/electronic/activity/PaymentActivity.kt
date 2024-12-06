package com.pro.electronic.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.pro.electronic.MyApplication.Companion.get
import com.pro.electronic.R
import com.pro.electronic.database.ProductDatabase.Companion.getInstance
import com.pro.electronic.event.DisplayCartEvent
import com.pro.electronic.event.OrderSuccessEvent
import com.pro.electronic.model.Order
import com.pro.electronic.utils.Constant
import com.pro.electronic.utils.GlobalFunction.startActivity
import org.greenrobot.eventbus.EventBus

class PaymentActivity : BaseActivity() {
    private var mOrderBooking: Order? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        dataIntent

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({ this.createOrderFirebase() }, 2000)
    }

    private val dataIntent: Unit
        get() {
            val bundle = intent.extras ?: return
            mOrderBooking = bundle[Constant.ORDER_OBJECT] as Order?
        }

    private fun createOrderFirebase() {
        get(this).orderDatabaseReference
            .child(mOrderBooking!!.id.toString())
            .setValue(mOrderBooking) { _: DatabaseError?, _: DatabaseReference? ->
                getInstance(this)!!
                    .productDAO()!!.deleteAllProduct()
                EventBus.getDefault().post(DisplayCartEvent())
                EventBus.getDefault().post(OrderSuccessEvent())

                val bundle = Bundle()
                bundle.putLong(Constant.ORDER_ID, mOrderBooking!!.id)
                startActivity(
                    this@PaymentActivity,
                    ReceiptOrderActivity::class.java, bundle
                )
                finish()
            }
    }
}