package com.kygoinc.mediamagnet.views.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.kygoinc.mediamagnet.R
import com.kygoinc.mediamagnet.data.StatusRepo
import com.kygoinc.mediamagnet.databinding.FragmentMediaBinding
import com.kygoinc.mediamagnet.models.MediaModel
import com.kygoinc.mediamagnet.utils.Constants
import com.kygoinc.mediamagnet.viewmodels.StatusViewModel
import com.kygoinc.mediamagnet.viewmodels.factories.StatusViewModelFactories
import com.kygoinc.mediamagnet.views.adapters.MediaAdapter

class MediaFragment : Fragment() {

    private lateinit var binding: FragmentMediaBinding
    private lateinit var type: String
    private lateinit var statusViewModel: StatusViewModel
    private lateinit var mediaAdapter: MediaAdapter
    private lateinit var rcvMediaRecycler: androidx.recyclerview.widget.RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMediaBinding.inflate(inflater, container, false)
        rcvMediaRecycler = binding.rcvMediaRecycler

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {

            val repo = StatusRepo(requireActivity())
            statusViewModel = ViewModelProvider(
                requireActivity(),
                StatusViewModelFactories(repo)
            )[StatusViewModel::class.java]

            type = it.getString(Constants.FRAGMENT_TYPE_KEY, "")
            val mediaType = it.getString(Constants.MEDIA_TYPE_KEY, "")
            Log.d("temptxv", "${mediaType}")
//            binding.txvMedia.text = mediaType

            when (mediaType) {
                Constants.MEDIA_TYPE_WHATSAPP_IMAGES -> {
                    statusViewModel.wpImagesLiveData.observe(requireActivity()) { unFilteredList ->
                        val filteredList = unFilteredList.distinctBy { model ->
                            model.fileName
                        }
                        val list = ArrayList<MediaModel>()
                        filteredList.forEach { model ->

                            list.add(model)
                        }

                        mediaAdapter = MediaAdapter(list, requireActivity())
                        rcvMediaRecycler.adapter = mediaAdapter
                    }
                }

                Constants.MEDIA_TYPE_WHATSAPP_VIDEOS -> {
                    statusViewModel.wpVideosLiveData.observe(requireActivity()) { unFilteredList ->
                        val filteredList = unFilteredList.distinctBy { model ->
                            model.fileName
                        }
                        val list = ArrayList<MediaModel>()
                        filteredList.forEach { model ->

                            list.add(model)
                        }

                        mediaAdapter = MediaAdapter(list, requireActivity())
                        rcvMediaRecycler.adapter = mediaAdapter
                    }
                }

                Constants.MEDIA_TYPE_WHATSAPP_BUSINESS_IMAGES -> {

                    statusViewModel.wpBusinessImagesLiveData.observe(requireActivity()) { unFilteredList ->
                        val filteredList = unFilteredList.distinctBy { model ->
                            model.fileName
                        }
                        val list = ArrayList<MediaModel>()
                        filteredList.forEach { model ->

                            list.add(model)
                        }

                        mediaAdapter = MediaAdapter(list, requireActivity())

                        rcvMediaRecycler.adapter = mediaAdapter
                    }
                }

                Constants.MEDIA_TYPE_WHATSAPP_BUSINESS_VIDEOS -> {

                    statusViewModel.wpBusinessVideosLiveData.observe(requireActivity()) { unFilteredList ->
                        val filteredList = unFilteredList.distinctBy { model ->
                            model.fileName
                        }
                        val list = ArrayList<MediaModel>()
                        filteredList.forEach { model ->

                            list.add(model)
                        }

                        mediaAdapter = MediaAdapter(list, requireActivity())
                        rcvMediaRecycler.adapter = mediaAdapter
                    }
                }

            }

        }
//
//    <com.airbnb.lottie.LottieAnimationView
//    android:layout_width="match_parent"
//    android:layout_height="match_parent"
//    android:id="@+id/animation_view"
//    app:lottie_rawRes="@raw/loading"
//    app:lottie_autoPlay="true"
//    app:lottie_loop="true"/>
    }
}