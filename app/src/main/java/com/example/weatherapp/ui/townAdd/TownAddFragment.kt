package com.example.weatherapp.ui.townAdd

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.core.common.DatabaseModule
import com.example.weatherapp.core.common.Utils
import com.example.weatherapp.core.data.remote.mapper.asDatabaseModel
import com.example.weatherapp.core.domain.model.LocatedTown
import com.example.weatherapp.databinding.FragmentTownAddBinding
import com.example.weatherapp.ui.townAdd.adapter.SearchTownAdapter
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
    var locatedTownsSearchResult = mutableListOf<LocatedTown>()
    private lateinit var searchAdapter: SearchTownAdapter
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
            //adapter for search result
            searchAdapter = SearchTownAdapter(locatedTownsSearchResult){
                locatedTown = it
                binding.editTextTownName.setText(Utils.generateTownDisplayName(it))
            }
            townsToAddRecyclerView.adapter = searchAdapter
            townsToAddRecyclerView.layoutManager = LinearLayoutManager(requireContext())

            //search input with debounce timer
            editTextTownName.doAfterTextChanged { s ->
                searchJob?.cancel()

                searchJob = CoroutineScope(Dispatchers.Main).launch {
                    delay(700)

                    val searchInput = s.toString()
                    if(searchInput.isNotBlank() )
                    {
                        if(!searchInput.contains(',')){
                            showLoading(true)
                            viewModel.searchForTown(searchInput)
                        }
                    }
                }
            }

            //add button
            buttonAddTown.setOnClickListener {
                val hasLocatedTown = locatedTown != null
                val hasSearchResults = locatedTownsSearchResult.isNotEmpty()

                if (hasLocatedTown || hasSearchResults) {
                    val selectedTown = locatedTown ?: locatedTownsSearchResult.first()
                    lifecycleScope.launch {
                        DatabaseModule.townDao().addLocatedTown(selectedTown.asDatabaseModel())
                    }
                    clearUI()
                    Toast.makeText(context, resources.getText(R.string.town_add_success), Toast
                        .LENGTH_LONG).show()
                }else {
                    Toast.makeText(context,resources.getText(R.string.no_town_found_to_add),Toast
                        .LENGTH_LONG).show()
                }
            }


        }
    }
    private fun clearUI() {
        locatedTown = null
        binding.editTextTownName.text.clear()
        locatedTownsSearchResult.clear()
        searchAdapter.notifyDataSetChanged()
    }
    private fun observeTownSearch()
    {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            viewModel.searchFlow.collect { searchResult ->
                showLoading(false)
                if(searchResult.isEmpty()){
                    Toast.makeText(context,resources.getText(R.string.no_town_found), Toast
                        .LENGTH_LONG).show()
                    return@collect
                }
                displayResults(searchResult)
            }
        }
    }

    private fun displayResults(locatedTowns: List<LocatedTown>)
    {
        locatedTownsSearchResult.clear()
        locatedTownsSearchResult.addAll(locatedTowns)
        searchAdapter.notifyDataSetChanged()
    }

    private fun showLoading(show: Boolean) {
        with(binding)
        {
            if (show) {
                progressBar.visibility = View.VISIBLE
                contentLayout.visibility = View.INVISIBLE
            } else {
                progressBar.visibility = View.GONE
                contentLayout.visibility = View.VISIBLE
            }
        }

    }
}