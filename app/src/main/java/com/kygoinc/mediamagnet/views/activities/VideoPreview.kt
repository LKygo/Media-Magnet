package com.kygoinc.mediamagnet.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.kygoinc.mediamagnet.R
import com.kygoinc.mediamagnet.databinding.ActivityVideoPreviewBinding
import com.kygoinc.mediamagnet.models.MediaModel
import com.kygoinc.mediamagnet.views.adapters.VideoPreviewAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VideoPreview : AppCompatActivity() {
    private val activity = this
    private lateinit var binding: ActivityVideoPreviewBinding
    lateinit var videoAdapter: VideoPreviewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_video_preview)
        binding = ActivityVideoPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            val list =
                intent.getSerializableExtra(com.kygoinc.mediamagnet.utils.Constants.MEDIA_LIST_KEY) as ArrayList<MediaModel>
            val scrollTo =
                intent.getIntExtra(com.kygoinc.mediamagnet.utils.Constants.MEDIA_SCROLL_KEY, 0)
            videoAdapter = VideoPreviewAdapter(list, activity)
            rcvVideoViewer.adapter = videoAdapter
            val pageSnap = PagerSnapHelper()
            pageSnap.attachToRecyclerView(rcvVideoViewer)
            rcvVideoViewer.scrollToPosition(scrollTo)

            rcvVideoViewer.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState != RecyclerView.SCROLL_STATE_DRAGGING) {
                        stopAllPlayers()
                    }
                }
            })
        }
    }

    private fun stopAllPlayers() {
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                binding.apply {
                    for (i in 0 until rcvVideoViewer.childCount) {
                        val child = rcvVideoViewer.getChildAt(i)
                        val viewHolder =
                            rcvVideoViewer.getChildViewHolder(child)
                        if (viewHolder is VideoPreviewAdapter.ViewHolder) {
                            viewHolder.stopPlayer()
                        }
                    }
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        stopAllPlayers()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopAllPlayers()
    }
}