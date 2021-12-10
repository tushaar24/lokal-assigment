package com.example.simpleapifetch.ui.fragments.showDataListFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpleapifetch.databinding.FragmentShowDataListBinding

class ShowDataList : Fragment() {

    private var _binding: FragmentShowDataListBinding? = null
    private val binding get() = _binding!!
    private val mViewModel: ShowDataListViewModel by viewModels()
    private lateinit var mAdapter: ShowDataAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentShowDataListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = ShowDataAdapter(requireContext())

        mViewModel.getDataList()

        binding.progressBar.visibility = View.VISIBLE

        mViewModel.isSuccessful.observe(requireActivity(), { isSuccessful ->
            if (isSuccessful) {
                mViewModel.dataList.observe(requireActivity(), {
                    mAdapter.setDataList(it)
                    binding.progressBar.visibility = View.GONE
                })
            } else {
                binding.progressBar.visibility = View.GONE
                binding.rvDataList.visibility = View.GONE
                binding.tvErrorText.visibility = View.VISIBLE
            }
        })

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        binding.rvDataList.adapter = mAdapter
        binding.rvDataList.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding
    }

}