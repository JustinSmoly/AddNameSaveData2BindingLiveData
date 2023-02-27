package com.example.addnamesavedata1viewmodel.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.addnamesavedata1viewmodel.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel
    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.button.setOnClickListener {
            val name = binding.textInputEditText.text.toString()
            if (name.isNotEmpty()) {
                viewModel.addName(name)
                updateNames()
                binding.textInputEditText.text?.clear()
            }
        }

        if (savedInstanceState != null) {
            viewModel.restoreInstanceState(savedInstanceState)
            updateNames()
        } else {
            viewModel.clearNames()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateNames() {
        val namesTextview = binding.textView
        val nameList = viewModel.names.value
        if (nameList.isNullOrEmpty()) {
            namesTextview.text = "No names entered yet."
        } else {
            val namesString = nameList.joinToString("\n")
            namesTextview.text = namesString
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.saveInstanceState(outState)
    }
}