package com.kygoinc.mediamagnet.views.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kygoinc.mediamagnet.R
import com.kygoinc.mediamagnet.databinding.FragmentStatusBinding
import com.kygoinc.mediamagnet.utils.Constants


class StatusFragment : Fragment() {
  private val binding by lazy{
      FragmentStatusBinding.inflate(layoutInflater)
  }
    private lateinit var type:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding.apply{

          arguments?.let {
              type = it.getString(Constants.FRAGMENT_TYPE_KEY, "")
              Log.d("temptxv", type)
             txvTempText.text = type
          }
      }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root


}