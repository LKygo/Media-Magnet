package com.kygoinc.mediamagnet.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Constants
import com.kygoinc.mediamagnet.R
import com.kygoinc.mediamagnet.databinding.ActivityImagePreviewBinding
import com.kygoinc.mediamagnet.databinding.ActivityVideoPreviewBinding
import com.kygoinc.mediamagnet.models.MediaModel
import com.kygoinc.mediamagnet.views.adapters.ImagePreviewAdapter

class ImagePreview : AppCompatActivity() {
    private val activity = this
    private lateinit var binding: ActivityImagePreviewBinding
    private lateinit var imageAdapter: ImagePreviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_image_preview)
        binding = ActivityImagePreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            val list = intent.getSerializableExtra(com.kygoinc.mediamagnet.utils.Constants.MEDIA_LIST_KEY) as ArrayList<MediaModel>
            val scrollTo = intent.getIntExtra(com.kygoinc.mediamagnet.utils.Constants.MEDIA_SCROLL_KEY, 0)
            imageAdapter = ImagePreviewAdapter( list, activity)
            imageViewPager.adapter = imageAdapter
            imageViewPager.currentItem = scrollTo
        }

    }
}