package com.kygoinc.mediamagnet.views.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kygoinc.mediamagnet.utils.Constants
import com.kygoinc.mediamagnet.views.fragments.MediaFragment


data class MediaViewPager(
   private val fragmentActivity: androidx.fragment.app.FragmentActivity,
    private val imagesType: String = Constants.MEDIA_TYPE_WHATSAPP_IMAGES,
    private val videosType: String = Constants.MEDIA_TYPE_WHATSAPP_VIDEOS,
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                val mediaFragment = MediaFragment()
                val bundle = Bundle()
                bundle.putString(Constants.MEDIA_TYPE_KEY, imagesType)
                mediaFragment.arguments = bundle
                mediaFragment
            }

            else -> {
                val mediaFragment = MediaFragment()
                val bundle = Bundle()
                bundle.putString(Constants.MEDIA_TYPE_KEY, videosType)
                mediaFragment.arguments = bundle
                mediaFragment
            }

        }
    }


}
