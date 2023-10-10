package com.kygoinc.mediamagnet.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kygoinc.mediamagnet.R
import com.kygoinc.mediamagnet.databinding.ActivityVideoPreviewBinding

class VideoPreview : AppCompatActivity() {
    private val activity = this
    private val binding by lazy {
        ActivityVideoPreviewBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_preview)

        binding.apply {  }
    }
}