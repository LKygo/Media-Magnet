package com.kygoinc.mediamagnet.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kygoinc.mediamagnet.R
import com.kygoinc.mediamagnet.databinding.ItemMediaBinding
import com.kygoinc.mediamagnet.models.MEDIA_TYPE_IMAGE
import com.kygoinc.mediamagnet.models.MediaModel

class MediaAdapter(val list: ArrayList<MediaModel>, val context: Context) :
    RecyclerView.Adapter<MediaAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemMediaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: MediaModel) {
            binding.apply {
                Glide.with(context).load(model.pathUri).into(statusImage)
                val downloadImage = if (model.isDownloaded) {
                    R.drawable.baseline_check_24
                } else {
                    R.drawable.save
                }
                statusDownload.setImageResource(downloadImage)
                statusDownload.setOnClickListener {
                    if (model.type == MEDIA_TYPE_IMAGE) {
//                        Open image preview activity

                    } else {
//                        Open video preview activity

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