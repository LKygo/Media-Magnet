package com.kygoinc.mediamagnet.viewmodels

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kygoinc.mediamagnet.data.StatusRepo
import com.kygoinc.mediamagnet.models.MEDIA_TYPE_IMAGE
import com.kygoinc.mediamagnet.models.MEDIA_TYPE_VIDEO
import com.kygoinc.mediamagnet.models.MediaModel
import com.kygoinc.mediamagnet.utils.Constants
import com.kygoinc.mediamagnet.utils.SharedPrefKeys
import com.kygoinc.mediamagnet.utils.SharedPrefUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StatusViewModel(val repo: StatusRepo) : ViewModel() {

    private val wpStatusLiveData get() = repo.whatsappStatusesLiveData
    private val wpBusinessStatusLiveData get() = repo.whatsappBusinessStatusesLiveData

    //    wp main
    val wpImagesLiveData = MutableLiveData<ArrayList<MediaModel>>()
    val wpVideosLiveData = MutableLiveData<ArrayList<MediaModel>>()

    //    wp business
    val wpBusinessImagesLiveData = MutableLiveData<ArrayList<MediaModel>>()
    val wpBusinessVideosLiveData = MutableLiveData<ArrayList<MediaModel>>()

    private var isPermissionGranted = false

    init {
        val wpPermissions =
            SharedPrefUtils.getPrefBoolean(SharedPrefKeys.PREF_KEY_WP_PERMISSION_GRANTED, false)
        val wpBusinessPermissions = SharedPrefUtils.getPrefBoolean(
            SharedPrefKeys.PREF_KEY_WP_BUSINESS_PERMISSION_GRANTED, false
        )

        isPermissionGranted = wpPermissions && wpBusinessPermissions

        if (isPermissionGranted) {
            CoroutineScope(Dispatchers.IO).launch {
                repo.getAllStatuses()

            }
            CoroutineScope(Dispatchers.IO).launch {
                repo.getAllStatuses(Constants.TYPE_WHATSAPP_BUSINESS)

            }
        }

    }

    fun getWpStatuses() {
            CoroutineScope(Dispatchers.IO).launch {
                if (!isPermissionGranted) {
                    repo.getAllStatuses()
                }
                withContext(Dispatchers.Main) {
                    getWhatsAppImages()
                    getWhatsAppVideos()
                }
            }


    }

    fun getWhatsAppImages() {
        wpStatusLiveData.observe(repo.activity as LifecycleOwner) {
            val tempList = ArrayList<MediaModel>()
            it.forEach { mediaModel ->

                if (mediaModel.type == MEDIA_TYPE_IMAGE) {
                    tempList.add(mediaModel)

                }
            }
            wpImagesLiveData.postValue(tempList)
        }
    }

    fun getWhatsAppVideos() {
        wpStatusLiveData.observe(repo.activity as LifecycleOwner) {
            val tempList = ArrayList<MediaModel>()
            it.forEach { mediaModel ->

                if (mediaModel.type == MEDIA_TYPE_VIDEO) {
                    tempList.add(mediaModel)

                }
            }
            wpVideosLiveData.postValue(tempList)
        }
    }

    fun getWpBusinessStatuses() {
            CoroutineScope(Dispatchers.IO).launch {
                if (!isPermissionGranted) {
                    repo.getAllStatuses(Constants.TYPE_WHATSAPP_BUSINESS)
                }
                withContext(Dispatchers.Main) {
                    getWhatsAppBusinessImages()

                    getWhatsAppBusinessVideos()
                }


        }
    }

    fun getWhatsAppBusinessImages() {
        wpBusinessStatusLiveData.observe(repo.activity as LifecycleOwner) {
            val tempList = ArrayList<MediaModel>()
            it.forEach { mediaModel ->

                if (mediaModel.type == MEDIA_TYPE_IMAGE) {
                    tempList.add(mediaModel)

                }
            }
            wpBusinessImagesLiveData.postValue(tempList)
        }
    }

    fun getWhatsAppBusinessVideos() {
        wpBusinessStatusLiveData.observe(repo.activity as LifecycleOwner) {
            val tempList = ArrayList<MediaModel>()
            it.forEach { mediaModel ->

                if (mediaModel.type == MEDIA_TYPE_VIDEO) {
                    tempList.add(mediaModel)

                }
            }
            wpBusinessVideosLiveData.postValue(tempList)
        }
    }


}