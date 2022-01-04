package com.wl.android

import android.app.Application
import android.util.Log
import com.idlefish.flutterboost.FlutterBoost
import com.idlefish.flutterboost.FlutterBoostDelegate
import com.idlefish.flutterboost.FlutterBoostRouteOptions
import com.idlefish.flutterboost.containers.FlutterBoostActivity
import io.flutter.embedding.android.FlutterActivityLaunchConfigs

/**
 * @author wangliang at 2021/12/30
 */
object FlutterHelper {
    @JvmStatic
    fun init(application: Application) {
        val delegate = object : FlutterBoostDelegate {

            override fun pushNativeRoute(options: FlutterBoostRouteOptions?) {
                //TODO native
            }

            override fun pushFlutterRoute(options: FlutterBoostRouteOptions?) {
                val optionsNotNull = options ?: return
                Log.d("WLTest", "pushFlutterRoute ${optionsNotNull.pageName()}")
                val intent = FlutterBoostActivity.CachedEngineIntentBuilder(FlutterBoostActivity::class.java)
                    .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.transparent)
                    .destroyEngineWithActivity(false)
                    .uniqueId(optionsNotNull.uniqueId())
                    .url(optionsNotNull.pageName())
                    .urlParams(optionsNotNull.arguments())
                    .build(FlutterBoost.instance().currentActivity())
                FlutterBoost.instance().currentActivity().startActivity(intent)
            }

        }

        FlutterBoost.instance().setup(application, delegate) { engine ->
        }
    }

    fun openFlutterPage(options: FlutterBoostRouteOptions) {
        FlutterBoost.instance().open(options.pageName(), options.arguments())
    }

}