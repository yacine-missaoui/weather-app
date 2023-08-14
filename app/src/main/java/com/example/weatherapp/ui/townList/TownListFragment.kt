package com.example.weatherapp.ui.townList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.withStarted
import com.example.weatherapp.R
import com.example.weatherapp.core.common.ApiClient
import com.example.weatherapp.core.data.remote.dto.request.GeoLocateTownParameter
import com.example.weatherapp.databinding.FragmentTownListBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class TownListFragment : Fragment() {
    private lateinit var binding: FragmentTownListBinding
    private val viewModel: TownListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTownListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                observeTownList()
            }
        }


    }
    private suspend fun observeTownList()
    {
        viewModel.townsState.collectLatest { towns ->

            if (towns.isEmpty()) {
                with(binding)
                {
                    textViewEmptyMessage.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
            } else {
                with(binding)
                {
                    textViewEmptyMessage.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                }
            }
        }
    }
}
