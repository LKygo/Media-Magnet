package com.kygoinc.mediamagnet.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kygoinc.mediamagnet.data.StatusRepo
import com.kygoinc.mediamagnet.viewmodels.StatusViewModel

class StatusViewModelFactories(private val repo: StatusRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StatusViewModel(repo) as T
    }
}