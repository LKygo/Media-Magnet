package com.kygoinc.mediamagnet.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kygoinc.mediamagnet.R
import com.kygoinc.mediamagnet.databinding.ActivityVideoPreviewBinding

class VideoPreview : AppCompatActivity() {
    private val activity = this
    private lateinit var binding: ActivityVideoPreviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_video_preview)
        binding = ActivityVideoPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}