package com.kygoinc.mediamagnet.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kygoinc.mediamagnet.R
import com.kygoinc.mediamagnet.databinding.FragmentMediaBinding

class MediaFragment : Fragment() {

    private val binding by lazy{
        FragmentMediaBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {

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