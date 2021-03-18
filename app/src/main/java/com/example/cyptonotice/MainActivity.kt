package com.example.cyptonotice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cyptonotice.ui.main.MainFragment
import kotlinx.android.synthetic.main.main_activity.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

//const val API_URL = "https://bitflyer.com/"削除
const val API_URL = "https://bitflyer.com/api/echo/price"//追加

class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
        btn.setOnClickListener {
            run(API_URL)
        }
    }

    fun run(url: String) {
        val request = Request.Builder()
                .url(url)
                .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("失敗")
            }

            // override fun onResponse(call: Call, response: Response) = println(response.body()?.string())
            override fun onResponse(call: Call, response: Response) {
                val jsonText = (response.body()?.string())
                val parentJsonObj = JSONObject(jsonText)
                //val parentJsonArray = parentJsonObj.getJSONArray("mid") 削除
                val mid = parentJsonObj.getDouble("mid")//追加
                val ask = parentJsonObj.getDouble("ask")//追加
                val bid = parentJsonObj.getDouble("bid")//追加
                //println(parentJsonArray)削除
                println("${mid}" + mid)//追加
                println("${ask}" + ask)//追加
                println("${bid}" + bid)//追加

                // val parentJsonObjStr: String = response.getParameter("mid") 失敗例
            }

        })
    }
}