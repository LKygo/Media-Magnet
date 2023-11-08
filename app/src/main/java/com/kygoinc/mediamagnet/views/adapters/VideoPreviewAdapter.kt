package com.kygoinc.mediamagnet.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kygoinc.mediamagnet.R
import com.kygoinc.mediamagnet.databinding.ItemImagePreviewBinding
import com.kygoinc.mediamagnet.databinding.ItemMediaBinding
import com.kygoinc.mediamagnet.databinding.ItemVideoPreviewBinding
import com.kygoinc.mediamagnet.models.MediaModel
import com.kygoinc.mediamagnet.utils.saveStatus

class VideoPreviewAdapter(val list: ArrayList<MediaModel>, val context: Context) :
    RecyclerView.Adapter<VideoPreviewAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemVideoPreviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: MediaModel) {
            binding.apply {

                val player = ExoPlayer.Builder(context).build()
                playerView.player = player

                val mediaItem = MediaItem.fromUri(model.pathUri)

                player.setMediaItem(mediaItem)
                player.prepare()


                val downloadImage = if (model.isDownloaded) {
                    R.drawable.baseline_check_24
                } else {
                    R.drawable.save
                }
                tools.toolsSave.setImageResource(downloadImage)

                tools.statusDownload.setOnClickListener {
                    val isDownloaded = context.saveStatus(model)
                    if (isDownloaded) {
                        Toast.makeText(context, "Status Saved", Toast.LENGTH_SHORT).show()
                        model.isDownloaded = true

                        tools.toolsSave.setImageResource(R.drawable.baseline_check_24)
                    } else {
                        Toast.makeText(context, "Failed to Download", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }

        fun stopPlayer() {
            binding.playerView.player?.stop()
        }
    }



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VideoPreviewAdapter.ViewHolder {
        return ViewHolder(
            ItemVideoPreviewBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: VideoPreviewAdapter.ViewHolder, position: Int) {
        val model = list[position]
        holder.bind(model)
    }
}