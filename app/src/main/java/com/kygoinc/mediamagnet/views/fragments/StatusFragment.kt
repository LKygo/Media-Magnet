package com.kygoinc.mediamagnet.views.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.kygoinc.mediamagnet.data.StatusRepo
import com.kygoinc.mediamagnet.databinding.FragmentStatusBinding
import com.kygoinc.mediamagnet.utils.Constants
import com.kygoinc.mediamagnet.utils.SharedPrefKeys
import com.kygoinc.mediamagnet.utils.SharedPrefUtils
import com.kygoinc.mediamagnet.utils.getFolderPermissions
import com.kygoinc.mediamagnet.viewmodels.StatusViewModel
import com.kygoinc.mediamagnet.viewmodels.factories.StatusViewModelFactories
import com.kygoinc.mediamagnet.views.adapters.MediaViewPager


class StatusFragment : Fragment() {
    private lateinit var binding: FragmentStatusBinding
    private lateinit var type: String
    private val WHATSAPP_REQUEST_CODE = 101
    private val WHATSAPP_BUSINESS_REQUEST_CODE = 102

    private val viewPagerTitles = arrayListOf("Images", "Videos")
    lateinit var statusViewModel: StatusViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStatusBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {

            val repo = StatusRepo(requireContext())
            statusViewModel = ViewModelProvider(
                requireActivity(),
                StatusViewModelFactories(repo)
            )[StatusViewModel::class.java]


            type = it.getString(Constants.FRAGMENT_TYPE_KEY, "")
            Log.d("temptxv", "$type")
            binding.txvTempText.text = type


            when (type) {
                Constants.TYPE_WHATSAPP_BUSINESS -> {

//                   check permissions
                    val isPermissionGranted = SharedPrefUtils.getPrefBoolean(
                        SharedPrefKeys.PREF_KEY_WP_BUSINESS_PERMISSION_GRANTED, false
                    )
                    if (!isPermissionGranted) {
                        getWhatsappBusinessStatuses()


                    }

//                    Fetch status when granted
//                    get permissions
                    binding.permissionLayout.btnAllowPermissions.setOnClickListener {

                        getFolderPermissions(
                            REQUEST_CODE = WHATSAPP_BUSINESS_REQUEST_CODE,
                            context = this.requireContext(),
                            initialUri = Constants.getWhatsappBusinessUri()
                        )

                    }

//                    fetch status
                    val viewPagerAdapter = MediaViewPager(requireActivity())
                    binding.statusViewPager.adapter = viewPagerAdapter
                    TabLayoutMediator(binding.tabLayout, binding.statusViewPager) { tab, position ->
                        tab.text = viewPagerTitles[position]
                    }.attach()

                }

                Constants.TYPE_WHATSAPP_MAIN -> {

//                    Check permissions
                    val isPermissionGranted = SharedPrefUtils.getPrefBoolean(
                        SharedPrefKeys.PREF_KEY_WP_PERMISSION_GRANTED, false
                    )
                    if (isPermissionGranted) {
                        getWhatsappSatuses()
                    } else {
                        binding.permissionLayoutHolder.visibility = View.VISIBLE
                    }

//                    Get permissions
                    binding.permissionLayout.btnAllowPermissions.setOnClickListener {

                        getFolderPermissions(
                            REQUEST_CODE = WHATSAPP_REQUEST_CODE,
                            context = this.requireContext(),
                            initialUri = Constants.getWhatsappUri()
                        )
                    }

                    val viewPagerAdapter = MediaViewPager(
                        requireActivity(),
                        imagesType = Constants.MEDIA_TYPE_WHATSAPP_IMAGES,
                        videosType = Constants.MEDIA_TYPE_WHATSAPP_VIDEOS
                    )
                    binding.statusViewPager.adapter = viewPagerAdapter
                    TabLayoutMediator(binding.tabLayout, binding.statusViewPager) { tab, position ->
                        tab.text = viewPagerTitles[position]
                    }.attach()
                }


//                Constants.TYPE_INSTAGRAM -> {
//                    binding.animationView.setAnimation(R.raw.instagram)
//                    binding.animationView.playAnimation()
//                }
//
//                Constants.TYPE_FACEBOOK -> {
//                    binding.animationView.setAnimation(R.raw.facebook)
//                    binding.animationView.playAnimation()
//                }
//
//                Constants.TYPE_TWITTER -> {
//                    binding.animationView.setAnimation(R.raw.twitter)
//                    binding.animationView.playAnimation()
//                }
//
//                Constants.TYPE_LINKEDIN -> {
//                    binding.animationView.setAnimation(R.raw.linkedin)
//                    binding.animationView.playAnimation()
//                }
//
//                Constants.TYPE_TIKTOK -> {
//                    binding.animationView.setAnimation(R.raw.tiktok)
//                    binding.animationView.playAnimation()
//                }
//
//                Constants.TYPE_SNAPCHAT -> {
//                    binding.animationView.setAnimation(R.raw.snapchat)
//                    binding.animationView.playAnimation()
//                }
//
//                Constants.TYPE_YOUTUBE -> {
//                    binding.animationView.setAnimation(R.raw.youtube)
//                    binding.animationView.playAnimation()
//                }
//
//                Constants.TYPE_PINTEREST -> {
//                    binding.animationView.setAnimation(R.raw.pinterest)
//                    binding.animationView.playAnimation()
//                }
//
//                Constants.TYPE_REDDIT -> {
//                    binding.animationView.setAnimation(R.raw.reddit)
//                    binding.animationView.playAnimation()
//                }
            }
        }
    }

    fun getWhatsappSatuses() {
//        val uri = Constants.getWhatsappUri()
//        val uri2 = Constants.getWhatsappBusinessUri()
//        Log.d("uri", "$uri")
//        Log.d("uri2", "$uri2")

        binding.permissionLayoutHolder.visibility = View.GONE
        statusViewModel.getWpStatuses()
    }

    fun getWhatsappBusinessStatuses() {
//        val uri = Constants.getWhatsappUri()
//        val uri2 = Constants.getWhatsappBusinessUri()
//        Log.d("uri", "$uri")
//        Log.d("uri2", "$uri2")

        binding.permissionLayoutHolder.visibility = View.GONE
        statusViewModel.getWpBusinessStatuses()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            val treeUri = data?.data!!
            requireActivity().contentResolver.takePersistableUriPermission(
                treeUri, Intent.FLAG_GRANT_READ_URI_PERMISSION
            )

            when (requestCode) {
                WHATSAPP_REQUEST_CODE -> {
//                    binding.animationView.setAnimation(R.raw.whatsapp)
//                    binding.animationView.playAnimation()

                    SharedPrefUtils.putPrefString(
                        SharedPrefKeys.PREF_KEY_WP_TREE_URI, treeUri.toString()
                    )
                    SharedPrefUtils.putPrefBoolean(
                        SharedPrefKeys.PREF_KEY_WP_PERMISSION_GRANTED, true
                    )
                    getWhatsappSatuses()
                }

                WHATSAPP_BUSINESS_REQUEST_CODE -> {
//                    binding.animationView.setAnimation(R.raw.whatsapp_business)
//                    binding.animationView.playAnimation()
                    SharedPrefUtils.putPrefString(
                        SharedPrefKeys.PREF_KEY_WP_BUSINESS_TREE_URI, treeUri.toString()
                    )
                    SharedPrefUtils.putPrefBoolean(
                        SharedPrefKeys.PREF_KEY_WP_BUSINESS_PERMISSION_GRANTED, true
                    )
                    getWhatsappBusinessStatuses()
                }
            }
        }
    }
}