package com.example.weatherapp.ui.townAdd

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentTownAddBinding
import com.example.weatherapp.databinding.FragmentTownListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TownAddFragment : Fragment() {
    private lateinit var binding: FragmentTownAddBinding
    private val viewModel: TownAddViewModel by viewModels()
    var searchJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentTownAddBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        observeTownSearch()

    }

    private fun setupViews()
    {
        binding.editTextTownName.doAfterTextChanged { s ->
            searchJob?.cancel()

            searchJob = CoroutineScope(Dispatchers.Main).launch {
                delay(500)
                viewModel.searchForTown(s.toString())
            }
        }
    }

    private fun observeTownSearch()
    {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            viewModel.searchFlow.collect { searchResult ->
                Log.d("weather_app", "observeTownSearch: $searchResult")
            }
        }
    }
}