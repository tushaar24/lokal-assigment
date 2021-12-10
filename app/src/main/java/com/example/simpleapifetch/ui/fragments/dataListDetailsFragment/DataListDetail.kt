package com.example.simpleapifetch.ui.fragments.dataListDetailsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.simpleapifetch.R
import com.example.simpleapifetch.databinding.FragmentDataListDetailBinding

class DataListDetail : Fragment() {

    private var _binding: FragmentDataListDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDataListDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = arguments?.get("title")
        val url = arguments?.get("url")

        if (title != null && url != null) {
            binding.tvTitle.text = title.toString()

            Glide.with(this)
                .load(url)
                .placeholder(R.drawable.demo)
                .into(binding.ivDataImage)

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}