package com.kygoinc.mediamagnet.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kygoinc.mediamagnet.R
import com.kygoinc.mediamagnet.databinding.ActivityImagePreviewBinding
import com.kygoinc.mediamagnet.databinding.ActivityVideoPreviewBinding

class ImagePreview : AppCompatActivity() {
    private val activity = this
    private val binding by lazy {
        ActivityImagePreviewBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_preview)
        binding.apply {  }
    }
}