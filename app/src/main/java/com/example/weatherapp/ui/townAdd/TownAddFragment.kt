package com.example.weatherapp.ui.townAdd

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.core.common.DatabaseModule
import com.example.weatherapp.core.data.remote.mapper.asDatabaseModel
import com.example.weatherapp.core.domain.model.LocatedTown
import com.example.weatherapp.databinding.FragmentTownAddBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TownAddFragment : Fragment() {
    private lateinit var binding: FragmentTownAddBinding
    private val viewModel: TownAddViewModel by viewModels()
    var searchJob: Job? = null
    var locatedTown: LocatedTown? = null

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

        with(binding)
        {
            editTextTownName.doAfterTextChanged { s ->
                searchJob?.cancel()

                searchJob = CoroutineScope(Dispatchers.Main).launch {
                    delay(700)

                    val searchInput = s.toString()
                    if(searchInput.isNotBlank())
                    {
                        viewModel.searchForTown(searchInput)

                    }
                }
            }
            buttonAddTown.setOnClickListener {
                locatedTown?.let {

                    lifecycleScope.launch {
                        DatabaseModule.townDao().addLocatedTown(it.asDatabaseModel())
                    }

                    locatedTown = null
                    editTextTownName.text.clear()
                    Toast.makeText(context,"Town successfully added!",Toast.LENGTH_LONG).show()
                }?: run {
                    Toast.makeText(context,"No town found to add!",Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun observeTownSearch()
    {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            viewModel.searchFlow.collect { searchResult ->
                Log.d("weather_app", "observeTownSearch: $searchResult")
                if(searchResult.isEmpty()) return@collect

                locatedTown = searchResult.first()
            }
        }
    }
}