package com.pro.electronic.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.pro.electronic.R
import com.pro.electronic.activity.admin.AdminMainActivity
import com.pro.electronic.prefs.DataStoreManager
import com.pro.electronic.utils.GlobalFunction.startActivity
import com.pro.electronic.utils.StringUtil.isEmpty

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({ this.goToActivity() }, 2000)
    }

    private fun goToActivity() {
        if (DataStoreManager.user != null
            && !isEmpty(DataStoreManager.user?.email)
        ) {
            if (DataStoreManager.user!!.isAdmin) {
                startActivity(this, AdminMainActivity::class.java)
            } else {
                startActivity(this, MainActivity::class.java)
            }
        } else {
            startActivity(this, LoginActivity::class.java)
        }
        finish()
    }
}
