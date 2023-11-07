package com.kygoinc.mediamagnet.data

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.core.net.toUri
import androidx.documentfile.provider.DocumentFile
import androidx.lifecycle.MutableLiveData
import com.kygoinc.mediamagnet.models.MEDIA_TYPE_IMAGE
import com.kygoinc.mediamagnet.models.MEDIA_TYPE_VIDEO
import com.kygoinc.mediamagnet.models.MediaModel
import com.kygoinc.mediamagnet.utils.Constants
import com.kygoinc.mediamagnet.utils.SharedPrefKeys
import com.kygoinc.mediamagnet.utils.SharedPrefUtils
import com.kygoinc.mediamagnet.utils.isStatusExist

class StatusRepo(val context: Context) {

    val whatsappStatusesLiveData = MutableLiveData<ArrayList<MediaModel>>()
    val whatsappBusinessStatusesLiveData = MutableLiveData<ArrayList<MediaModel>>()

    val activity = context as Activity

    private val wpStatusesList = ArrayList<MediaModel>()
    private val wpBusinessStatusesList = ArrayList<MediaModel>()

    fun getAllStatuses(whatsAppType: String = Constants.TYPE_WHATSAPP_MAIN) {
        val treeUri = when (whatsAppType) {
            Constants.TYPE_WHATSAPP_MAIN -> {
                SharedPrefUtils.getPrefString(SharedPrefKeys.PREF_KEY_WP_TREE_URI, "")?.toUri()!!
            }

            else -> {
                SharedPrefUtils.getPrefString(SharedPrefKeys.PREF_KEY_WP_BUSINESS_TREE_URI, "")
                    ?.toUri()!!
            }
        }
        activity.contentResolver?.takePersistableUriPermission(
            treeUri,
            Intent.FLAG_GRANT_READ_URI_PERMISSION
        )
        val fileDocument = DocumentFile.fromTreeUri(activity, treeUri)

        fileDocument?.let {

            it.listFiles()?.forEach { file ->
                if (file.name != ".nomedia" && file.isFile) {
                    val isDownloaded = context.isStatusExist(file.name!!)
                    val type = if (file.name!!.endsWith(".mp4")) {
                        MEDIA_TYPE_VIDEO
                    } else {
                        MEDIA_TYPE_IMAGE
                    }
                    val mediaModel = MediaModel(
                        file.uri.toString(),
                        file.name!!,
                        type,
                        isDownloaded
                    )
                    when (whatsAppType) {
                        Constants.TYPE_WHATSAPP_MAIN -> {
                            wpStatusesList.add(mediaModel)
                        }

                        else -> {
                            wpBusinessStatusesList.add(mediaModel)
                        }
                    }
                }
            }
        }

            when (whatsAppType) {
                Constants.TYPE_WHATSAPP_MAIN -> {
                    whatsappStatusesLiveData.postValue(wpStatusesList)
                }

                else -> {
                    whatsappBusinessStatusesLiveData.postValue(wpBusinessStatusesList)
                }
            }
    }
}