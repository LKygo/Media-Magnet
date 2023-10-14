package com.kygoinc.mediamagnet.utils

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.kygoinc.mediamagnet.R

fun Activity.replaceFragment(fragment: Fragment, args: Bundle?=null){
    this as FragmentActivity
    supportFragmentManager.beginTransaction().apply {
        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        args?.let {
            fragment.arguments = it
        }
        replace(R.id.fragment_container, fragment)
        addToBackStack(null)
    }.commit()
}