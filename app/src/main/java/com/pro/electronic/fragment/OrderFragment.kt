package com.pro.electronic.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.pro.electronic.MyApplication.Companion.get
import com.pro.electronic.R
import com.pro.electronic.activity.ReceiptOrderActivity
import com.pro.electronic.activity.TrackingOrderActivity
import com.pro.electronic.adapter.OrderAdapter
import com.pro.electronic.adapter.OrderAdapter.IClickOrderListener
import com.pro.electronic.model.Order
import com.pro.electronic.model.TabOrder
import com.pro.electronic.prefs.DataStoreManager
import com.pro.electronic.utils.Constant
import com.pro.electronic.utils.GlobalFunction.startActivity

class OrderFragment : Fragment() {
    private var mView: View? = null

    private var orderTabType = 0
    private var listOrder: MutableList<Order>? = null
    private var orderAdapter: OrderAdapter? = null
    private var mOrderAllValueEventListener: ValueEventListener? = null
    private var mOrderValueEventListener: ValueEventListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_order, container, false)

        dataArguments
        initUi()
        if (DataStoreManager.user!!.isAdmin) {
            listOrderAllUsersFromFirebase
        } else {
            listOrderFromFirebase
        }

        return mView
    }

    private val dataArguments: Unit
        get() {
            val bundle = arguments ?: return
            orderTabType = bundle.getInt(Constant.ORDER_TAB_TYPE)
        }

    private fun initUi() {
        listOrder = ArrayList()
        val rcvOrder = mView!!.findViewById<RecyclerView>(R.id.rcv_order)
        val linearLayoutManager = LinearLayoutManager(activity)
        rcvOrder.layoutManager = linearLayoutManager
        orderAdapter = OrderAdapter(activity, listOrder, object : IClickOrderListener {
            override fun onClickTrackingOrder(orderId: Long) {
                val bundle = Bundle()
                bundle.putLong(Constant.ORDER_ID, orderId)
                startActivity(activity!!, TrackingOrderActivity::class.java, bundle)
            }

            override fun onClickReceiptOrder(order: Order?) {
                val bundle = Bundle()
                bundle.putLong(Constant.ORDER_ID, order!!.id)
                startActivity(activity!!, ReceiptOrderActivity::class.java, bundle)
            }
        })
        rcvOrder.adapter = orderAdapter
    }

    @get:SuppressLint("NotifyDataSetChanged")
    private val listOrderAllUsersFromFirebase: Unit
        get() {
            if (activity == null) return
            mOrderAllValueEventListener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (listOrder != null) {
                        listOrder!!.clear()
                    } else {
                        listOrder = ArrayList()
                    }
                    for (dataSnapshot in snapshot.children) {
                        val order = dataSnapshot.getValue(
                            Order::class.java
                        )
                        if (order != null) {
                            if (TabOrder.TAB_ORDER_PROCESS == orderTabType) {
                                if (Order.STATUS_COMPLETE != order.status) {
                                    listOrder!!.add(0, order)
                                }
                            } else if (TabOrder.TAB_ORDER_DONE == orderTabType) {
                                if (Order.STATUS_COMPLETE == order.status) {
                                    listOrder!!.add(0, order)
                                }
                            }
                        }
                    }
                    if (orderAdapter != null) orderAdapter!!.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                }
            }
            get(requireActivity()).orderDatabaseReference
                .addValueEventListener(mOrderAllValueEventListener as ValueEventListener)
        }

    @get:SuppressLint("NotifyDataSetChanged")
    private val listOrderFromFirebase: Unit
        get() {
            if (activity == null) return
            mOrderValueEventListener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (listOrder != null) {
                        listOrder!!.clear()
                    } else {
                        listOrder = ArrayList()
                    }
                    for (dataSnapshot in snapshot.children) {
                        val order = dataSnapshot.getValue(
                            Order::class.java
                        )
                        if (order != null) {
                            if (TabOrder.TAB_ORDER_PROCESS == orderTabType) {
                                if (Order.STATUS_COMPLETE != order.status) {
                                    listOrder!!.add(0, order)
                                }
                            } else if (TabOrder.TAB_ORDER_DONE == orderTabType) {
                                if (Order.STATUS_COMPLETE == order.status) {
                                    listOrder!!.add(0, order)
                                }
                            }
                        }
                    }
                    if (orderAdapter != null) orderAdapter!!.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                }
            }
            get(requireActivity()).orderDatabaseReference
                .orderByChild("userEmail").equalTo(DataStoreManager.user?.email)
                .addValueEventListener(mOrderValueEventListener as ValueEventListener)
        }

    override fun onDestroyView() {
        super.onDestroyView()
        orderAdapter?.release()
        if (mOrderAllValueEventListener != null) {
            get(requireActivity()).orderDatabaseReference
                .removeEventListener(mOrderAllValueEventListener!!)
        }
        if (mOrderValueEventListener != null) {
            get(requireActivity()).orderDatabaseReference
                .removeEventListener(mOrderValueEventListener!!)
        }
    }

    companion object {
        fun newInstance(type: Int): OrderFragment {
            val orderFragment = OrderFragment()
            val bundle = Bundle()
            bundle.putInt(Constant.ORDER_TAB_TYPE, type)
            orderFragment.arguments = bundle
            return orderFragment
        }
    }
}