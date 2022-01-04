package com.wl.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.idlefish.flutterboost.FlutterBoost
import com.idlefish.flutterboost.FlutterBoostRouteOptions

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnFlutterMainPage).setOnClickListener {
            FlutterHelper.openFlutterPage(FlutterBoostRouteOptions.Builder()
                .pageName("mainPage")
                .arguments(HashMap<String, Any>().apply {
                    put("data", "test params")
                })
                .build())
        }
    }
}