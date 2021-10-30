package com.example.cyptonotice.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import com.example.cyptonotice.MainActivity
import com.example.cyptonotice.R
import kotlinx.android.synthetic.main.fragment_set_price.*
import kotlinx.android.synthetic.main.main_fragment.*

class SetPriceFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_set_price, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val btnxxx: Button = view.findViewById(R.id.btnxxx)
        val pickerHighPtice: NumberPicker = view.findViewById(R.id.pickerHighPtice)
//        btnxxx.setOnClickListener {
//            //
//        }
        // Switchに、状態変更イベントを追加
        switchNotificationHighPrice.setOnCheckedChangeListener { buttonView, isChecked ->
            // 通知がオンの時の挙動
            if(isChecked){
                (activity as MainActivity?)?.notificationAlerm()
            } else {
                pickerHighPtice.displayedValues = arrayOf("0")
            }

        }
        //ドラムロール表示用の配列作成
        val fruits = arrayOf("100万円", "200万円", "300万円", "400万円", "500万円")
        //配列のインデックス最小、最大を指定
        pickerHighPtice.minValue = 0
        pickerHighPtice.maxValue = fruits.size - 1
        //NumberPickerに配列をセットする
        pickerHighPtice.displayedValues = fruits
    }
}