package com.kygoinc.mediamagnet.views.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kygoinc.mediamagnet.R
import com.kygoinc.mediamagnet.databinding.FragmentMediaBinding
import com.kygoinc.mediamagnet.utils.Constants

class MediaFragment : Fragment() {

    private lateinit var binding: FragmentMediaBinding
    private lateinit var type: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMediaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            type = it.getString(Constants.FRAGMENT_TYPE_KEY, "")
            val mediaType = it.getString(Constants.MEDIA_TYPE_KEY, "")
            Log.d("temptxv", "${mediaType}")
            binding.txvMedia.text = mediaType
        }

    }
//
//    <com.airbnb.lottie.LottieAnimationView
//    android:layout_width="match_parent"
//    android:layout_height="match_parent"
//    android:id="@+id/animation_view"
//    app:lottie_rawRes="@raw/loading"
//    app:lottie_autoPlay="true"
//    app:lottie_loop="true"/>
}