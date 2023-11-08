package com.kygoinc.mediamagnet.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import com.kygoinc.mediamagnet.R
import com.kygoinc.mediamagnet.databinding.ActivityMainBinding
import com.kygoinc.mediamagnet.utils.Constants
import com.kygoinc.mediamagnet.utils.SharedPrefUtils
import com.kygoinc.mediamagnet.utils.replaceFragment
import com.kygoinc.mediamagnet.views.fragments.MediaFragment
import com.kygoinc.mediamagnet.views.fragments.SettingsFragment
import com.kygoinc.mediamagnet.views.fragments.StatusFragment
import javax.net.ssl.SSLEngineResult.Status

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val activity = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        SharedPrefUtils.init(activity)
        val navView = binding.bottomNavigation

// Set initial fragment
        val initialFragment = StatusFragment()
        val initialBundle = Bundle()
        initialBundle.putString(Constants.FRAGMENT_TYPE_KEY, Constants.TYPE_WHATSAPP_MAIN)
        initialFragment.arguments = initialBundle
        supportFragmentManager.beginTransaction().replace(R.id.fragment_nav_container, initialFragment)
            .commit()

        navView.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.menu_business_status -> {


                    Log.d("bottomnav", "b status clicked")
                    val statusFragment = StatusFragment()
                    val bundle = Bundle()
                    bundle.putString(Constants.FRAGMENT_TYPE_KEY, Constants.TYPE_WHATSAPP_BUSINESS)
                    Toast.makeText(
                        this@MainActivity,
                        Constants.TYPE_WHATSAPP_BUSINESS,
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    replaceFragment(statusFragment, bundle)
                }

                R.id.menu_settings -> {
                    Toast.makeText(this@MainActivity, "Settings clicked", Toast.LENGTH_SHORT)
                        .show()
                    Log.d("bottomnav", "settings clicked")
                    replaceFragment(SettingsFragment())
                }

                R.id.menu_status -> {
                    Toast.makeText(this@MainActivity, "Status clicked", Toast.LENGTH_SHORT)
                        .show()
                    Log.d("bottomnav", "status clicked")
                    val statusFragment = StatusFragment()
                    val bundle = Bundle()
                    bundle.putString(Constants.FRAGMENT_TYPE_KEY, Constants.TYPE_WHATSAPP_MAIN)
                    Toast.makeText(
                        this@MainActivity,
                        Constants.TYPE_WHATSAPP_MAIN,
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    replaceFragment(statusFragment, bundle)
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val fragment =  supportFragmentManager.findFragmentById(R.id.fragment_nav_container)
        fragment?.onActivityResult(requestCode, resultCode, data)

    }
}
