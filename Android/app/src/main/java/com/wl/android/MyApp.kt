package com.wl.android

import android.app.Application
import com.idlefish.flutterboost.FlutterBoost
import com.idlefish.flutterboost.FlutterBoostDelegate
import com.idlefish.flutterboost.FlutterBoostRouteOptions
import com.idlefish.flutterboost.containers.FlutterBoostActivity
import io.flutter.embedding.android.FlutterActivityLaunchConfigs

/**
 * @author wangliang at 2021/12/30
 */
class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        FlutterHelper.init(this)
    }

}