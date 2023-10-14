package com.kygoinc.mediamagnet.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.kygoinc.mediamagnet.R
import com.kygoinc.mediamagnet.databinding.ActivityMainBinding
import com.kygoinc.mediamagnet.utils.Constants
import com.kygoinc.mediamagnet.utils.replaceFragment
import com.kygoinc.mediamagnet.views.fragments.MediaFragment
import com.kygoinc.mediamagnet.views.fragments.SettingsFragment
import com.kygoinc.mediamagnet.views.fragments.StatusFragment

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val activity = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding.apply {
            bottomNavigation.setOnItemSelectedListener {
                when (it.itemId) {

                    R.id.menu_business_status -> {
                        Toast.makeText(this@MainActivity, "B Status clicked", Toast.LENGTH_SHORT).show()

                        Log.d("bottomnav","b status clicked")
                        val statusFragment = MediaFragment()
                        val bundle = Bundle()
                        bundle.putString(Constants.MEDIA_TYPE_KEY, Constants.TYPE_WHATSAPP_BUSINESS)
                        replaceFragment(statusFragment, bundle)
                    }

                    R.id.menu_settings -> {
                        Toast.makeText(this@MainActivity, "Settings clicked", Toast.LENGTH_SHORT).show()
                        Log.d("bottomnav","settings clicked")
                        replaceFragment(SettingsFragment())
                    }

                    R.id.menu_status -> {
                        Toast.makeText(this@MainActivity, "Status clicked", Toast.LENGTH_SHORT).show()
                        Log.d("bottomnav","status clicked")
                        val statusFragment = StatusFragment()
                        val bundle = Bundle()
                        bundle.putString(Constants.MEDIA_TYPE_KEY, Constants.TYPE_WHATSAPP_MAIN)
                        replaceFragment(statusFragment, bundle)
                    }
                }
                return@setOnItemSelectedListener true
            }
        }
    }
}