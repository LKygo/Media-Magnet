package com.kygoinc.mediamagnet.views.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kygoinc.mediamagnet.R
import com.kygoinc.mediamagnet.databinding.ItemMediaBinding
import com.kygoinc.mediamagnet.models.MEDIA_TYPE_IMAGE
import com.kygoinc.mediamagnet.models.MediaModel
import com.kygoinc.mediamagnet.utils.Constants
import com.kygoinc.mediamagnet.utils.saveStatus
import com.kygoinc.mediamagnet.views.activities.ImagePreview
import com.kygoinc.mediamagnet.views.activities.VideoPreview

class MediaAdapter(val list: ArrayList<MediaModel>, val context: Context) :
    RecyclerView.Adapter<MediaAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemMediaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: MediaModel) {
            binding.apply {
                Glide.with(context).load(model.pathUri).into(statusImage)
                if (model.type == MEDIA_TYPE_IMAGE) {
                    statusPlay.visibility = ViewGroup.GONE
                } else {
                    statusPlay.visibility = ViewGroup.VISIBLE
                }

                val downloadImage = if (model.isDownloaded) {
                    R.drawable.baseline_check_24
                } else {
                    R.drawable.save
                }
                statusDownload.setImageResource(downloadImage)
                cardViewStatus.setOnClickListener {
                    if (model.type == MEDIA_TYPE_IMAGE) {
//                        Open image preview activity
                        Intent().apply {
                            putExtra(Constants.MEDIA_LIST_KEY, list)
                            putExtra(Constants.MEDIA_SCROLL_KEY, layoutPosition)
                            setClass(context, ImagePreview::class.java)
                            context.startActivity(this)
                        }
                    } else {
//                        Open video preview activity
                        Intent().apply {
                            putExtra(Constants.MEDIA_LIST_KEY, list)
                            putExtra(Constants.MEDIA_SCROLL_KEY, layoutPosition)
                            setClass(context, VideoPreview::class.java)
                            context.startActivity(this)
                        }

                    }
                }

                statusDownload.setOnClickListener {
                    val isDownloaded = context.saveStatus(model)
                    if (isDownloaded) {
                        Toast.makeText(context, "Status Saved", Toast.LENGTH_SHORT).show()
                        model.isDownloaded = true

                        statusDownload.setImageResource(R.drawable.baseline_check_24)
                    } else {
                        Toast.makeText(context, "Failed to Download", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemMediaBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]
        holder.bind(model)

    }
}