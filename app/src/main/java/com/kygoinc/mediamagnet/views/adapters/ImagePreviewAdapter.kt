package com.kygoinc.mediamagnet.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kygoinc.mediamagnet.R
import com.kygoinc.mediamagnet.databinding.ItemImagePreviewBinding
import com.kygoinc.mediamagnet.databinding.ItemMediaBinding
import com.kygoinc.mediamagnet.models.MediaModel
import com.kygoinc.mediamagnet.utils.saveStatus

class ImagePreviewAdapter(val list: ArrayList<MediaModel>, val context: Context) :
    RecyclerView.Adapter<ImagePreviewAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemImagePreviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: MediaModel) {
            binding.apply {
                Glide.with(context).load(model.pathUri).into(zoomableImageView)

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
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImagePreviewAdapter.ViewHolder {
        return ViewHolder(
            ItemImagePreviewBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ImagePreviewAdapter.ViewHolder, position: Int) {
        val model = list[position]
        holder.bind(model)
    }
}