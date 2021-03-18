package com.example.cyptonotice.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.cyptonotice.R

class MainFragment : Fragment() {

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
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnSetHigh: Button = view.findViewById(R.id.btnSetHigh)
        val btnSetLow: Button = view.findViewById(R.id.btnSetLow)
        btnSetHigh.setOnClickListener {
            onbtnHighClick()
        }
        btnSetLow.setOnClickListener {
            onbtnLowClick()
        }
    }
    fun onbtnHighClick(){
        Log.i("NewItemFragment","onbtnHighClick")
        // ボタン押下時の処理
    }
    fun onbtnLowClick(){
        Log.i("NewItemFragment","onbtnLowClick")
        // ボタン押下時の処理
    }

}