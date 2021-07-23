package com.example.cyptonotice.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cyptonotice.MainActivity
import com.example.cyptonotice.R
import com.example.cyptonotice.ui.main.MainFragment.Companion.newInstance
import kotlinx.android.synthetic.main.main_fragment.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request.Builder
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

const val API_URL = "https://bitflyer.com/api/echo/price"//追加

class MainFragment : Fragment() {

    private val client = OkHttpClient()
    var currentBit = "5000000"

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentRateAPI(API_URL)
        val btnSetHigh: Button = view.findViewById(R.id.btnSetHigh)
        val btnSetLow: Button = view.findViewById(R.id.btnSetLow)
        val btnGetCurrentBit: Button = view.findViewById(R.id.btnGetCurrentBit)
        btnSetHigh.setOnClickListener {
            onbtnHighClick()
        }

        btnSetLow.setOnClickListener {
            onbtnLowClick()
        }

        btnGetCurrentBit.setOnClickListener {
            currentRateAPI(API_URL)
            textCurrentBit.text = currentBit
        }
        // Switchに、状態変更イベントを追加
        switchNotification.setOnCheckedChangeListener { buttonView, isChecked ->
            // 通知がオンの時の挙動
            if(isChecked){
                (activity as MainActivity?)?.notificationAlerm()
            }

        }

        textCurrentBit.text = currentBit
    }

    fun onbtnHighClick() {
        Log.i("NewItemFragment", "onbtnHighClick")
        // ボタン押下時の処理
        // SharedPrefeに既に入っている高値段を削除する
        // SharedPrefeに高値段を登録する
        // 通知の有無のif文の条件判定に値を入れる(SharedPrefeで良いかな？)
        //値の設定画面へ
        val fragmentManager = fragmentManager

        if (fragmentManager != null) {
            val fragmentTransaction = fragmentManager.beginTransaction()
            // BackStackを設定
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.replace(R.id.container, SetPriceFragment())
            fragmentTransaction.commit()
        }



    }

    fun onbtnLowClick() {
        Log.i("NewItemFragment", "onbtnLowClick")
        // ボタン押下時の処理
    }

    fun currentRateAPI(url: String) {
        val request = Builder()
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
                println("${mid}" + mid)//追加
                println("${ask}" + ask)//追加
                println("${bid}" + bid)//追加
                currentBit = mid.toString()
            }

        })
    }

}