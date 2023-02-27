package com.example.addnamesavedata1viewmodel.ui.main

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MainViewModel(state: SavedStateHandle) : ViewModel() {
    private val names1 = MutableLiveData(state.get<MutableList<String>>(NAMES_KEY) ?: mutableListOf())
    val names: LiveData<MutableList<String>>
        get() = names1

    companion object {
        const val NAMES_KEY = "names"
    }
    fun restoreInstanceState(savedInstanceState: Bundle?) {
        val savedNames = savedInstanceState?.getStringArrayList(NAMES_KEY)
        if (!savedNames.isNullOrEmpty()) {
            names1.value = savedNames.toMutableList()
        }
    }
    fun addName(name: String) {
        names1.value?.add(name)
    }
    fun clearNames() {
        names1.value?.clear()
    }
    fun saveInstanceState(outState: Bundle) {
        names1.value?.let {
            outState.putStringArrayList(NAMES_KEY, ArrayList(it))
        }
    }

}